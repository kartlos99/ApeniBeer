package com.example.kartl.apenibeer.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kartl on 10.10.2017.
 */

public class Jgufi {
    private String name;
    private ArrayList<Obieqti> childs ;

    public Jgufi(String name, ArrayList<Obieqti> childs) {
        this.name = name;
        this.childs = childs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Obieqti> getChilds() {
        return childs;
    }

    public void setChilds(ArrayList<Obieqti> childs) {
        this.childs = childs;
    }
}
