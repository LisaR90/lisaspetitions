package com.example.lisaspetitions;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PetitionController {
    private List<Petition> petitions = new ArrayList<>();

    // Sample data for testing
    public PetitionController() {
        petitions.add(new Petition("1", "Save the Forest", "Help protect our forests!", "Lisa"));
        petitions.add(new Petition("2", "Clean the Oceans", "Reduce plastic waste in oceans", "Steve"));
        petitions.add(new Petition("3", "Animal Rights", "Support animal protection laws", "Lily"));
        petitions.add(new Petition("4", "Increase Education Funding", "Demand better funding for schools, teachers, and students to improve education quality", "Bob"));
        petitions.add(new Petition("5", "Stop Cyberbullying!", "Help us stop cyberbullying today", "Billy"));
        petitions.add(new Petition("6", "Stop Polluting", "Help stop pollution now", "Bobby"));
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

    @PostMapping("/create")
    public String createPetition(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String creatorName) {
        String id = String.valueOf(petitions.size() + 1);
        petitions.add(new Petition(id, title, description, creatorName));
        return "redirect:/petitions";
    }

    // View a specific petition and sign it
    @GetMapping("/petition")
    public String viewPetition(@RequestParam String id, Model model) {
        Optional<Petition> petitionOpt = petitions.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        if (petitionOpt.isPresent()) {
            model.addAttribute("petition", petitionOpt.get());
        } else {
            model.addAttribute("errorMessage", "Petition not found");
            return "error"; // Add an error page if the petition is not found
        }
        return "view-petition";
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

    @PostMapping("/sign")
    public String signPetition(@RequestParam String id,
                               @RequestParam String name,
                               @RequestParam String email) {
        Petition petition = petitions.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (petition != null) {
            petition.addSignature(name, email);
        }
        return "redirect:/petitions";
    }
}
