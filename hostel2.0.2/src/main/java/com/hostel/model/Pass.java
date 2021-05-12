package com.hostel.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pass implements Entity {
    private Long id;
    private String sku; 
}
