package com.fakesib.SpaceOfPsychologyAndSpeech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "my_records")
@NoArgsConstructor
@AllArgsConstructor
public class MyRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String date;
    private String time;
}
