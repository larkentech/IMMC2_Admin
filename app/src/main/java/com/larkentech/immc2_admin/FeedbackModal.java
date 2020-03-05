package com.larkentech.immc2_admin;

public class FeedbackModal {

    private String Feedback;
    private String Feeling;


    public FeedbackModal() {
    }

    public FeedbackModal(String feedback, String feeling) {
        Feedback = feedback;
        Feeling = feeling;
    }

    public String getFeedback() {
        return Feedback;
    }

    public void setFeedback(String feedback) {
        Feedback = feedback;
    }

    public String getFeeling() {
        return Feeling;
    }

    public void setFeeling(String feeling) {
        Feeling = feeling;
    }
}
