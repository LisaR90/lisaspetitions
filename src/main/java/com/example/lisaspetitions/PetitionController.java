package com.example.lisaspetitions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
public class PetitionController {
    private List<Petition> petitions = new ArrayList<>();

    // Create a new petition page
    @GetMapping("/create")
    public String createPetitionPage() {
        return "create";
    }

    @PostMapping("/create")
    public String createPetition(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String creatorName) {
        String id = String.valueOf(petitions.size() + 1);
        petitions.add(new Petition(id, title, description, creatorName));
        return "redirect:/petitions";
    }

}