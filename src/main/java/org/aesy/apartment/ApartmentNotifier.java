package org.aesy.apartment;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Log
@Component
public class ApartmentNotifier {
    @Value("${apartment.mail.reciever}")
    private String RECIEVER;

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Autowired
    public ApartmentNotifier(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @EventListener
    public void notifyNewApartments(NewApartmentsEvent event) {
        List<Apartment> apartments = event.getApartments();

        List<Apartment> urgent = apartments.stream()
                                           .filter(Apartment::isQuick)
                                           .collect(Collectors.toList());

        List<Apartment> nonUrgent = apartments.stream()
                                              .filter(apartment -> !apartment.isQuick())
                                              .collect(Collectors.toList());

        if (urgent.size() > 0) {
            notifyUrgentApartments(urgent);
        }

        if (nonUrgent.size() > 0) {
            notifyNonUrgentApartments(nonUrgent);
        }
    }

    private void notifyUrgentApartments(List<Apartment> apartments) {
        sendEmail(RECIEVER, "(QUICK NOTICE) New apartments found!", apartments);
    }

    private void notifyNonUrgentApartments(List<Apartment> apartments) {
        sendEmail(RECIEVER, "New apartments found!", apartments);
    }

    private void sendEmail(String reciever, String subject, List<Apartment> apartments) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                                                             MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                                                             StandardCharsets.UTF_8.name());
            Context context = new Context();
            context.setVariable("apartments", apartments);
            String html = templateEngine.process("new_apartments", context);

            helper.setTo(reciever);
            helper.setSubject(subject);
            helper.setText(html, true);

            mailSender.send(message);
        } catch (MessagingException exception) {
            log.warning(String.format("Failed to send email. Error: '%s'.", exception.getMessage()));
        }
    }
}
