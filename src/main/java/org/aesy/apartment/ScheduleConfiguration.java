package org.aesy.apartment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.PeriodicTrigger;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class ScheduleConfiguration {
    private final ApartmentProperties apartmentProperties;
    private final ApartmentJob apartmentJob;
    private final TaskScheduler scheduler;

    @Autowired
    public ScheduleConfiguration(ApartmentProperties apartmentConfiguration, ApartmentJob apartmentJob) {
        this.apartmentProperties = apartmentConfiguration;
        this.apartmentJob = apartmentJob;
        this.scheduler = new ConcurrentTaskScheduler();

        scheduleJobs();
    }

    public void scheduleJobs() {
        List<ApartmentHunter> hunters = apartmentProperties.getHunter();

        for (ApartmentHunter hunter : hunters) {
            String cron = hunter.getCheck().getCron();
            Integer interval = hunter.getCheck().getInterval();
            Boolean startup = hunter.getCheck().getStartup();

            if (cron != null) {
                scheduler.schedule(() -> {
                    apartmentJob.checkForNewApartments(hunter);
                }, new CronTrigger(cron));
            }

            if (interval != null) {
                scheduler.schedule(() -> {
                    apartmentJob.checkForNewApartments(hunter);
                }, new PeriodicTrigger(interval, TimeUnit.SECONDS));
            }

            if (cron == null && interval == null) {
                throw new IllegalArgumentException("CRON expression or interval must be provided");
            }

            if (startup != null) {
                apartmentJob.checkForNewApartments(hunter);
            }
        }
    }
}
