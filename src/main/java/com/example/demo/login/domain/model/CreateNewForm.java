package com.example.demo.login.domain.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class CreateNewForm {


    private String title;
    private String Text;
    private String contributor;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date createDate;
    private String role;
}
