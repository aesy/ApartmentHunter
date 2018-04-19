package org.aesy.apartment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
public class Apartment {
    @Id
    @JsonProperty("AnnonsId")
    private int id;

    @JsonProperty("Stadsdel")
    private String district;

    @JsonProperty("Gatuadress")
    private String address;

    @JsonProperty("Kommun")
    private String municipality;

    @JsonProperty("Vaning")
    private int floor;

    @JsonProperty("AntalRum")
    private int rooms;

    @JsonProperty("Yta")
    private int area;

    @JsonProperty("Hyra")
    private int rent;

    @JsonProperty("AnnonseradTill")
    private String validUntil;

    @JsonProperty("AnnonseradFran")
    private String created;

    @JsonProperty("KoordinatLongitud")
    private Double longitude;

    @JsonProperty("KoordinatLatitud")
    private Double latitude;

    @JsonProperty("Url")
    private String url;

    @JsonProperty("Antal")
    private int count;

    @JsonProperty("Balkong")
    private boolean balcony;

    @JsonProperty("Hiss")
    private boolean lift;

    @JsonProperty("Nyproduktion")
    private boolean newlyProduced;

    @JsonProperty("Ungdom")
    private boolean youth;

    @JsonProperty("Student")
    private boolean student;

    @JsonProperty("Senior")
    private boolean senior;

    @JsonProperty("Korttid")
    private boolean shorttime;

    @JsonProperty("Vanlig")
    private boolean regular;

    @JsonProperty("Bostadssnabben")
    private boolean quick;

    @JsonProperty("Ko")
    private String queue;

    @JsonProperty("KoNamn")
    private String queueName;

    @JsonProperty("Lagenhetstyp")
    private String type;
}
