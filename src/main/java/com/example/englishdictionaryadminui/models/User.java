package com.example.englishdictionaryadminui.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String email;
    private String fullName;
    private Integer gender;
    private Integer level;
    private Integer occupation;
    private Boolean active;
}
