package com.example.lisaspetitions;

public class Petition {
    private String id;
    private String title;
    private String description;
    private String creatorName;
    private int signatures;

    // Constructor
    public Petition(String id, String title, String description, String creatorName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creatorName = creatorName;
        this.signatures = 0;
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

    public void addSignature() {
        this.signatures++;
    }

    @Override
    public String toString() {
        return "Petition: " + title + " by " + creatorName + " (" + signatures + " signatures)";
    }
}
