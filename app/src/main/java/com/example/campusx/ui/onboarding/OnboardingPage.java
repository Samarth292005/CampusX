package com.example.campusx.ui.onboarding;

public class OnboardingPage {
    private int illustrationRes;
    private String title;
    private String description;

    public OnboardingPage(int illustrationRes, String title, String description) {
        this.illustrationRes = illustrationRes;
        this.title = title;
        this.description = description;
    }

    public int getIllustrationRes() {
        return illustrationRes;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
