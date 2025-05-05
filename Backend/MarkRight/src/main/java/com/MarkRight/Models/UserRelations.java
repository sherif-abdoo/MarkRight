package com.MarkRight.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserRelations {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user1_id" , nullable = false)
    private User user1;

    @ManyToOne
    @JoinColumn(name = "user2_id" , nullable = false)
    private User user2;

    @Enumerated(EnumType.STRING)
    private UsersRelationshipsStatus status;


}
