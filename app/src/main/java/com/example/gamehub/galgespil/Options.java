package com.example.gamehub.galgespil;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.CheckBox;

public class Options extends FragmentActivity implements View.OnClickListener{

    CheckBox checkBoxOriginal, checkBoxLayoutWrite, checkBoxLayoutKeyboard;
    static boolean booleanOriginal = true, booleanPokemon, booleanLayoutWrite, booleanLayoutKeyboard = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.indstillinger_layout);

        checkBoxOriginal = (CheckBox) findViewById(R.id.checkBoxOriginal);
        checkBoxLayoutWrite = (CheckBox) findViewById(R.id.checkBoxLayoutWrite);
        checkBoxLayoutKeyboard = (CheckBox) findViewById(R.id.checkBoxLayoutKeyboard);

        if(booleanOriginal) checkBoxOriginal.setChecked(true);
        if(booleanLayoutWrite) checkBoxLayoutWrite.setChecked(true);
        if(booleanLayoutKeyboard) checkBoxLayoutKeyboard.setChecked(true);

        checkBoxOriginal.setOnClickListener(this);
        checkBoxLayoutWrite.setOnClickListener(this);
        checkBoxLayoutKeyboard.setOnClickListener(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        if(checkBoxOriginal.isChecked()) checkBoxOriginal.setClickable(false);
        if(checkBoxLayoutWrite.isChecked()) checkBoxLayoutWrite.setClickable(false);
        if(checkBoxLayoutKeyboard.isChecked()) checkBoxLayoutKeyboard.setClickable(false);
    }


    @Override
    public void onClick(View v) {
        if(v==checkBoxOriginal){
            checkBoxOriginal.setClickable(false);
            StartMenu.galge.originaleOrd();
        }
        if(v==checkBoxLayoutWrite) {
            checkBoxLayoutKeyboard.setChecked(false);
            checkBoxLayoutWrite.setClickable(false);
            checkBoxLayoutKeyboard.setClickable(true);
        }
        if(v==checkBoxLayoutKeyboard){
            checkBoxLayoutWrite.setChecked(false);
            checkBoxLayoutKeyboard.setClickable(false);
            checkBoxLayoutWrite.setClickable(true);
        }
    }
}
