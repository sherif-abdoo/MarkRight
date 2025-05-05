package com.MarkRight.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique=true)
    private String username;
    @Column(unique=false)
    @JsonIgnore
    private String password;
    private String email;
}
