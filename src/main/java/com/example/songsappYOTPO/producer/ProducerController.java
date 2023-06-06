package com.example.songsappYOTPO.producer;

import com.example.songsappYOTPO.shared.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/producer")
public class ProducerController {
    private final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/add")
    public ResponseEntity<Producer> addProducer(@RequestBody Producer producer){
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
    public ResponseEntity<CustomResponse> updateProducerByIdNoLoadObject(@PathVariable Long id, @RequestBody Producer producer){
        CustomResponse updateProducerResponse = new CustomResponse();
        if (producerService.updateProducer(id, producer) == 1){
            updateProducerResponse.setMessage("Updated producer.id: " + id);
            updateProducerResponse.setData(producer);
            return new ResponseEntity<>(updateProducerResponse, HttpStatus.OK);
        }
        updateProducerResponse.setMessage("Did not update producer.id: " + id);
        updateProducerResponse.setData(producer);
        return new ResponseEntity<>(updateProducerResponse, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id){
            producerService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
    }

}



