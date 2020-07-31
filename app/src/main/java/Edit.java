package com.example.mygallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.mygallery.AllVariables.CHOOSEN;
import static com.example.mygallery.AllVariables.EXTRA_MESSAGE;
import static com.example.mygallery.AllVariables.FLIP;
import static com.example.mygallery.AllVariables.IMAGES;
import static com.example.mygallery.AllVariables.TINT;


public class Edit extends AppCompatActivity {
    Button button1;
    Button button2;
    String message;
    int choosen=-1;
    int flipUnit=0;
    int tintUnit=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        message = extras.getString(EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.edittext);
        textView.setText("Editing "+message);
        ImageView ava = findViewById(R.id.editing);
        choosen=extras.getInt(CHOOSEN);
        ava.setImageResource(IMAGES[choosen]);
        int[] flip= extras.getIntArray(FLIP);
        switch(flip[choosen]){
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

        button1 = (Button) findViewById(R.id.save);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });

        button2 = (Button) findViewById(R.id.cancel);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public void openMenu(){
        Intent oldintent = getIntent();
        Bundle extras = oldintent.getExtras();
        int[] arr = extras.getIntArray(FLIP);
        arr[choosen]=flipUnit;
        int[] arr1 = extras.getIntArray(TINT);
        arr1[choosen]= tintUnit;
        extras.putIntArray(FLIP,arr);
        extras.putIntArray(TINT,arr1);
        Intent i=new Intent(getApplicationContext(), openMenu.class);
        i.putExtras(extras);
        startActivity(i);
    }

    public void flip(View view) {
        ImageView ava = findViewById(R.id.editing);
        switch (view.getId()) {
            case R.id.flipx:
                if(ava.getScaleX()==1f){
                    ava.setScaleX(-1f);
                    if(flipUnit==0){
                        flipUnit=1;
                    }else{flipUnit=3;}
                }
                else{
                    ava.setScaleX(1f);
                    if(flipUnit==1){
                        flipUnit=0;
                    }else{flipUnit=2;}
                }
                break;
            case R.id.flipy:
                if(ava.getScaleY()==1f){
                    ava.setScaleY(-1f);
                    if(flipUnit==0){
                        flipUnit=2;
                    }else{flipUnit=3;}
                }
                else{
                    ava.setScaleY(1f);
                    if(flipUnit==2){
                        flipUnit=0;
                    }else{flipUnit=1;}}
                break;
        }

    }

    public void colorChange(View view){
        ImageView ava = findViewById(R.id.editing);
        switch (view.getId()) {
            case R.id.red:
                ava.setColorFilter(Color.argb(32, 232, 30, 30));
                tintUnit=1;
                break;
            case R.id.blue:
                ava.setColorFilter(Color.argb(32, 30, 30, 232));
                tintUnit=2;
                break;
            case R.id.yellow:
                ava.setColorFilter(Color.argb(32, 232, 232, 30));
                tintUnit=3;
                break;
        }

    }
}