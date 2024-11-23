package com.example.lisaspetitions;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
public class PetitionController {
    private List<Petition> petitions = new ArrayList<>();

    // Sample data for testing
    public PetitionController() {
        petitions.add(new Petition("1", "Save the Forest", "Help protect our forests!", "Lisa"));
        petitions.add(new Petition("2", "Clean the Oceans", "Reduce plastic waste in oceans", "Steve"));
        petitions.add(new Petition("3", "Animal Rights", "Support animal protection laws", "Lily"));
    }

    // View all petitions
    @GetMapping("/petitions")
    public String viewAllPetitions(Model model) {
        model.addAttribute("petitions", petitions);
        return "petitions.html"; // Explicitly specify the file extension
    }


    // Create a new petition page
    @GetMapping("/create")
    public String createPetitionPage() {
        return "create";
    }

    // Search for petitions page
    @GetMapping("/search")
    public String searchPage() {
        return "search";
    }

    @PostMapping("/search")
    public String searchPetitions(@RequestParam String keyword, Model model) {
        List<Petition> searchResults = petitions.stream()
                .filter(p -> p.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                        p.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .toList();

        model.addAttribute("searchResults", searchResults);
        model.addAttribute("keyword", keyword);
        return "search-results";
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