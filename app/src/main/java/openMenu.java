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
import static com.example.mygallery.AllVariables.NAMES;
import static com.example.mygallery.AllVariables.TINT;


public class openMenu extends AppCompatActivity {
    Button button1;
    Button button2;
    Button button3;
    String message;
    int choosen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_menu);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        message = extras.getString(EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.choosenName);
        textView.setText(message);
        ImageView ava = findViewById(R.id.choosenImg);
        choosen=extras.getInt(CHOOSEN);
        ava.setImageResource(IMAGES[choosen]);
        int[] flip= extras.getIntArray(FLIP);
        int[] tint = extras.getIntArray(TINT);

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
        switch(tint[choosen]){
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

        button1 = (Button) findViewById(R.id.rename);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRename();
            }
        });

        button2 = (Button) findViewById(R.id.back);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent oldintent = getIntent();
                Bundle extras = oldintent.getExtras();
                String[] names= extras.getStringArray(NAMES);
                names[choosen]=message;
                Intent i=new Intent(getApplicationContext(), MainActivity.class);
                i.putExtras(extras);
                startActivity(i);
            }
        });


        button3 = (Button) findViewById(R.id.edit);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEdit();
            }
        });

    }
    public void openRename(){
        Intent oldintent = getIntent();
        Bundle extras = oldintent.getExtras();
        extras.putString(EXTRA_MESSAGE, message);
        Intent intent = new Intent(this, renameMenu.class);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void openEdit(){
        Intent oldintent = getIntent();
        Bundle extras = oldintent.getExtras();
        extras.putString(EXTRA_MESSAGE, message);
        Intent inten = new Intent(this, Edit.class);
        inten.putExtras(extras);
        startActivity(inten);
    }
}