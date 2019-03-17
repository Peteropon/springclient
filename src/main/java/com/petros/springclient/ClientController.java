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

    private AnimalRepository animalRepository;

    @Autowired
    public ClientController(RestTemplate restTemplate, AnimalRepository animalRepository) {
        this.restTemplate = restTemplate;
        this.animalRepository = animalRepository;
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
    public void createAnimal(@ApiParam(value = "Store new animal in database table", required = true) @Valid @RequestBody Animal animal){
        HttpEntity<Animal> requestBody = new HttpEntity<>(animal);
//        restTemplate.exchange(URL+"/animals", HttpMethod.POST, requestBody, Animal.class);
//        ResponseEntity<Animal> result
//                = restTemplate.postForEntity(URL+"/animals", requestBody, Animal.class);

        restTemplate.postForEntity(URL+"/animals", requestBody, String.class);
//TODO: check out multivalue map in case it's what we want

//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity<Animal> entity = new HttpEntity<>(animal, headers);
//        return restTemplate.exchange(URL+"/animals", HttpMethod.POST, entity, String.class).getBody();
    }

    @ApiOperation(value = "Delete an animal.")
    @DeleteMapping(path = "/animals/{id}")
    public void deleteAnimal(@PathVariable(value = "id") Long id){

        restTemplate.delete(URL+"/animals/" + id);
        //TODO : add exception
    }


}
