package com.example.gamehub.galgespil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class Galgelogik {
  public static ArrayList<String> muligeOrd = new ArrayList<String>();
  public static Lists allLists = new Lists();
  private ArrayList<String> aktivListe = new ArrayList<String>();
  private ArrayList<String> brugteBogstaver = new ArrayList<String>();
  public static ArrayList<String> brugteOrd = new ArrayList<String>();
  private String ordet;
  private String synligtOrd;
  private int antalForkerteBogstaver;
  private boolean sidsteBogstavVarKorrekt;
  private boolean spilletErVundet;
  private boolean spilletErTabt;
  static int index;

  public ArrayList<String> getBrugteBogstaver() { return brugteBogstaver; }

  public String getSynligtOrd() { return synligtOrd; }

  public String getOrdet() { return ordet; }

  public ArrayList<String> getMuligeOrd() { return muligeOrd; }

  public int getAntalForkerteBogstaver() { return antalForkerteBogstaver; }

  public boolean erSidsteBogstavKorrekt() { return sidsteBogstavVarKorrekt; }

  public boolean erSpilletVundet() { return spilletErVundet; }

  public boolean erSpilletTabt() { return spilletErTabt; }

  public boolean erSpilletSlut() { return spilletErTabt || spilletErVundet; }


  public Galgelogik() {

  }

  public void originaleOrd(){
      brugteOrd.clear();
      muligeOrd = allLists.getOprindeligeOrdList();
  }



  public void nulstil(String ord) {
    brugteBogstaver.clear();
    antalForkerteBogstaver = 0;
    spilletErVundet = false;
    spilletErTabt = false;
    ordet = ord;
    opdaterSynligtOrd();
  }

  private void opdaterSynligtOrd() {
    synligtOrd = "";
    spilletErVundet = true;
    for (int n = 0; n < ordet.length(); n++) {
      String bogstav = ordet.substring(n, n + 1);
      if (brugteBogstaver.contains(bogstav)) {
        synligtOrd = synligtOrd + bogstav;
      } else {
        synligtOrd = synligtOrd + "*";
        spilletErVundet = false;
      }
    }
  }

  public void gætBogstav(String bogstav) {
    if (bogstav.length() != 1) return;
    System.out.println("Der gættes på bogstavet: " + bogstav);
    if (brugteBogstaver.contains(bogstav)) return;
    if (spilletErVundet || spilletErTabt) return;

    brugteBogstaver.add(bogstav);

    if (ordet.contains(bogstav)) {
      sidsteBogstavVarKorrekt = true;
      System.out.println("Bogstavet var korrekt: " + bogstav);
    } else {
      // Vi gættede på et bogstav der ikke var i ordet.
      sidsteBogstavVarKorrekt = false;
      System.out.println("Bogstavet var IKKE korrekt: " + bogstav);
      antalForkerteBogstaver = antalForkerteBogstaver + 1;
      if (antalForkerteBogstaver > 6) {
        spilletErTabt = true;
      }
    }
    opdaterSynligtOrd();
  }

  public static String hentUrl(String url) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
    StringBuilder sb = new StringBuilder();
    String linje = br.readLine();
    while (linje != null) {
      sb.append(linje + "\n");
      linje = br.readLine();
    }
    return sb.toString();
  }

  public void hentOrdFraDr() throws Exception {
    String data = hentUrl("http://dr.dk");

    data = data.substring(data.indexOf("<body")). // fjern headere
            replaceAll("<.+?>", " ").toLowerCase(). // fjern tags
            replaceAll("[^a-zæøå]", " "). // fjern tegn der ikke er bogstaver
            replaceAll(" [a-zæøå] "," "). // fjern 1-bogstavsord
            replaceAll(" [a-zæøå][a-zæøå] "," "); // fjern 2-bogstavsord

    System.out.println("data = " + data);
    muligeOrd.clear();
    muligeOrd.addAll(new HashSet<String>(Arrays.asList(data.split(" "))));

    System.out.println("muligeOrd = " + muligeOrd);
  }
}
