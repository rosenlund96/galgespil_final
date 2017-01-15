package com.example.gamehub.galgespil;

import android.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Mathias Larsen on 14-01-2017.
 */
public class Main_keyboard_frag extends Fragment implements View.OnClickListener {

    public static Galgelogik galgeMain;
    public static int points, finalScore;
    TextView syndligtOrd, besked, gætord, score;
    Button letterA,letterB,letterC,letterD,letterE,letterF,letterG,letterH,
           letterI,letterJ,letterK,letterL,letterM,letterN,letterO,letterP,
           letterQ,letterR,letterS,letterT,letterU,letterV,letterW,letterX,
           letterY,letterZ,letterÆ,letterØ,letterÅ;
    ImageView image;
    Bundle bundle;
    String temp;




    private int[] fejlBilleder = new int[]{
            R.mipmap.galge,
            R.mipmap.forkert1,
            R.mipmap.forkert2,
            R.mipmap.forkert3,
            R.mipmap.forkert4,
            R.mipmap.forkert5,
            R.mipmap.forkert6,
    };

    private String[] fejlBesked = new String[]{
            "Syv forsøg tilbage",
            "Seks forsøg tilbage",
            "Fem forsøg tilbage",
            "Fire forsøg tilbage",
            "Tre forsøg tilbage",
            "To forsøg tilbage",
            "Sidste forsøg tilbage",
            ""
    };

    public Main_keyboard_frag() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        galgeMain = StartMenu.galge;
        bundle = getArguments();
        System.out.println(bundle);
        temp = bundle.getString("ordet");
        StartMenu.galge.nulstil(temp);



    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {

        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_main_keyboard_frag, container, false);

        syndligtOrd = (TextView) root.findViewById(R.id.synligtOrd);
        besked = (TextView) root.findViewById(R.id.besked);
        gætord = (TextView) root.findViewById(R.id.gætOrd);
        score = (TextView) root.findViewById(R.id.scoreView);
        image = (ImageView) root.findViewById(R.id.galgeImage);

        letterA = (Button) root.findViewById(R.id.letterA); letterB = (Button) root.findViewById(R.id.letterB);
        letterC = (Button) root.findViewById(R.id.letterC); letterD = (Button) root.findViewById(R.id.letterD);
        letterE = (Button) root.findViewById(R.id.letterE); letterF = (Button) root.findViewById(R.id.letterF);
        letterG = (Button) root.findViewById(R.id.letterG); letterH = (Button) root.findViewById(R.id.letterH);
        letterI = (Button) root.findViewById(R.id.letterI); letterJ = (Button) root.findViewById(R.id.letterJ);
        letterK = (Button) root.findViewById(R.id.letterK); letterL = (Button) root.findViewById(R.id.letterL);
        letterM = (Button) root.findViewById(R.id.letterM); letterN = (Button) root.findViewById(R.id.letterN);
        letterO = (Button) root.findViewById(R.id.letterO); letterP = (Button) root.findViewById(R.id.letterP);
        letterQ = (Button) root.findViewById(R.id.letterQ); letterR = (Button) root.findViewById(R.id.letterR);
        letterS = (Button) root.findViewById(R.id.letterS); letterT = (Button) root.findViewById(R.id.letterT);
        letterU = (Button) root.findViewById(R.id.letterU); letterV = (Button) root.findViewById(R.id.letterV);
        letterW = (Button) root.findViewById(R.id.letterW); letterX = (Button) root.findViewById(R.id.letterX);
        letterY = (Button) root.findViewById(R.id.letterY); letterZ = (Button) root.findViewById(R.id.letterZ);
        letterÆ = (Button) root.findViewById(R.id.letterÆ); letterØ = (Button) root.findViewById(R.id.letterØ);
        letterÅ = (Button) root.findViewById(R.id.letterÅ);

        letterA.setOnClickListener(this); letterB.setOnClickListener(this); letterC.setOnClickListener(this);
        letterD.setOnClickListener(this); letterE.setOnClickListener(this); letterF.setOnClickListener(this);
        letterG.setOnClickListener(this); letterH.setOnClickListener(this); letterI.setOnClickListener(this);
        letterJ.setOnClickListener(this); letterK.setOnClickListener(this); letterL.setOnClickListener(this);
        letterM.setOnClickListener(this); letterN.setOnClickListener(this); letterO.setOnClickListener(this);
        letterP.setOnClickListener(this); letterQ.setOnClickListener(this); letterR.setOnClickListener(this);
        letterS.setOnClickListener(this); letterT.setOnClickListener(this); letterU.setOnClickListener(this);
        letterV.setOnClickListener(this); letterW.setOnClickListener(this); letterX.setOnClickListener(this);
        letterY.setOnClickListener(this); letterZ.setOnClickListener(this); letterÆ.setOnClickListener(this);
        letterØ.setOnClickListener(this); letterÅ.setOnClickListener(this);



        syndligtOrd.setText(galgeMain.getSynligtOrd());
        gætord.setText("Gæt ordet \nmed " + syndligtOrd.length() + " bogstaver.");
        image.setImageResource(fejlBilleder[galgeMain.getAntalForkerteBogstaver()]);

        return root;
    }

    @Override
    public void onClick(View v) {
        String temp = v.toString();
        String chosenLetter = temp.substring(temp.length()-2, temp.length()-1).toLowerCase();
        v.setClickable(false);
        galgeMain.gætBogstav(chosenLetter);
        if(galgeMain.erSidsteBogstavKorrekt()) v.setBackgroundColor(Color.GREEN);
        else v.setBackgroundColor(Color.RED);


        if(galgeMain.erSidsteBogstavKorrekt()){
            points= points+1;
            score.setText(String.valueOf(points));
        }
        syndligtOrd.setText(galgeMain.getSynligtOrd());

        if (!galgeMain.erSpilletSlut()) {
            besked.setText(fejlBesked[galgeMain.getAntalForkerteBogstaver()]);
            if (!galgeMain.erSidsteBogstavKorrekt() && galgeMain.getAntalForkerteBogstaver() < 7) {
                image.setImageResource(fejlBilleder[galgeMain.getAntalForkerteBogstaver()]);
            }
        }
        if (galgeMain.erSpilletSlut()) {
            getFragmentManager().beginTransaction().replace(R.id.fragment_main, new GameEnd_frag()).commit();
            finalScore = points;
            points = 0;
        }
    }
}
