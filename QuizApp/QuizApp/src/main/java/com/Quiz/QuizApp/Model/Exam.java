package com.Quiz.QuizApp.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.security.PrivateKey;
import java.util.List;

@Entity
@Data
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    @ManyToMany
    private List<Question> list;
}
