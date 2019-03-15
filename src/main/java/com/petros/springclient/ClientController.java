package com.petros.springclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class ClientController {

    public static final Logger log = LoggerFactory.getLogger(SpringclientApplication.class);

    private final
    RestTemplate restTemplate;

    @Autowired
    public ClientController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping(path = "/animals")
    public String getAllAnimals(){
        log.info("Request for all animals.");
        String url = "http://localhost:8088/animals";
        ResponseEntity<String> re = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        return re.getBody();
    }


    @GetMapping(path = "/randomanimal")
    public String getRandomAnimal(){
        log.info("Request for a random animal.");
        String url = "http://localhost:8088/randomanimal";
        ResponseEntity<String> re = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        return re.getBody();
    }

    @PostMapping("/animals")
    public ResponseEntity<String> createAnimal(@RequestBody Animal animal){
        String url = "http://localhost:8088/animals";
        return null;
    }


}
