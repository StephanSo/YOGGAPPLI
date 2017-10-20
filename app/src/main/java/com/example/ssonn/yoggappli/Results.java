package com.example.ssonn.yoggappli;

import android.content.SharedPreferences;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import metier.Enchainement;

public class Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Bundle b = getIntent().getExtras();
        String choix = b.getString("ChoixMethode");
        TextView bienvenue = (TextView) findViewById(R.id.msgBienvenue);
        bienvenue.setText("Bienvenue dans voir "+choix);

        switch (choix){
            case "Préferences":

                Gson gson = new Gson();
                SharedPreferences mesPrefs;
                mesPrefs = this.getSharedPreferences("mesVarGlobales",0);
                int nb=mesPrefs.getInt("nbEnchainement",0);
                Log.i("test",String.valueOf(nb));
                for(int i=1;i<=nb;i++){
                    String str = mesPrefs.getString(String.valueOf(i),"");
                    Enchainement exo = gson.fromJson(str,Enchainement.class);
                    Toast.makeText(getApplicationContext(),
                            exo.getPosture(),
                            Toast.LENGTH_SHORT).show();
                    
                }
        }
    }
}
