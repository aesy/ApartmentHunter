package org.aesy.apartment;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ApartmentHunter {
    @Data
    public static class Check {
        private String cron;
        private Integer interval;
        private Boolean startup;
    }

    @Data
    public static class Requirements {
        private Boolean shortTime;
        private Boolean regular;
        private Boolean student;
        private Boolean youth;
        private Boolean senior;
        private Boolean balcony;
        private Boolean lift;
        private Boolean newlyProduced;
        private Boolean quick;
        private String type;
        private Integer minRent;
        private Integer maxRent;
        private Integer minRooms;
        private Integer maxRooms;
        private Integer minArea;
        private Integer maxArea;
        private List<String> districts;
        private List<String> municipalities;
    }

    @NotNull
    private Check check;

    @NotNull
    private String email;

    @NotNull
    private Requirements requirements;
}
