package slam.sio.llb.fr.suivivisitesgsb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;

import slam.sio.llb.fr.suivivisitesgsb.metier.Modele;
import slam.sio.llb.fr.suivivisitesgsb.metier.Visite;

public class Importation extends Activity {
    private Button btnImporter, btnAnnuler;
    private AsyncTask<String, String, Boolean> connexionAsynchrone;
    private String identifiant, mdp;
    private SharedPreferences jetonAuth;
    private Modele md;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_importation);

        btnAnnuler = (Button) findViewById(R.id.annuler);
        btnAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        btnImporter = (Button) findViewById(R.id.importer);
        btnImporter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                jetonAuth = getSharedPreferences("AppData", Context.MODE_PRIVATE);
                identifiant = jetonAuth.getString("login", null);
                mdp = jetonAuth.getString("mdp", null);

                String[] mesParams = {identifiant, mdp, "http://gsb.labo.ch/android/suivivisitesgsb/import.php"};
                connexionAsynchrone = new Connexion(Importation.this).execute(mesParams);
            }
        });
    }

    // --> Retour
    // MÈthode exÈcutÈe aprËs la fin de l'AsynTask
    public void retourImport(StringBuilder donneesJSONServeur) {
        ArrayList<Visite> listeVisites = new ArrayList<Visite>();

        // RÈcupÈration des ÈlÈments au format JSON (dans un tableau)
        JsonElement json = new JsonParser().parse(donneesJSONServeur.toString());
        JsonArray tabJSON = json.getAsJsonArray();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();

        for (JsonElement jsonEle : tabJSON){
            listeVisites.add(gson.fromJson(jsonEle.getAsJsonObject(), Visite.class));
        }
        // Si la liste contient visites alors on la sauvegarde dans la base DB4o
        if (!listeVisites.isEmpty()) {
            md = new Modele();
            md.deleteVisite();
            md.addVisite(listeVisites);
            Toast.makeText(this, "Importation de donnÈes effectuÈe", Toast.LENGTH_SHORT).show();
            setResult(Activity.RESULT_OK);
            finish();

        } else {
            setResult(Activity.RESULT_CANCELED);
            finish();
        }
    }
}
