package com.example.ssonn.yoggappli;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import metier.Enchainement;
import metier.Importation;

public class ImportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import);

        Button btnVoirPrefs = (Button) findViewById(R.id.btnVoirPrefs);
        btnVoirPrefs.setOnClickListener(observateurClickBoutonVoir);

        Button btnPref = (Button) findViewById(R.id.btnPref);
        btnPref.setOnClickListener(observateurClickBouton);

        Button btnImport = (Button) findViewById(R.id.btnImport);
        btnImport.setOnClickListener(ObservateurImportButton);
    }
    private View.OnClickListener observateurClickBoutonVoir = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnVoirPrefs:
                    Intent i = new Intent(getApplication(), Results.class);
                    i.putExtra("ChoixMethode", "Préferences");
                    startActivity(i);
                    break;
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
                case R.id.btnPref:
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



            }
        }
    };
    private View.OnClickListener ObservateurImportButton = new View.OnClickListener(){
        public void onClick(View v){
            switch (v.getId()){
                case R.id.btnImport:
                    Spinner spin = (Spinner) findViewById(R.id.spinnerPostures);
                    Importation tacheImport = new Importation();
                    tacheImport.execute("http://localhost/yogappli/import.php");
                    try {
                        ArrayList<Enchainement> listeImportee = tacheImport.get();
                        if(listeImportee!=null){
                            EnchainementAdapter adapter;
                            adapter=new EnchainementAdapter(getApplicationContext(), listeImportee);
                            spin.setAdapter(adapter);
                        }
                        else{
                            Log.i("Parseur","Problème lors de la lecture du fichier");
                        }
                    } catch (InterruptedException e) {
                        Log.i("Parseur", "Interruption lecture fichier"+e.getMessage());
                    } catch (ExecutionException e) {
                        Log.i("Parseur", "Erreur execution"+ e.getMessage());
                    }
                    break;
            }
        }
    };
}

