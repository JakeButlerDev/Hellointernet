package com.careerdevs.hellointernet.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

// First step is to add the RestController and RequestMapping annotations to the NasaController class
@RestController
@RequestMapping("/nasa")
public class NasaController {

    //Second step is to create a nasaApodEndpoint field within your new class.
    private final String myNasaKey = "HRnjNYo4665YLMXBeNbDzuhhAdZMdWMyhxhSs4QD";

    private final String nasaApodEndpoint = "https://api.nasa.gov/planetary/apod?api_key=" + myNasaKey;

    // Third step is to add a route handler to your code.
    @GetMapping("/apod")
    public Object apodHandler(RestTemplate restTemplate) {
        return restTemplate.getForObject(nasaApodEndpoint, Object.class);
    }

    // Fourth step is to make a request route that allows you to change the date of the APOD information being requested. You’ll need to use either @PathVariable or @RequestParam, either will work.
    @GetMapping("/{date}")
    private void getByDate(@RequestParam String date) {

    }

    // Fourth request to build is a GET request that will return a collection of 5 randomly selected APOD pictures. You’ll need to reference the APOD documentation linked at the top of this page. There’s a specific APOD endpoint you can hit to get a random amount of images.



    //TODO: BONUS - Learn how to store your API Key secretly in the application.properties file. (learn about environment variables)
}
