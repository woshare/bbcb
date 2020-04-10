package com.example.bbcb.entity;

import lombok.Data;



@Data
public class User {
    private Long uid;
    private String phoneNumber;
    private String registerTime;
    private String userName;
    private String passWord;

}
