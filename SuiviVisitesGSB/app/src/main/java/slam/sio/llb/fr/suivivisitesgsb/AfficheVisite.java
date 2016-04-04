package slam.sio.llb.fr.suivivisitesgsb;

import android.app.Activity;
import android.graphics.AvoidXfermode;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import slam.sio.llb.fr.suivivisitesgsb.metier.Modele;
import slam.sio.llb.fr.suivivisitesgsb.metier.Visite;

public class AfficheVisite extends Activity {

    private String idVisite;
    private Visite visite;

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
        visite = (new Modele()).trouveVisite(idVisite);
        Log.i("visite ", visite.toString());

        // Données du prospect
        TextView textView = (TextView) findViewById(R.id.nomProspect);
        textView.setText(visite.getNom() + " " + visite.getPrenom());

        textView = (TextView) findViewById(R.id.telProspect);
        textView.setText(visite.getTel());

        textView = (TextView) findViewById(R.id.adresseProspect);
        textView.setText(visite.getAdresse());

        Switch switchView = (Switch) findViewById(R.id.presenceProspect);
        switchView.setChecked(visite.getPresent());

        //textView = (TextView) findViewById(R.id.datePickerVisite);
        //textView.setText(visite.getDateVisite());

        RatingBar barView = (RatingBar) findViewById(R.id.ratingBarProspect);
        barView.setRating(visite.getNiveauConfiance());
        barView.setStepSize(0.5f);

        RadioButton radioLisible = (RadioButton) findViewById(R.id.lisibleOk);
        RadioButton radioMoyen = (RadioButton) findViewById(R.id.lisibleMoyen);
        RadioButton radioKo = (RadioButton) findViewById(R.id.lisibleKo);
        switch (visite.getLisibilite()){
            case "Ok":
                radioLisible.setChecked(true);
                break;
            case "Moyen":
                radioMoyen.setChecked(true);
                break;
            case "Ko":
                radioKo.setChecked(true);
                break;
        }

        EditText editView = (EditText) findViewById(R.id.champProblemes);
        editView.setText(visite.getProblemesUtilisation());

        Button boutonAnnuler = (Button) findViewById(R.id.annuler);
        boutonAnnuler.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

        Button boutonValider = (Button) findViewById(R.id.valider);
        boutonValider.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveAvis();
                finish();
            }
        });
    }

    // Méthode qui sauvegarde les modifications saisies
    public void saveAvis() {

        // On récupère les valeurs saisies
        Switch sw = ((Switch) findViewById(R.id.presenceProspect));

        int day = ((DatePicker) findViewById(R.id.datePickerVisite)).getDayOfMonth();
        int month = ((DatePicker) findViewById(R.id.datePickerVisite)).getMonth();
        int year = ((DatePicker) findViewById(R.id.datePickerVisite)).getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        float confiance = ((RatingBar) findViewById(R.id.ratingBarProspect)).getRating();
        String problemes = ((EditText) findViewById(R.id.champProblemes)).getText().toString();
        String radioLisible = ((RadioButton) findViewById(R.id.lisibleOk)).getText().toString();
        String radioMoyen = ((RadioButton) findViewById(R.id.lisibleMoyen)).getText().toString();
        String radioKo = ((RadioButton) findViewById(R.id.lisibleKo)).getText().toString();

        // Si le prospect est présent on sauvegarde tous les champs
        if(sw.isChecked()){
            visite.setPresent(true);
            visite.setDateVisite(calendar.getTime());
            visite.setNiveauConfiance(confiance);
            visite.setProblemesUtilisation(problemes);
            if (radioKo == "true"){
                visite.setLisibilite("Ko");
            }else if(radioMoyen == "true"){
                visite.setLisibilite("Moyen");
            }else {
                visite.setLisibilite("Ok");
            }
            (new Modele()).saveVisite(visite);
            Toast.makeText(getApplicationContext(), "Sauvegarde avis ok", Toast.LENGTH_SHORT).show();
        } else{
            visite.setPresent(false);
            visite.setDateVisite(calendar.getTime());
        }
        Log.i("avisvisite", visite.toString());
        // A compléter
    }

}


