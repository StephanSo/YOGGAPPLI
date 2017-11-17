package com.example.ssonn.yoggappli;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.IOException;

import metier.Enchainement;

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
        Button btnVoirImport = (Button) findViewById(R.id.btnVoirImport);
        btnVoirImport.setOnClickListener(observateurClickBoutonVoir);

        Button btnPrefs = (Button) findViewById(R.id.btnPrefs);
        btnPrefs.setOnClickListener(observateurClickBouton);
        Button btnFile = (Button)findViewById(R.id.btnFile);
        btnFile.setOnClickListener(observateurClickBouton);
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
                case R.id.btnVoirImport:
                    Intent z = new Intent(getApplication(), ImportActivity.class);
                    startActivity(z);

            }
        }
    };
    private View.OnClickListener observateurClickBouton = new View.OnClickListener(){
      public void onClick(View v){
          EditText postureEdit = (EditText) findViewById(R.id.txtPosture);
          String posture = postureEdit.getText().toString();
          EditText nbRespEdit = (EditText) findViewById(R.id.txtNbResp);
          int nbResp = Integer.parseInt(nbRespEdit.getText().toString());
          switch (v.getId()){
              case R.id.btnPrefs:
//                  ArrayList<Enchainement> list;
//                  SharedPreferences mesPrefs;
//                  mesPrefs = getApplicationContext().getSharedPreferences("maSeanceDeYoga", 0);
//                  SharedPreferences.Editor monEditeur = mesPrefs.edit();
//                  Gson gson = new Gson();
//                  String str = mesPrefs.getString("liste","");
//                  if(str.equals("")){
//                      list = new ArrayList<>();
//                  }
//                  else{
//                      list = gson.fromJson(str, new TypeToken<ArrayList<Enchainement>>(){}.getType());
//                  }
//                  Enchainement.setTousLesEnchainements(list);
//                  Enchainement.addEnchainement(posture, nbResp);
//                  monEditeur.putString("liste", gson.toJson(Enchainement.tousLesEnchainements));



                  Enchainement exo = new Enchainement(posture, nbResp);
                  SharedPreferences mesPrefs;
                  mesPrefs = getApplicationContext().getSharedPreferences("mesVarGlobales",0);
                  SharedPreferences.Editor monEditeurDePreferences = mesPrefs.edit();
                  int nb = mesPrefs.getInt("nbEnchainement",0);
                  nb++;
                  monEditeurDePreferences.putInt("nbEnchainement",nb);

                  Gson gson = new Gson();
                  monEditeurDePreferences.putString(String.valueOf(nb),gson.toJson(exo));
                  monEditeurDePreferences.apply();
                  Toast.makeText(getApplicationContext(),
                          "Enregistrement effectué",
                          Toast.LENGTH_LONG).show();
                  break;
              case R.id.btnFile:
                  exo = new Enchainement(posture,nbResp);
                  gson = new Gson();
                  try {
                      FileOutputStream fic;
                      fic = getApplicationContext().openFileOutput("monFichier.txt",
                              Context.MODE_APPEND);
                      String str = gson.toJson(exo)+"\n";
                      fic.write(str.getBytes());
                      fic.close();
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
                  Toast.makeText(getApplicationContext(),
                          "Enregisterment effectué ",
                          Toast.LENGTH_LONG).show();
                  break;


          }
      }
    };
}
