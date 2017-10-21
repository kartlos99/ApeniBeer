package com.example.kartl.apenibeer.utils;

import java.util.ArrayList;
import java.util.List;

import com.example.kartl.apenibeer.models.BeerType;
import com.example.kartl.apenibeer.models.KasriType;

/**
 * Created by kartl on 08.10.2017.
 */

public class Constantebi {
//    static public List<String> kasriTypeList = new ArrayList<>();

    public static String URL_GET_OBIEQTS = "http://apeni.ge/andr_app_links/get_obieqts.php";
    public static String URL_GET_KASRILIST = "http://apeni.ge/andr_app_links/get_kasri_list.php";
    public static String URL_GET_LUDILIST = "http://apeni.ge/andr_app_links/get_ludi_list.php";

    public static ArrayList<KasriType> kasriTypeList = new ArrayList<>();
    public static ArrayList<BeerType> beerTypeList = new ArrayList<>();

}
