package com.thanksgiving.thanksgivingservice.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sectionName;
    private BigDecimal sectionAmount;
    private String sectionLeader;


    @ManyToOne
    @JoinColumn(name = "thanksGiving_id")
    @JsonBackReference
    private ThanksGiving thanksGiving;
}
