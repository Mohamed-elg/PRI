package com.bbai.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class mainModel {
    private Long Id;
    private boolean online;
    private String message;
}
