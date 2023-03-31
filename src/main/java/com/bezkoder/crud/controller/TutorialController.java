package com.bezkoder.crud.controller;

import com.bezkoder.crud.exception.TutorialNotFoundException;
import com.bezkoder.crud.model.Tutorial;
import com.bezkoder.crud.repository.TutorialRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Tag(name ="tutorial", description="Tutorial management API")
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api")
@RestController
public class TutorialController {

    @Autowired
    private TutorialRepository tutorialRepository;

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials (@RequestParam(required = false) String title){

        List<Tutorial> tutorials = new ArrayList<>();

        if(title == null){
             tutorialRepository.findAll().forEach(tutorials::add);

        }else{
            tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);
        }

        if(tutorials.isEmpty() ){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }

    @Operation(
      summary = "Retrieve a Tutorial by Id",
      description = "Get a Tutorial object by specifying its id. The response is Tutorial object with id, title, description and published status.",
      tags = { "tutorials", "get" })
//   @ApiResponses({
//       @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Tutorial.class), mediaType = "application/json") }),
//       @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
//       @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") Long id){

        Optional<Tutorial> tutorial = this.tutorialRepository.findById(id);

        if(tutorial.isEmpty()){

            throw new TutorialNotFoundException(String.format("Tutorial with id %d not found", id));

        }

        return ResponseEntity.ok(tutorial.get());
    }

    @PostMapping("/tutorials")
    public ResponseEntity<Tutorial> createTutorial (@RequestBody Tutorial tutorial){

        Tutorial _tutorial = tutorialRepository.save(tutorial);
        return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
    }


    @PutMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> updateTutorial (@PathVariable("id") Long id, @RequestBody Tutorial tutorial){

        Optional<Tutorial> optionalTutorial = tutorialRepository.findById(id);

        if(optionalTutorial.isPresent()){

            Tutorial newTutorial = optionalTutorial.get();
            newTutorial.setDescription(tutorial.getDescription());
            newTutorial.setPublished(tutorial.isPublished());
            newTutorial.setTitle(tutorial.getTitle());

            return new ResponseEntity<>(tutorialRepository.save(newTutorial), HttpStatus.OK);

        }else{

            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

    }


    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") Long id){

        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

        if(tutorialData.isPresent()){
            tutorialRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{

             throw new TutorialNotFoundException("Not found tutorial with id : "+id);
        }

    }


    @DeleteMapping("/tutorials")
    public ResponseEntity<HttpStatus> deleteAllTutorials(){
        try{
            tutorialRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



}
