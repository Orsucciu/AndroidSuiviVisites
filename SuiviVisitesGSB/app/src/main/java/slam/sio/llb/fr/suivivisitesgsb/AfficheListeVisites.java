package slam.sio.llb.fr.suivivisitesgsb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import slam.sio.llb.fr.suivivisitesgsb.metier.Modele;
import slam.sio.llb.fr.suivivisitesgsb.metier.Visite;
import slam.sio.llb.fr.suivivisitesgsb.metier.VisiteAdapter;

public class AfficheListeVisites extends Activity {

    private ListView listView;
    private Modele modele = new Modele();
    private List<Visite> listeVisites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_liste_visites);

        // Affichage de la liste des visites
        listeVisites = modele.listeVisites();
        listView = (ListView) findViewById(R.id.listeVisites);
        VisiteAdapter adaptVisite = new VisiteAdapter(this, listeVisites);
        listView.setAdapter(adaptVisite);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Intent i;
                Toast.makeText(getApplicationContext(), "Choix : " + listeVisites.get(position).getId(), Toast.LENGTH_SHORT).show();
                i = new Intent(getApplicationContext(), AfficheVisites.class);
                startActivity(i);
                // Appel de l'activité AfficheVisite
                // A compléter

            }
        });
    }
}
