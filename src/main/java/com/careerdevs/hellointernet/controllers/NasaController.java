package com.careerdevs.hellointernet.controllers;

import com.careerdevs.hellointernet.models.NasaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

// First step is to add the RestController and RequestMapping annotations to the NasaController class
@RestController
@RequestMapping("/nasa")
public class NasaController {

//    @Value("${myNasaKey}")
//    private String myNasaKey;

    //Second step is to create a nasaApodEndpoint field within your new class.
//    private final String myNasaKey = "HRnjNYo4665YLMXBeNbDzuhhAdZMdWMyhxhSs4QD";
    @Autowired
    private Environment env;

    private final String nasaApodEndpoint = "https://api.nasa.gov/planetary/apod?api_key=";

    // Third step is to add a route handler to your code.
    @GetMapping("/apod")
    public ResponseEntity<?> apodHandler(RestTemplate restTemplate) {
        try {

            String url = nasaApodEndpoint;
            url += env.getProperty("myNasaKey", "DEMO_KEY");
            NasaModel response = restTemplate.getForObject(url, NasaModel.class);
            return ResponseEntity.ok(response);

        } catch (HttpClientErrorException.Forbidden e) {

            return ResponseEntity.status(500).body("Server has no API key or current key is invalid.");

        } catch (Exception e) {

            System.out.println(e.getClass());
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());

        }

    }

    // Fourth step is to make a request route that allows you to change the date of the APOD information being requested. You’ll need to use either @PathVariable or @RequestParam, either will work.
    @GetMapping("/{date}")
    public Object getByDate(RestTemplate restTemplate, @PathVariable String date) {
        try {

            /*
        Date entered cannot be before 1995-06-16
        UNIX time for this is 803275200.
        Convert PathVar to UNIX time, ensure date entered is > 803275200.
        If not, throw error.
         */

            String nasaApodDate = nasaApodEndpoint + env.getProperty("myNasaKey");
            nasaApodDate += "&date=" + date;
            return restTemplate.getForObject(nasaApodDate, NasaModel.class);

        } catch (HttpClientErrorException.NotFound e) {

            return ResponseEntity.status(404).body("Response not found. Please contact support.");

        } catch (HttpClientErrorException.BadRequest e) {

            String rawErr = e.getMessage() != null ? e.getMessage() : "";
            String apiErrorMsg = extractNasaErrorMsg(rawErr);
            return ResponseEntity.badRequest().body("Invalid date provided: " + date + "\n" + apiErrorMsg);

        } catch (HttpClientErrorException.Forbidden e) {

            return ResponseEntity.status(500).body("Server has no API key or current key is invalid.");

        } catch (Exception e) {

            System.out.println(e.getClass());
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());

        }


//        https://reflectoring.io/spring-boot-exception-handling/
    }

    // Fourth request to build is a GET request that will return a collection of 5 randomly selected APOD pictures. You’ll need to reference the APOD documentation linked at the top of this page. There’s a specific APOD endpoint you can hit to get a random amount of images.
    @GetMapping("/randomnumber")
    public Object getRandomCount(RestTemplate restTemplate) {
        int count = 5;
        String randomizeApod = nasaApodEndpoint + env.getProperty("myNasaKey");
        randomizeApod += "&count=" + count;
        return restTemplate.getForObject(randomizeApod, NasaModel.class);
    }


    // Method to help with error handling
    private String extractNasaErrorMsg (String fullErrorMsg) {
        // 400 Bad Request: "{"code": 400, "msg": unconverted data remains: 1", "service_version":"v1"}<EOL>"

//        ArrayList<String> splitErrorMsg =  (ArrayList<String>) Arrays.stream(fullErrorMsg.split("\"")).toList();
//        int msgIndex = splitErrorMsg.indexOf("msg") + 2;
//        return splitErrorMsg.get(msgIndex);
        String[] splitErrorMsg = fullErrorMsg.split("\"");

        return "";

    }

}
