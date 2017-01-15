package com.example.gamehub.galgespil;


import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AllWords_frag extends Fragment implements AdapterView.OnItemClickListener {


    View myView;
    ListView lv;
    ArrayList<String> words;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_wordlist, container, false);


        words = StartMenu.galge.getMuligeOrd();
        lv = (ListView) myView.findViewById(R.id.listview);
        ArrayAdapter adapter = new ArrayAdapter(this.getContext(),android.R.layout.simple_list_item_1, android.R.id.text1, words);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);

        return myView;
    }

    public AllWords_frag(){

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Bundle bundle = new Bundle();
        bundle.putString("ordet",words.get(i).toString());
        System.out.println(words.get(i));
        Main_keyboard_frag nextFrag = new Main_keyboard_frag();
        nextFrag.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.fragment_main, nextFrag).commit();
    }
}
