package com.example.lisaspetitions;

import java.util.ArrayList;
import java.util.List;

public class Petition {
    private String id;
    private String title;
    private String description;
    private String creatorName;
    private int signatures;
    private List<String> signers;
    private List<String> emails;

    // Constructor
    public Petition(String id, String title, String description, String creatorName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creatorName = creatorName;
        this.signatures = 0;
        this.signers = new ArrayList<>();
        this.emails = new ArrayList<>();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public int getSignatures() {
        return signatures;
    }

    public List<String> getSigners() {
        return signers;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void addSignature(String signerName, String email) {
        this.signatures++;
        this.signers.add(signerName);
        this.emails.add(email);
    }

    @Override
    public String toString() {
        return "Petition: " + title + " by " + creatorName + " (" + signatures + " signatures)";
    }
}
