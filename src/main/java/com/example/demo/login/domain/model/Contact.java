package com.example.demo.login.domain.model;

import lombok.Data;

import java.util.Date;

@Data
public class Contact {
    private String title;
    private String text;
    private String contributor;
    private Date createDate;
    private String role;
}
