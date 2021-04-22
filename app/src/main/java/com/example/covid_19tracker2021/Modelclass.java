package com.example.covid_19tracker2021;

public class Modelclass {

    private String districtname;
    private int totalcases;
    private int recoveredcases;
    private int activecases;
    private int deathcases;

    Modelclass(){

    }

    public Modelclass( String districtname, int totalcases, int recoveredcases, int activecases, int deathcases) {

        this.districtname = districtname;
        this.totalcases = totalcases;
        this.recoveredcases = recoveredcases;
        this.activecases = activecases;
        this.deathcases = deathcases;
    }



    public String getDistrictname() {
        return districtname;
    }

    public int getTotalcases() {
        return totalcases;
    }

    public int getRecoveredcases() {
        return recoveredcases;
    }

    public int getActivecases() {
        return activecases;
    }

    public int getDeathcases() {
        return deathcases;
    }
}
