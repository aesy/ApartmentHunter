package org.aesy.apartment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.List;

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
            scheduler.schedule(() -> {
                apartmentJob.checkForNewApartments(hunter);
            }, new CronTrigger(cron));
        }
    }
}
