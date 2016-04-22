package slam.sio.llb.fr.suivivisitesgsb.metier;

import android.os.Environment;
import android.util.Log;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by btssio2 on 25/03/16.
 */
public class Modele {
    private String db4oFileName;
    private ObjectContainer dataBase;
    private File appDir;

    public Modele() {
        createDirectory();

    };

    public void createDirectory(){
        appDir = new File (Environment.getExternalStorageDirectory() + "/baseDB4o");
        if (!appDir.exists() && !appDir.isDirectory()){
            appDir.mkdirs();
        }
    }

    public void open() {
        db4oFileName = Environment.getExternalStorageDirectory() + "/baseDB4o" + "/BaseGSB.db4o";
        dataBase = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), db4oFileName);
    }

    public void close() {
        dataBase.close();
    }


    public ArrayList<Visite> listeVisites(){
        ArrayList<Visite> retour = new ArrayList();
        open();
        ObjectSet result = dataBase.queryByExample(Visite.class);
        while (result.hasNext()){
            retour.add((Visite) result.next());
        }
        close();
        return retour;
    }

    public Visite trouveVisite(String id){
        Visite laVisite = new Visite();
        laVisite.setId(id);
        open();
        ObjectSet<Visite> result = dataBase.queryByExample(laVisite);
        laVisite = (Visite) result.next();
        close();
        return laVisite;
    }

    public void saveVisite(Visite v){
        Visite laVisite = new Visite();
        laVisite.setId(v.getId());
        open();
        ObjectSet<Visite> result = dataBase.queryByExample(laVisite);
        laVisite = (Visite) result.next();
        if (laVisite != null){
            dataBase.store(laVisite.copieVisite(v));
        }else{
            dataBase.store(v);
        }
        close();
    }

    public void deleteVisite(){
        open();
        Log.i("erreur", "open ok");
        ObjectSet<Visite> result = dataBase.queryByExample(Visite.class);
        while (result.hasNext()){
            dataBase.delete(result.next());
        }
        close();
    }

    public void addVisite(ArrayList<Visite> lesVistes){
        open();
        for (Visite v : lesVistes){
            dataBase.store(v);
        }
        close();
    }

    public void chargeJeuEssai() {
        open();
        ObjectSet<Visite> result = dataBase.queryByExample(Visite.class);
        if (result.size() == 0) {
            dataBase.store(new Visite("1001", "Riera ", "Alain", "14 Boulevard Maglioli 20000 Ajaccio", "0495238757"));
            dataBase.store(new Visite("1002", "Eiden ", "Pierre", "14 Rue Docteur Del Pellegrino 20090 Ajaccio", "0495208585"));
            dataBase.store(new Visite("1003", "Ferrandi ", "Frédéric", "20 Cours Napoléon 20090 Ajaccio", "0495213371"));
            dataBase.store(new Visite("1004", "Ferrara ", "Jean-Jacques", "2 Cours Grandval 20000 Ajaccio", "0495212447"));
            dataBase.store(new Visite("1005", "Flori ", "Alexandre", "Résidence Acqualonga 20167 Ajaccio", "0495100808"));
            dataBase.store(new Visite("1006", "Franceschini ", "Antoine", "14 Rue Docteur Del Pellegrino 20090 Ajaccio", "0495208585"));
            dataBase.store(new Visite("1007", "Franchini ", "Marc", "19 Cours Général Leclerc 20000 Ajaccio", "0495103695"));
            dataBase.store(new Visite("1008", "Le Breton ", "Geneviève", "28 Boulevard Pascal Rossini 20000 Ajaccio", "0495200220"));
            dataBase.commit();
        }
        close();
    }
}
