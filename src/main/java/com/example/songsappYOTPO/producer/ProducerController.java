package com.example.songsappYOTPO.producer;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id){
            producerService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
    }

}



