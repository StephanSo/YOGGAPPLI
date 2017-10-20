package com.example.ssonn.yoggappli;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnVoirPrefs =(Button) findViewById(R.id.btnVoirPrefs);
        btnVoirPrefs.setOnClickListener(observateurClickBoutonVoir);
        Button btnVoirFiles = (Button) findViewById(R.id.btnVoirFile);
        btnVoirFiles.setOnClickListener(observateurClickBoutonVoir);
        Button btnVoirSQLite = (Button) findViewById(R.id.btnVoirSQLite);
        btnVoirSQLite.setOnClickListener(observateurClickBoutonVoir);
    }

    private View.OnClickListener observateurClickBoutonVoir = new View.OnClickListener(){
        public void onClick(View v){
            switch (v.getId()){
                case R.id.btnVoirPrefs:
                    Intent i = new Intent(getApplication(), Results.class);
                    i.putExtra("ChoixMethode","Préferences");
                    startActivity(i);
                    break;
                case R.id.btnVoirFile:
                    Intent j = new Intent(getApplication(),Results.class);
                    j.putExtra("ChoixMethode", "Fichier");
                    startActivity(j);
                    break;
                case R.id.btnVoirSQLite:
                    Intent y = new Intent(getApplication(),Results.class);
                    y.putExtra("ChoixMethode","SQLite");
                    startActivity(y);
                    break;

            }
        }
    };
}
