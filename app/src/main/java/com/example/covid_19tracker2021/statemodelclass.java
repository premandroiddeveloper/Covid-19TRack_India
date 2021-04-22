package com.example.covid_19tracker2021;

public class statemodelclass {
    public String states;
    public String key;

    public statemodelclass(String states,String key) {
        this.states = states;
        this.key=key;

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }
}
