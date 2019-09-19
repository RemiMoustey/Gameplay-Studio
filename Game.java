package com.cursan.gameplay_studio;

abstract public class Game {
    private String combination;
    private String proposition;
    private String response;

    public String getCombination() {
        return combination;
    }

    public String getProposition() {
        return proposition;
    }

    public String getResponse() {
        return response;
    }

    public void setCombination(String combination) {
        this.combination = combination;
    }

    public void setProposition(String proposition) {
        this.proposition = proposition;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
