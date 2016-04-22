package slam.sio.llb.fr.suivivisitesgsb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import slam.sio.llb.fr.suivivisitesgsb.metier.Modele;

public class MainActivity extends Activity {
    private ImageView imageViewVisites;
    private ImageView imageAuthentification;
    private ImageView imageImportation;
    private Modele m = new Modele();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewVisites = (ImageView) findViewById(R.id.imgVisites);
        imageViewVisites.setOnClickListener(imageClick);
        Log.i("affichage", m.listeVisites().toString());

        imageAuthentification = (ImageView) findViewById(R.id.imgAuth);
        imageAuthentification.setOnClickListener(imageClick);

        imageImportation = (ImageView) findViewById(R.id.imgImport);
        imageImportation.setOnClickListener(imageClick);
    }


            private View.OnClickListener imageClick = new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i;
                    switch (v.getId()) {
                        // Cas du clic sur l'image Visite
                        case R.id.imgVisites:
                            i = new Intent(getApplicationContext(), AfficheListeVisites.class);
                            startActivity(i);
                            break;
                        case R.id.imgAuth:
                            i = new Intent(getApplicationContext(), Authentification.class);
                            startActivity(i);
                            break;
                        case R.id.imgImport:
                            i = new Intent(getApplicationContext(), Importation.class);
                            startActivity(i);
                            break;
                    }
                }
            };

}
