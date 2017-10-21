package com.example.kartl.apenibeer.models;

/**
 * Created by kartl on 14.10.2017.
 */

public class BeerType {
    private String dasaxeleba;
    private double fasi;

    public BeerType(String dasaxeleba, double fasi) {
        this.dasaxeleba = dasaxeleba;
        this.fasi = fasi;
    }

    public String getDasaxeleba() {
        return dasaxeleba;
    }

    public void setDasaxeleba(String dasaxeleba) {
        this.dasaxeleba = dasaxeleba;
    }

    public double getFasi() {
        return fasi;
    }

    public void setFasi(double fasi) {
        this.fasi = fasi;
    }
}
