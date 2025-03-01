package com.thanksgiving.thanksgivingservice.Service;

import com.thanksgiving.thanksgivingservice.Entity.Section;
import com.thanksgiving.thanksgivingservice.Entity.ThanksGiving;
import com.thanksgiving.thanksgivingservice.Repository.SectionRepository;
import com.thanksgiving.thanksgivingservice.Repository.ThanksGivingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SectionServiceImp implements SectionService{

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private ThanksGivingRepository thanksGivingRepository;


    @Override
    public Section addSectionInHarvest(String thanksGivingId, Section section) {
        // This now returns the first matching ThanksGiving
        ThanksGiving thanksGiving = thanksGivingRepository.findFirstByThanksGivingId(thanksGivingId);
        if (thanksGiving == null) {
            throw new RuntimeException("ThanksGiving not found for ID: " + thanksGivingId);
        }
        section.setThanksGiving(thanksGiving);
        return sectionRepository.save(section);
    }


    @Override
    public Section updatedSection(Long id, Section sectionToUpdate) {
        Optional<Section> optionalSection = sectionRepository.findById(id);

        if(!optionalSection.isPresent()){
            throw new RuntimeException("The section does not exist");
        }

        Section existSection = optionalSection.get();

        existSection.setSectionAmount(sectionToUpdate.getSectionAmount());
        existSection.setSectionLeader(sectionToUpdate.getSectionLeader());
        existSection.setSectionName(sectionToUpdate.getSectionName());

        return sectionRepository.save(existSection);
    }

    @Override
    public void deleteSection(Long id) {
        sectionRepository.deleteById(id);
    }
}
