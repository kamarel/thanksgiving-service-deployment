package com.thanksgiving.thanksgivingservice.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "thanksGiving")
public class ThanksGiving {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_id_seq")
    @SequenceGenerator(name = "event_id_seq", sequenceName = "YOUR_SEQUENCE_NAME", allocationSize = 1)
    private Long id;
    private String thanksGivingId;
    private String startedDate;
    private String endDate;
    private String supervise;





    @OneToMany(mappedBy = "thanksGiving", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Section> sections;
}