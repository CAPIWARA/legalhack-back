package br.com.legalhack.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Card {

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String bia;

    public String getBia() {
        return bia;
    }

    public void setBia(String bia) {
        this.bia = bia;
    }
}
