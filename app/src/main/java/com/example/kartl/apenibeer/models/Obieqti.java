package com.example.kartl.apenibeer.models;

/**
 * Created by kartl on 02.10.2017.
 */

public class Obieqti {
    String dasaxeleba, jgufi, adress, tel, comment;

    public Obieqti(String dasaxeleba, String jgufi) {
        this.dasaxeleba = dasaxeleba;
        this.jgufi = jgufi;
    }

    public String getDasaxeleba() {
        return dasaxeleba;
    }

    public void setDasaxeleba(String dasaxeleba) {
        this.dasaxeleba = dasaxeleba;
    }

    public String getJgufi() {
        return jgufi;
    }

    public void setJgufi(String jgufi) {
        this.jgufi = jgufi;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
