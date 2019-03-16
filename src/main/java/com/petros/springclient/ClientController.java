package com.petros.springclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
public class ClientController {

    public static final Logger log = LoggerFactory.getLogger(SpringclientApplication.class);
    String URL = "http://petros.us-east-2.elasticbeanstalk.com";

    private final
    RestTemplate restTemplate;

    @Autowired
    public ClientController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping(path = "/animals")
    public String getAllAnimals(){
        log.info("Request for all animals.");
        ResponseEntity<String> re = restTemplate.exchange(URL+"/animals", HttpMethod.GET, null, String.class);
        return re.getBody();
    }


    @GetMapping(path = "/randomanimal")
    public String getRandomAnimal(){
        log.info("Request for a random animal.");
        ResponseEntity<String> re = restTemplate.exchange(URL+"/randomanimal", HttpMethod.GET, null, String.class);
        return re.getBody();
    }

    @PostMapping("/animals")
    public String createAnimal(@RequestBody Animal animal){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Animal> entity = new HttpEntity<>(animal, headers);
        return restTemplate.exchange(URL+"/animals", HttpMethod.POST, entity, String.class).getBody();
    }


}
