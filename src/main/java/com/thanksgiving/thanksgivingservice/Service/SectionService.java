package com.thanksgiving.thanksgivingservice.Service;

import com.thanksgiving.thanksgivingservice.Entity.Section;

public interface SectionService {

    Section addSectionInHarvest(String thanksGivingId, Section section);

    Section updatedSection (Long id, Section sectionToUpdate);

    void deleteSection(Long id);
}
