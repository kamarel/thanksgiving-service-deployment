package com.thanksgiving.thanksgivingservice.Repository;

import com.thanksgiving.thanksgivingservice.Entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {



}
