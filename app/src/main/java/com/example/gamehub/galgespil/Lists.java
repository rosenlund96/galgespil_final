package com.example.gamehub.galgespil;

import java.util.ArrayList;

/**
 * Created by Mathias Larsen on 14-01-2017.
 */
public class Lists {

    private ArrayList<String> oprindeligeOrd = new ArrayList<>();



    public Lists() {
        oprindeligeOrd.add("bil");
        oprindeligeOrd.add("computer");
        oprindeligeOrd.add("programmering");
        oprindeligeOrd.add("motorvej");
        oprindeligeOrd.add("busrute");
        oprindeligeOrd.add("gangsti");
        oprindeligeOrd.add("skovsnegl");
        oprindeligeOrd.add("solsort");
        oprindeligeOrd.add("søko");
        oprindeligeOrd.add("lysekrone");
        oprindeligeOrd.add("ært");
        oprindeligeOrd.add("pålæg");
        oprindeligeOrd.add("blyant");
        oprindeligeOrd.add("playstation");
        oprindeligeOrd.add("xbox");
        oprindeligeOrd.add("nintendo");



    }

    public ArrayList getOprindeligeOrdList(){ Options.booleanPokemon = false; Options.booleanOriginal = true; return oprindeligeOrd; }
}
