package com.example.mygallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.mygallery.AllVariables.EXTRA_MESSAGE;


public class renameMenu extends AppCompatActivity {
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rename_menu);
        Intent intent = getIntent();
        message = intent.getStringExtra(EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.oldname);
        textView.setText("Renaming "+message);
        EditText eview =  findViewById(R.id.newName);
        eview.setText(message);
    }

    public void onClick(View view) {
        Intent oldintent = getIntent();
        Bundle extras = oldintent.getExtras();
        EditText eview =  findViewById(R.id.newName);
        String x = eview.getText().toString();
        extras.putString(EXTRA_MESSAGE, x);
        Intent intent = new Intent(this, openMenu.class);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void cancel(View view) {
        finish();
    }


}