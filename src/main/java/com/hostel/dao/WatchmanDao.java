package com.hostel.dao;

import com.hostel.model.Watchman;

public interface WatchmanDao extends Dao<Watchman> {

    Watchman findByUsername(String username);

}
