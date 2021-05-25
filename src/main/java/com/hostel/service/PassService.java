package com.hostel.service;

import java.util.Set;

import com.hostel.model.Pass;
import com.hostel.model.PassLine;

public class PassService {

    public void create(Pass pass) {
        Set<PassLine> lines = pass.getLines();
        for (PassLine passLine : lines) {

        }
    }
}
