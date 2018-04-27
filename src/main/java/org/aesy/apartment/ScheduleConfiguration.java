package org.aesy.apartment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@EnableScheduling
@Configuration
public class ScheduleConfiguration implements SchedulingConfigurer {
    private final ApartmentProperties apartmentProperties;
    private final ApartmentJob apartmentJob;

    @Autowired
    public ScheduleConfiguration(ApartmentProperties apartmentConfiguration, ApartmentJob apartmentJob) {
        this.apartmentProperties = apartmentConfiguration;
        this.apartmentJob = apartmentJob;
    }

    @Bean
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(100);
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar registrar) {
        registrar.setScheduler(taskExecutor());

        List<ApartmentHunter> hunters = apartmentProperties.getHunter();

        for (ApartmentHunter hunter : hunters) {
            String cron = hunter.getCheck().getCron();
            Integer interval = hunter.getCheck().getInterval();
            Boolean startup = hunter.getCheck().getStartup();

            if (cron != null) {
                registrar.addCronTask(() -> {
                    apartmentJob.checkForNewApartments(hunter);
                }, cron);
            }

            if (interval != null) {
                registrar.addFixedRateTask(() -> {
                    apartmentJob.checkForNewApartments(hunter);
                }, TimeUnit.MILLISECONDS.convert(interval, TimeUnit.SECONDS));
            }

            if (cron == null && interval == null) {
                throw new IllegalArgumentException("CRON expression or interval must be provided");
            }

            if (startup) {
                apartmentJob.checkForNewApartments(hunter);
            }
        }
    }
}
