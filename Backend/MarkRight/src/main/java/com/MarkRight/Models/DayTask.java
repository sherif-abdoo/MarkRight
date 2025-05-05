package com.MarkRight.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class DayTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "day_id" , nullable = false)
    private Day day;

    @ManyToOne
    @JoinColumn(name = "task_id" , nullable = false)
    private Task task;
}
