package com.annodocs.annodocsbackend.gesetz.artikel;
import javax.persistence.*;

@Entity
public class artikelEntity {
  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long artikelId;

    @ManyToOne
    @JoinColumn(name = "gid")
    private Gesetz gesetz;

    private String nummer;
    private String name;
    private String land;

    // Standardkonstruktor
    public Artikel() {}

    // Parameterisierter Konstruktor
    public Artikel(Gesetz gesetz, String nummer, String name, String land) {
        this.gesetz = gesetz;
        this.nummer = nummer;
        this.name = name;
        this.land = land;
    }

    // Getter und Setter
    public Long getArtikelId() {
        return artikelId;
    }

    public void setArtikelId(Long artikelId) {
        this.artikelId = artikelId;
    }

    public Gesetz getGesetz() {
        return gesetz;
    }

    public void setGesetz(Gesetz gesetz) {
        this.gesetz = gesetz;
    }

    public String getNummer() {
        return nummer;
    }

    public void setNummer(String nummer) {
        this.nummer = nummer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }
}
}
