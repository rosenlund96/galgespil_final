package com.example.gamehub.galgespil;

import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameEnd_frag extends Fragment implements View.OnClickListener {

    TextView endMessage;
    ImageView endImage;
    Button newWords,newGame;
    private ArrayList<String> ordFraDr = new ArrayList<String>();



    public GameEnd_frag() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_game_end_frag_org, container, false);

        endMessage = (TextView) root.findViewById(R.id.endMessage);
        endImage = (ImageView) root.findViewById(R.id.imageView2);
        newWords = (Button) root.findViewById(R.id.newWords);
        newGame = (Button) root.findViewById(R.id.newGame);

        newWords.setOnClickListener(this);
        newGame.setOnClickListener(this);


        if (Main_keyboard_frag.galgeMain.erSpilletTabt()) {
            endMessage.setText("Du har tabt. Det rigtige ord var " + Main_keyboard_frag.galgeMain.getOrdet());
            endImage.setImageResource(R.mipmap.dead);
        }
        if (Main_keyboard_frag.galgeMain.erSpilletVundet()){
            endMessage.setText("Du har vundet. Din score blev: " + Main_keyboard_frag.finalScore);

            endImage.setImageResource(R.mipmap.yes);
            Galgelogik.brugteOrd.add(Galgelogik.muligeOrd.get(Galgelogik.index));
            Galgelogik.muligeOrd.remove(Galgelogik.index);
        }

        if(Galgelogik.muligeOrd.size()==0){
            Galgelogik.muligeOrd.addAll(Galgelogik.brugteOrd);
            Galgelogik.brugteOrd.clear();
        }

        return root;
    }

    @Override
    public void onClick(View v) {
        if(v==newWords){
            new AsyncTask(){
                @Override
                protected Object doInBackground(Object... arg0) {
                    try {
                        StartMenu.galge.hentOrdFraDr();
                        return "Nye ord blev hentet korrekt fra serveren";
                    } catch (Exception e) {
                        e.printStackTrace();
                        return "Ordene blev ikke hentet korrekt: "+e;
                    }
                }
                @Override
                protected void onPostExecute(Object resultat) {
                    Toast.makeText(getActivity(),resultat+"",Toast.LENGTH_SHORT).show();
                    ordFraDr.add(resultat.toString());
                }
            }.execute();
        }
        if(v==newGame)getFragmentManager().beginTransaction().replace(R.id.fragment_main, new AllWords_frag()).commit();
    }

}
