package slam.sio.llb.fr.suivivisitesgsb;

import android.app.Activity;
import android.graphics.AvoidXfermode;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import slam.sio.llb.fr.suivivisitesgsb.metier.Modele;
import slam.sio.llb.fr.suivivisitesgsb.metier.Visite;

public class AfficheVisite extends Activity {

    private String idVisite;
    private Visite visite;
    private Modele m = new Modele();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_visite);

        // Récupération des données de l'activité appelante
        Bundle bundle = getIntent().getExtras();
        idVisite = bundle.getString("id");
        Log.i("id : ", idVisite.toString());
        Toast.makeText(getApplicationContext(), idVisite, Toast.LENGTH_SHORT).show();

        // Récupére la visite ayant l'id passé en intent
        //visite = (new Modele()).trouveVisite(idVisite);
        //visite = m.trouveVisite("1005");

        // Données du prospect
        //TextView textView = (TextView) findViewById(R.id.nomProspect);
        //textView.setText(visite.getNom() + " " + visite.getPrenom());

        // A compléter

        // Gestion des évènements des boutons
        // A compléter


    }

    // Méthode qui sauvegarde les modifications saisies
    /*public void saveAvis() {

        // On récupère les valeurs saisies
        Switch sw = ((Switch) findViewById(R.id.presenceProspect));

        int day = ((DatePicker) findViewById(R.id.dateVisite)).getDayOfMonth();
        int month = ((DatePicker) findViewById(R.id.dateVisite)).getMonth();
        int year = ((DatePicker) findViewById(R.id.dateVisite)).getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        // A compléter

        // Si le prospect est présent on sauvegarde tous les champs
        if(sw.isChecked()){
            visite.setPresent(true);
            // A compléter
            Toast.makeText(getApplicationContext(), "Sauvegarde avis ok", Toast.LENGTH_SHORT).show();
        } else{
            // A compléter
        }
        Log.i("avisvisite", visite.toString());
        // A compléter
    }*/
}
