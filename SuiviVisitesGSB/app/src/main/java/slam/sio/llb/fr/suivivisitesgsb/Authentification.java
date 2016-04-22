package slam.sio.llb.fr.suivivisitesgsb;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Authentification extends Activity {

private Button btnSeConnecter, btnAnnuler;
private EditText editTextLogin, editTextMDP;
private String identifiant, mdp;
private AsyncTask<String, String, Boolean> connexionAsynchrone;
private SharedPreferences jetonAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);
        jetonAuth = getSharedPreferences("AppData", MODE_PRIVATE);


        btnAnnuler = (Button) findViewById(R.id.buttonAnnul);
        btnAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        btnSeConnecter = (Button) findViewById(R.id.buttonConn);
        btnSeConnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                editTextLogin = (EditText) findViewById(R.id.login);
                editTextMDP = (EditText) findViewById(R.id.mdp);

                identifiant = editTextLogin.getText().toString();
                mdp = editTextMDP.getText().toString();

                String[] mesParams = {identifiant, mdp, "http://gsb.labo.ch/android/suivivisitesgsb/authentification.php"};
                connexionAsynchrone = new Connexion(Authentification.this).execute(mesParams);
            }
        });
    }


    public void retourVersAuthentification(StringBuilder codeRetour) {
        SharedPreferences.Editor edit = jetonAuth.edit();
        // Authentification réussie si la valeur de retour est 1 (voir script PHP)
        if (codeRetour.toString().compareTo("1") == 0) {
            Toast.makeText(this, "Authentification réussie", Toast.LENGTH_SHORT).show();
            // On stocke dans les Shared Preference le login et le mot de passe
            // si l'utilisateur a été authentifié
            edit.putString("login", identifiant);
            edit.putString("mdp", mdp);
            edit.commit();
            setResult(Activity.RESULT_OK);
            finish();
        }
        else
        {
            // Echec Authentification
            Toast.makeText(this, "Echec authentification", Toast.LENGTH_SHORT).show();
            edit.clear();
            edit.commit();
            setResult (Activity.RESULT_CANCELED);
            finish();
        }
    }
}
