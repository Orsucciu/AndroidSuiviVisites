package slam.sio.llb.fr.suivivisitesgsb.métier;

import android.os.Environment;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by btssio2 on 25/03/16.
 */
public class modele {
    private String db4oFileName;
    private ObjectContainer dataBase;
    private File appDir;

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
            retour.add((Visite)result);
            result.next();
        }
        close();
        return retour;
    }

    public Visite trouveVisite(String id){
        Visite laVisite = new Visite("", "", "", "", "");
        laVisite.setId(id);
        open();
        ObjectSet<Visite> result = dataBase.queryByExample(laVisite);
        laVisite = (Visite) result.next();
        close();
        return laVisite;
    }

    public void saveVisite(Visite v){
        open();
        Visite check = trouveVisite(v.getId());
        if (check != null){
            dataBase.store(check.copieVisite(v));
        }else{
            dataBase.store(v);
        }
        close();
    }

    public void deleteVisite(){
        ArrayList<Visite> retour = new ArrayList();
        open();
        ObjectSet result = dataBase.queryByExample(Visite.class);
        while (result.hasNext()){
            dataBase.delete(result.next());
        }
        close();
    }
}
