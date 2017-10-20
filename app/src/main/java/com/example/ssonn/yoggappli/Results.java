package com.example.ssonn.yoggappli;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Bundle b = getIntent().getExtras();
        String choix = b.getString("ChoixMethode");
        TextView bienvenue = (TextView) findViewById(R.id.msgBienvenue);
        bienvenue.setText("Bienvenue dans voir "+choix);
    }
}
