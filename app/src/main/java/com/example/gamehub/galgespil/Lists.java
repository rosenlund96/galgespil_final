package com.example.gamehub.galgespil;

import java.util.ArrayList;

/**
 * Created by Daniel Bordig on 16-02-2016.
 */
public class Lists {

    private ArrayList<String> oprindeligeOrd = new ArrayList<>();

    private ArrayList<Integer> pokemonPictures = new ArrayList<>();


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
    public ArrayList getPokemonPictures(){ return pokemonPictures; };
}
