package com.example.songsappYOTPO.producer;

import com.example.songsappYOTPO.shared.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/producer")
public class ProducerController {
    private final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/add")
    public ResponseEntity<Producer> addProducer(@Valid @RequestBody Producer producer){
        return new ResponseEntity<>(producerService.save(producer), HttpStatus.OK);
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<Producer>> getProducers(){
        List<Producer> producers = producerService.getProducers();
        if (producers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(producers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producer> getProducerById(@PathVariable Long id){
        Optional<Producer> producerOptional = producerService.findById(id);
        if (producerOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(producerOptional.get(), HttpStatus.OK);
    }
    @GetMapping("/getbyfullname")
    public ResponseEntity<List<Producer>> findByLastNameAndFirstNameAllIgnoreCase(@RequestParam String lastName, @RequestParam String firstName){
        List<Producer> producers = producerService.findByLastNameAndFirstNameAllIgnoreCase(lastName, firstName);
        if (producers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(producers, HttpStatus.OK);
    }
    @PutMapping("/update1/{id}")//Option1 - Load object, modify and save again
    public ResponseEntity<Producer> updateProducerByIdLoadObject(@PathVariable Long id, @RequestBody Producer producer){
        Optional<Producer> producerOptional = producerService.findById(id);
        if (producerOptional.isPresent()){
            Producer producerToUpdate = producerOptional.get();
            producerToUpdate.setFirstName(producer.getFirstName());
            producerToUpdate.setLastName(producer.getLastName());
            producerToUpdate.setSongs(producer.getSongs());
            Producer savedProducer = producerService.save(producerToUpdate);
            return new ResponseEntity<>(savedProducer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update2/{id}")//Option2 - Create a custom query, without Loading the object
    public ResponseEntity<CustomResponse<Producer>> updateProducerByIdNoLoadObject(@PathVariable Long id, @RequestBody Producer producer){
        CustomResponse<Producer> updateProducerResponse = new CustomResponse();
        updateProducerResponse.setData(producer);
        if (producerService.updateProducer(id, producer) == 1){
            updateProducerResponse.setMessage("Updated producer.id: " + id);
            return new ResponseEntity<>(updateProducerResponse, HttpStatus.OK);
        }
        updateProducerResponse.setMessage("Did not update producer.id: " + id);
        return new ResponseEntity<>(updateProducerResponse, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id){
            producerService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}



