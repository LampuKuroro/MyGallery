package com.example.mygallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import static com.example.mygallery.AllVariables.CHOOSEN;
import static com.example.mygallery.AllVariables.EXTRA_MESSAGE;
import static com.example.mygallery.AllVariables.FLIP;
import static com.example.mygallery.AllVariables.GETNAMES;
import static com.example.mygallery.AllVariables.IMGID;
import static com.example.mygallery.AllVariables.NAMES;
import static com.example.mygallery.AllVariables.SHOWNAMES;
import static com.example.mygallery.AllVariables.TINT;
import static com.example.mygallery.AllVariables.total;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            names= extras.getStringArray(NAMES);
            for(int i=0;i<total;i++) {
                TextView n1 = findViewById(SHOWNAMES[i]);
                n1.setText(names[i]);
            }
            int[] flip= extras.getIntArray(FLIP);
            int[] tint= extras.getIntArray(TINT);
            for(int i=0;i<total;i++){
                ImageView ava = findViewById(IMGID[i]);
                switch(flip[i]){
                    case 1:
                        ava.setScaleX(-1f);
                        break;
                    case 2:
                        ava.setScaleY(-1f);
                        break;
                    case 3:
                        ava.setScaleX(-1f);
                        ava.setScaleY(-1f);
                        break;
                }
                switch(tint[i]){
                    case 1:
                        ava.setColorFilter(Color.argb(32, 232, 30, 30));
                        break;
                    case 2:
                        ava.setColorFilter(Color.argb(32, 30, 30, 232));
                        break;
                    case 3:
                        ava.setColorFilter(Color.argb(32, 232, 232, 30));
                        break;
                }
            }


        }
        else {
            for(int i=0;i<total;i++) {
            names[i]=getString(GETNAMES[i]);
            }
        }



    }

    String[] names= new String[total];
    String chooseMessage="";
    int choosen=-1;
    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }


    public void showChoosen(View view) {
        for( int i=0;i<total; i++){
            if(view.getId()==IMGID[i]){
                choosen=i;
            }
        }

        chooseMessage = names[choosen];
        displayToast(chooseMessage + " is choosen!");
    }

    public void onClick(View view) {
        if(choosen==-1){
            displayToast("You haven't choose any image!");
        }else{
            Intent oldintent = getIntent();
            Bundle oldextras = oldintent.getExtras();
            Bundle extras = new Bundle();
            if(oldextras != null){
                extras = oldextras;
                extras.putInt(CHOOSEN,choosen);
                extras.putString(EXTRA_MESSAGE, chooseMessage);
            }
            else{
                int[] arr=new int[total];
                Arrays.fill(arr,0);
                extras.putInt(CHOOSEN,choosen);
                extras.putIntArray(TINT,arr);
                extras.putIntArray(FLIP,arr);
                extras.putStringArray(NAMES,names);
                extras.putString(EXTRA_MESSAGE, chooseMessage);
            }

            Intent intent = new Intent(MainActivity.this, openMenu.class);
            intent.putExtras(extras);
            startActivity(intent);
        }

    }

}