package com.polling.app.model;

import java.util.Map;

public class PollResultDTO {
    private Long pollId;
    private String question;
    private Map<String, Double> optionPercentages;

    public Long getPollId() {
        return pollId;
    }

    public void setPollId(Long pollId) {
        this.pollId = pollId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Map<String, Double> getOptionPercentages() {
        return optionPercentages;
    }

    public void setOptionPercentages(Map<String, Double> optionPercentages) {
        this.optionPercentages = optionPercentages;
    }
}
