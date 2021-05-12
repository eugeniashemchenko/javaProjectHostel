package com.hostel.dao;

import com.hostel.model.Entity;

public interface Dao<E extends Entity> {
    
    E findById(Long id);
    
    E create(E entity);

}
