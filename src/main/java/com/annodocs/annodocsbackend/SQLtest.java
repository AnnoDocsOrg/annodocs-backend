package com.annodocs.annodocsbackend;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Test")
public class SQLtest {
    @Id
    @Column(name = "ID")
    private int id;
    @Column(name = "NAME")
    private String name;

}
