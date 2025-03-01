package com.thanksgiving.thanksgivingservice.Service;

import com.thanksgiving.thanksgivingservice.Dto.ApiResponseDto;
import com.thanksgiving.thanksgivingservice.Entity.ThanksGiving;

import java.util.List;

public interface ThanksGivingService {
    ThanksGiving createThanksGiving(ThanksGiving harvest, String token);

    ThanksGiving updateThanksGiving(Long id, ThanksGiving updatedHarvest);

    void deleteThanksGivingById(Long id);

    List<ThanksGiving> getAllThanksGiving();

    ThanksGiving getThanksGivingById(Long id);

    List<ThanksGiving> searchThanksGiving(String query);
    void deleteThanksGiving();

    ApiResponseDto getAllMembers(String token);
}
