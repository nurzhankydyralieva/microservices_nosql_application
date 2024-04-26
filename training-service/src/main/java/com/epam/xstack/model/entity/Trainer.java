package com.epam.xstack.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(value = "trainer")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String userName;
    @Indexed
    private String firstName;
    @Indexed
    private String lastName;
    private String status;
    private Number duration;
    @ElementCollection
    private List<Integer> years;
    @ElementCollection
    private List<String> months;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "trainer")
    private List<Training> trainings;
}
