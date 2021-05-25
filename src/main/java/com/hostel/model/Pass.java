package com.hostel.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Pass implements com.hostel.model.Entity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne()
    private Watchman watchman;

    @OneToMany(mappedBy = "pass")
    private Set<PassLine> lines;

    private Date date;

    private String patient;

    private Status status;

    public static enum Status {
        NEW,
        OLD
    }

}
