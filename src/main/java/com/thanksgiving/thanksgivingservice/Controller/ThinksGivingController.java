package com.thanksgiving.thanksgivingservice.Controller;


import com.thanksgiving.thanksgivingservice.Entity.Section;
import com.thanksgiving.thanksgivingservice.Entity.ThanksGiving;
import com.thanksgiving.thanksgivingservice.Service.SectionServiceImp;
import com.thanksgiving.thanksgivingservice.Service.ThanksGivingServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(
        name="ThinksGiving Controller",
        description = "ThinksGiving Controller exposes Rest Api for ThinksGiving service"
)
@RestController
@RequestMapping("/api/thanksGiving")
@AllArgsConstructor
public class ThinksGivingController {
    @Autowired
    private ThanksGivingServiceImp thanksGivingServiceImp;

    @Autowired
    private SectionServiceImp sectionServiceImp;

    @PostMapping("/{thanksId}/add")
    public ResponseEntity<Section> AddSectionToThanksGiving(@PathVariable String thanksId, @RequestBody Section section) {

        Section savedSection = sectionServiceImp.addSectionInHarvest(thanksId, section);
        return new ResponseEntity<>(savedSection, HttpStatus.CREATED);

    }

    @PutMapping("/{id}/section")
    public ResponseEntity<Section>updateSectionInfo(@PathVariable Long id, @RequestBody Section sectionToUpdate){
        return new ResponseEntity<>(sectionServiceImp.updatedSection(id, sectionToUpdate), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete/section")
    public ResponseEntity<String>deleteSectionById(@PathVariable Long id){
        sectionServiceImp.deleteSection(id);
        return new ResponseEntity<>("Section deleted successfully", HttpStatus.OK);
    }


    @Operation(
            summary = "Create a new thanksGiving information",
            description = "This Endpoint allow the user to add a new thinksGiving"
    )

    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<ThanksGiving> createThanksGiving(
            @RequestBody ThanksGiving thanksGiving,
            @RequestHeader(name = "Authorization", required = true) String authorizationHeader) {

        // Extract Bearer token (remove "Bearer " prefix)
        String token = authorizationHeader.startsWith("Bearer ") ? authorizationHeader.substring(7) : authorizationHeader;

        return new ResponseEntity<>(thanksGivingServiceImp.createThanksGiving(thanksGiving, token), HttpStatus.CREATED);
    }



    @Operation(
            summary = "Updating thanksGiving information",
            description = "This Endpoint allow the user to update by ID thinksGiving"
    )

    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 OK"
    )
    @PutMapping("/{id}")
    public ResponseEntity<ThanksGiving>updateThanksGiving(@PathVariable Long id, @RequestBody ThanksGiving thanksGiving){
        ThanksGiving updateThanksGiving = thanksGivingServiceImp.updateThanksGiving(id, thanksGiving);

        return new ResponseEntity<>(updateThanksGiving, HttpStatus.OK);
    }

    @Operation(
            summary = "Get all thanksGiving information",
            description = "This Endpoint allow the user to get all the thinksGiving"
    )

    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 FOUND"
    )
    @GetMapping
    public ResponseEntity<List<ThanksGiving>>getAllThanksGiving(){
        return new ResponseEntity<>(thanksGivingServiceImp.getAllThanksGiving(), HttpStatus.OK);
    }


    @Operation(
            summary = "Search thanksGiving information",
            description = "This Endpoint allow the user to use search bar to find  thinksGiving"
    )

    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 FOUND"
    )
    @GetMapping("/{search}")
    public ResponseEntity<List<ThanksGiving>>searchThanksGiving(@RequestParam String query){
        List<ThanksGiving>harvestList = thanksGivingServiceImp.searchThanksGiving(query);

        return new ResponseEntity<>(harvestList, HttpStatus.OK);
    }

    @Operation(
            summary = "Get thanksGiving information by ID",
            description = "This Endpoint allow the user to get by ID thinksGiving"
    )

    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 FOUND"
    )
    @GetMapping("/get/{id}")
    public ResponseEntity<ThanksGiving>getThanksGivingById(@PathVariable Long id){
        ThanksGiving harvest = thanksGivingServiceImp.getThanksGivingById(id);
        return new ResponseEntity<>(harvest, HttpStatus.OK);
    }

    @Operation(
            summary = "Deletes thanksGiving information by ID",
            description = "This Endpoint allow the user to delete by ID thinksGiving"
    )

    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 OK"
    )
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String>deleteThanksGivingById(@PathVariable Long id){
        thanksGivingServiceImp.deleteThanksGivingById(id);
        return new ResponseEntity<>("ThanksGiving Deleted", HttpStatus.OK);
    }

    @Operation(
            summary = "Delete all thanksGiving information ",
            description = "This Endpoint allow the user to delete all the thinksGiving"
    )

    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 OK"
    )
    @DeleteMapping
    public ResponseEntity<String>deleteAllThanksGiving(){
        thanksGivingServiceImp.deleteThanksGiving();
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }


}
