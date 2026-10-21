package fr.epsi.civicscope.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Election {

    @Id
    @UuidGenerator
    private UUID id;

    private String code;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private TypeElection typeElection;

    @Enumerated(EnumType.STRING)
    private TourElection tourElection;

    private String codePays;

    public enum TypeElection {
        PRESIDENTIELLE, LEGISLATIVE, MUNICIPALE, DEPARTEMENTALE, REGIONALE, LOCALE, REFERENDUM
    }

    public enum TourElection {
        TOUR_UNIQUE, PREMIER_TOUR, SECOND_TOUR
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public TypeElection getTypeElection() {
        return typeElection;
    }

    public void setTypeElection(TypeElection typeElection) {
        this.typeElection = typeElection;
    }

    public TourElection getTourElection() {
        return tourElection;
    }

    public void setTourElection(TourElection tourElection) {
        this.tourElection = tourElection;
    }

    public String getCodePays() {
        return codePays;
    }

    public void setCodePays(String codePays) {
        this.codePays = codePays;
    }
}