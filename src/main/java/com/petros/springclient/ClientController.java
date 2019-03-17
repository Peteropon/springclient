package com.petros.springclient;

import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@RestController
@Api(value = "Animal Database Management", description = "Operations regarding the management of an animal database.")
public class ClientController {

    static final Logger log = LoggerFactory.getLogger(SpringclientApplication.class);
    static String URL = "http://petros.us-east-2.elasticbeanstalk.com";

    private final
    RestTemplate restTemplate;


    @Autowired
    public ClientController(RestTemplate restTemplate ) {
        this.restTemplate = restTemplate;
    }

    @ApiOperation(value = "View a list of all available animals", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(path = "/animals")
    public ResponseEntity<String> getAllAnimals(){
        log.info("Request for all animals.");
        return restTemplate.exchange(URL+"/animals", HttpMethod.GET, null, String.class);
    }

    @ApiOperation(value = "Enter an id number in order to find an animal.")
    @GetMapping("/animals/{id}")
    public ResponseEntity<String> getAnimalById(@PathVariable(value = "id") Long id){
        log.info("Request for animal with id: " + id);
        return  restTemplate.exchange(URL+"/animals/"+ id, HttpMethod.GET, null, String.class);
    }


    @ApiOperation(value = "Get a randomly chosen animal from the database.")
    @GetMapping(path = "/randomanimal")
    public String getRandomAnimal(){
        log.info("Request for a random animal.");
        ResponseEntity<String> re = restTemplate.exchange(URL+"/randomanimal", HttpMethod.GET, null, String.class);
        return re.getBody();
    }

    @ApiOperation(value = "Add a new animal.")
    @PostMapping("/animals")
    public void createAnimal(@ApiParam(value = "Insert the info of the animal", required = true) @Valid @RequestBody Animal animal){
        HttpEntity<Animal> requestBody = new HttpEntity<>(animal);
        restTemplate.postForEntity(URL+"/animals", requestBody, String.class);
    }

    @ApiOperation(value = "Update an existing animal by entering its id.")
    @PutMapping("/animals/{id}")
    public void updateAnimal(@ApiParam(value = "Insert id here", required = true) @PathVariable(value = "id") Long id, @Valid @RequestBody Animal animal){
        HttpEntity<Animal> requestBody = new HttpEntity<>(animal);
        //restTemplate.exchange(URL+"/animals/"+ id, HttpMethod.PUT, null, String.class);
        restTemplate.put(URL+"/animals/"+ id, requestBody);
    }

    @ApiOperation(value = "Delete an animal.")
    @DeleteMapping(path = "/animals/{id}")
    public void deleteAnimal(@PathVariable(value = "id") Long id){

        restTemplate.delete(URL+"/animals/" + id);
        //TODO : add exception
    }


}
