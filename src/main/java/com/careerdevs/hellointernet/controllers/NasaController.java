package com.careerdevs.hellointernet.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

// First step is to add the RestController and RequestMapping annotations to the NasaController class
@RestController
@RequestMapping("/nasa")
public class NasaController {

    @Value("${myNasaKey}")
    private String myNasaKey;

    //Second step is to create a nasaApodEndpoint field within your new class.
//    private final String myNasaKey = "HRnjNYo4665YLMXBeNbDzuhhAdZMdWMyhxhSs4QD";

    private final String nasaApodEndpoint = "https://api.nasa.gov/planetary/apod?api_key=" + myNasaKey;

    // Third step is to add a route handler to your code.
    @GetMapping("/apod")
    public Object apodHandler(RestTemplate restTemplate) {
        return restTemplate.getForObject(nasaApodEndpoint, Object.class);
    }

    // Fourth step is to make a request route that allows you to change the date of the APOD information being requested. You’ll need to use either @PathVariable or @RequestParam, either will work.
    @GetMapping("/{date}")
    public Object getByDate(RestTemplate restTemplate, @PathVariable String date) {
        try {
            String nasaApodDate = nasaApodEndpoint;
            nasaApodDate += "&date=" + date;
            return restTemplate.getForObject(nasaApodDate, Object.class);
        } catch (Exception exception) {
            return "Issue with date. Investigate.";
        }


//        https://reflectoring.io/spring-boot-exception-handling/
    }

    // Fourth request to build is a GET request that will return a collection of 5 randomly selected APOD pictures. You’ll need to reference the APOD documentation linked at the top of this page. There’s a specific APOD endpoint you can hit to get a random amount of images.
    @GetMapping("/randomnumber")
    public Object getRandomCount(RestTemplate restTemplate) {
        int count = 5;
        String randomizeApod = nasaApodEndpoint;
        randomizeApod += "&count=" + count;
        return restTemplate.getForObject(randomizeApod, Object.class);
    }

    //TODO: Error handling - 400 bad request when user enters invalid date, FURTHER - print to client with msg provided from server

    //TODO: BONUS - Learn how to store your API Key secretly in the application.properties file. (learn about environment variables)

    // Can try to hide in application.properties, if unable to work go for .gitignore
}
