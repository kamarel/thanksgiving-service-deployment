package com.thanksgiving.thanksgivingservice.Service;

import com.google.common.net.HttpHeaders;
import com.thanksgiving.thanksgivingservice.Dto.ApiResponseDto;
import com.thanksgiving.thanksgivingservice.Dto.MembersDto;
import com.thanksgiving.thanksgivingservice.Entity.Section;
import com.thanksgiving.thanksgivingservice.Entity.ThanksGiving;
import com.thanksgiving.thanksgivingservice.Repository.SectionRepository;
import com.thanksgiving.thanksgivingservice.Repository.ThanksGivingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThanksGivingServiceImp implements ThanksGivingService{

    @Autowired
    private ThanksGivingRepository thanksGivingRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private WebClient webClient;

    @Autowired
    private EmailService emailService;

    @Override
    public ThanksGiving createThanksGiving(ThanksGiving thanksGiving, String token) {
        ThanksGiving savedThanksGiving = thanksGivingRepository.save(thanksGiving);

        // Get all members using the provided token
        ApiResponseDto apiResponseDto = getAllMembers(token);
        List<MembersDto> membersDtoList = apiResponseDto.getMembersDtoList();

        // Extract email addresses
        List<String> emails = membersDtoList.stream()
                .map(MembersDto::getEmail) // Ensure MembersDto has getEmail()
                .collect(Collectors.toList());

        // Send notification
        emailService.sendHarvestNotification(emails);

        return savedThanksGiving;
    }

    @Override
    public ThanksGiving updateThanksGiving(Long id, ThanksGiving updatedThanksGiving) {
        ThanksGiving existingThanksGiving = thanksGivingRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Harvest Not Found"));

        existingThanksGiving.setStartedDate(updatedThanksGiving.getStartedDate());
        existingThanksGiving.setEndDate(updatedThanksGiving.getEndDate());
        existingThanksGiving.setSupervise(updatedThanksGiving.getSupervise());
        existingThanksGiving.setSections(updatedThanksGiving.getSections());


        return thanksGivingRepository.save(existingThanksGiving);
    }

    @Override
    public void deleteThanksGivingById(Long id) {
        ThanksGiving thanksGiving = thanksGivingRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Harvest Not Found"));
        thanksGivingRepository.delete(thanksGiving);
    }

    @Override
    public List<ThanksGiving> getAllThanksGiving() {
        return thanksGivingRepository.findAll();
    }

    @Override
    public ThanksGiving getThanksGivingById(Long id) {
        return thanksGivingRepository.findById(id).orElseThrow(()-> new RuntimeException("ThanksGiving not found"));
    }

    @Override
    public List<ThanksGiving> searchThanksGiving(String query) {
        return thanksGivingRepository.searchThanksGiving(query);
    }

    @Override
    public void deleteThanksGiving() {
        thanksGivingRepository.deleteAll();
    }



    @Override
    public ApiResponseDto getAllMembers(String token) {
        Mono<List<MembersDto>> listMono = webClient.get()
                .uri("https://distinguished-education-production.up.railway.app/api/v1/members") // Use API Gateway
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token) // Attach the JWT token
                .retrieve()
                .bodyToFlux(MembersDto.class)
                .collectList();

        List<MembersDto> membersDtoList = listMono.block();

        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setMembersDtoList(membersDtoList);

        return apiResponseDto;
    }
}
