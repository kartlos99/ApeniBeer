package com.example.kartl.apenibeer.models;

/**
 * Created by kartl on 14.10.2017.
 */

public class KasriType {
    private String dasaxeleba;
    private int litraji;

    public KasriType(String dasaxeleba, int litraji) {
        this.dasaxeleba = dasaxeleba;
        this.litraji = litraji;
    }

    public String getDasaxeleba() {
        return dasaxeleba;
    }

    public void setDasaxeleba(String dasaxeleba) {
        this.dasaxeleba = dasaxeleba;
    }

    public int getLitraji() {
        return litraji;
    }

    public void setLitraji(int litraji) {
        this.litraji = litraji;
    }
}
