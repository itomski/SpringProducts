package de.lubowiecki.sproducts.model;

import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.Serializable;

public class User implements Serializable {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
