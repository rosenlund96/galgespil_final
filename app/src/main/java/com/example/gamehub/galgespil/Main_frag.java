package com.example.gamehub.galgespil;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main_frag extends Fragment implements View.OnClickListener {

    public static Galgelogik galgeMain;
    public static int points, finalScore;
    TextView syndligtOrd, brugteBogstaver, besked, gætord;
    EditText bogstav, score;
    Button ok, showWords;
    ImageView image;
    Fragment fragmentEnd = new GameEnd_frag();

    private int[] fejlBilleder = new int[]{
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

    public Main_frag() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        galgeMain = StartMenu.galge;
        if (galgeMain.erSpilletSlut()) galgeMain = new Galgelogik();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_main_frag, container, false);

        syndligtOrd = (TextView) root.findViewById(R.id.synligtOrd);
        brugteBogstaver = (TextView) root.findViewById(R.id.brugteBogstaver);
        brugteBogstaver.setText("Brugte bogstaver: " + galgeMain.getBrugteBogstaver());
        besked = (TextView) root.findViewById(R.id.besked);
        gætord = (TextView) root.findViewById(R.id.gætOrd);
        bogstav = (EditText) root.findViewById(R.id.editText);
        score = (EditText) root.findViewById(R.id.textScore);
        image = (ImageView) root.findViewById(R.id.galgeImage);
        ok = (Button) root.findViewById(R.id.button);
        showWords = (Button) root.findViewById(R.id.showWords);

        ok.setOnClickListener(this);
        showWords.setOnClickListener(this);

        syndligtOrd.setText(galgeMain.getSynligtOrd());
        gætord.setText("Gæt ordet \nmed " + syndligtOrd.length() + " bogstaver.");
        return root;
    }

    @Override
    public void onClick(View v) {
        if(v==ok) {

            String gættetBogstav = bogstav.getText().toString().toLowerCase();
            String trykketBogstav = bogstav.getText().toString().toLowerCase();

            if (gættetBogstav.length() != 1) {
                Toast.makeText(getActivity(), "Kun et bogstav af gangen", Toast.LENGTH_LONG).show();
                bogstav.setText("");
                return;

            }else if(!trykketBogstav.matches("[a-zA-Z]+")) {
                Toast.makeText(getActivity(), "Indtast kun Latin bogstaver og ikke tegn", Toast.LENGTH_LONG).show();
                bogstav.setText("");

            }
            if(trykketBogstav.matches("[a-zA-Z]+")) {
                galgeMain.gætBogstav(gættetBogstav);
            }

            if(galgeMain.erSidsteBogstavKorrekt()){
                points= points+1;
                score.setText(String.valueOf(points));
            }
            brugteBogstaver.setText("Brugte bogstaver:\n" + galgeMain.getBrugteBogstaver());
            syndligtOrd.setText(galgeMain.getSynligtOrd());


            if (!galgeMain.erSpilletSlut() && trykketBogstav.matches("[a-zA-Z]+") ) {
                besked.setText(fejlBesked[galgeMain.getAntalForkerteBogstaver()]);
                if (!galgeMain.erSidsteBogstavKorrekt() && galgeMain.getAntalForkerteBogstaver() < 7) {
                    image.setImageResource(fejlBilleder[galgeMain.getAntalForkerteBogstaver() - 1]);
                }
            }
            bogstav.setText("");
        }
        if(v==showWords){
           startActivity(new Intent(getActivity(), AllWords_frag.class));
        }
        if (galgeMain.erSpilletSlut()) {
           getFragmentManager().beginTransaction().replace(R.id.fragment_main, fragmentEnd).commit();
            finalScore = points;
            points = 0;
        }

    }

}
