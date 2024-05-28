package com.annodocs.annodocsbackend.gesetz;


import com.annodocs.annodocsbackend.gesetz.paragraph.paragraphEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "Gesetz")
public class gesetzEntity {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long GiD;

    @Setter
    @Getter
    @OneToMany(mappedBy = "gesetz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<paragraphEntity> paragraphen;

    @Setter
    @Getter
    private String Anlage;

    @Setter
    @Getter
    private String Name;

    public gesetzEntity() {
    }

    public gesetzEntity(List<paragraphEntity> paragraphen, String Anlage, String Name) {
        this.paragraphen = paragraphen;
        this.Anlage = Anlage;
        this.Name = Name;
    }

    @Override
    public String toString() {
        return "Gesetz{" +
                "GiD=" + GiD +
                ", Anlage='" + Anlage + '\'' +
                ", Name='" + Name + '\'' +
                '}';
    }



}
