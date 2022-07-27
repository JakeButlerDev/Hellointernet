package com.careerdevs.hellointernet.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

@RestController
public class RootController {

    @GetMapping("/")
    private String helloCareerDevs() {
        return "You requested the root route";
    }

    @GetMapping("/hello")
    private String helloRoute() {
        return "Hello!";
    }

    @GetMapping("/moviequote")
    private String printMovieQuote() {
        ArrayList<String> movieQuotes = new ArrayList<>();
        Random rand = new Random();

        movieQuotes.add("Say hello to my little friend!");
        movieQuotes.add("We're not in Kansas anymore.");
        movieQuotes.add("Oh captain, my captain.");
        movieQuotes.add("We're gonna need a bigger boat");
        movieQuotes.add("Hold onto your butts.");

        int index = rand.nextInt(movieQuotes.size());
        return movieQuotes.get(index);
    }

    @GetMapping("random")
    private Integer randomInt() {
        Random rand = new Random();
        int myNumber = rand.nextInt(100);
        return myNumber;
    }

    @GetMapping("joke")
    private String jokeTime() {
        ArrayList<String> jokes = new ArrayList<>();
        Random rand = new Random();

        // NEW LINE CHARACTERS NOT APPLIED??
        jokes.add("What did the fish say when he swam into a wall?\nDamn.");
        jokes.add("What do you call a fish with no eyes?\nA fsh.");
        jokes.add("What do you get when you combine a rhetorical question and a joke?\n...");
        jokes.add("Did you hear about the Italian chef who died?\nHe pasta-way.");
        jokes.add("Two guys walk into a bar.\nThe third guy ducks.");
        jokes.add("What do you call a fake noodle?\nAn impasta.");
        jokes.add("Did you hear about the claustrophobic astronaut?\nHe just needed some space.");

        int index = rand.nextInt(jokes.size());
        return jokes.get(index);
    }

    @GetMapping("customized")
    private String requestParamPath(@RequestParam String myName) {
        return "Hello " + myName + "!       :)";
    }

    // Create a path variable GET request
    @GetMapping("pathvar/{name}")
    private String pathVariablePath(@PathVariable String name) {
        return "Why are you here " + name + "?";
    }
}