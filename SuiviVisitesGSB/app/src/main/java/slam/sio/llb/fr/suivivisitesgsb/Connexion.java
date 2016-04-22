package slam.sio.llb.fr.suivivisitesgsb;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Connexion extends AsyncTask<String, String, Boolean> {

    // RÈfÈrence ‡ l'activitÈ qui appelle
    private WeakReference<Activity> activiteAppelante = null;
    private String nomClasseActiviteAppelante;
    private StringBuilder stringBuilder = new StringBuilder();


    // Constructeur
    public Connexion(Activity activiteAppelante) {
        this.activiteAppelante = new WeakReference<Activity>(activiteAppelante);// RÈfÈrence ‡ l'activitÈ appelante
        nomClasseActiviteAppelante = activiteAppelante.getClass().toString();    // Nom de la classe (activitÈ) appelante
    }

    // MÈthode exÈcutÈe au dÈmarrage
    @Override
    protected void onPreExecute() {
        // Au lancement, on envoie un message ‡ l'appelant
        if (activiteAppelante.get() != null)
            Toast.makeText(activiteAppelante.get(), "AsyncTask démarre", Toast.LENGTH_SHORT).show();
    }

    // MÈthode exÈcutÈe en arriËre plan
    @Override
    protected Boolean doInBackground(String... params) {
        String identifiant = "", mdp = "", urlServiceGSB = "" /* A complÈter pour l'activitÈ Export */;

        // Affectation des paramËtres adÈquats en fonction de l'activitÈ appelante
        if (nomClasseActiviteAppelante.contains("Authentification") || nomClasseActiviteAppelante.contains("Exportation") || nomClasseActiviteAppelante.contains("Importation")) {
            identifiant = params[0];
            mdp = params[1];
            urlServiceGSB = params[2];
        }

        // Connexion au serveur en POST et envoi des donnÈes d'authentification au format JSON
        HttpURLConnection urlConnexion = null;
        try {
            URL url = new URL(urlServiceGSB);
            urlConnexion = (HttpURLConnection) url.openConnection();
            urlConnexion.setRequestProperty("Content-Type", "application/json");
            urlConnexion.setRequestProperty("Accept", "application/json");
            urlConnexion.setRequestMethod("POST");
            urlConnexion.setDoOutput(true);
            urlConnexion.setConnectTimeout(5000);

            OutputStreamWriter out = new OutputStreamWriter(urlConnexion.getOutputStream());

            // Selon l'activitÈ appelante on peut passer des paramËtres en JSON
            if (nomClasseActiviteAppelante.contains("Authentification") || nomClasseActiviteAppelante.contains("Importation")) {

                // CrÈation objet JSON clÈ->valeur pour l'activitÈ Authentification
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("login", identifiant);
                jsonParam.put("mdp", mdp);
                out.write(jsonParam.toString());
                out.flush();
            }

            out.close();

            // Traitement du script PHP correspondant sur le serveur

            // RÈcupÈration du rÈsultat de la requÍte au format JSON depuis le serveur
            int HttpResult = urlConnexion.getResponseCode();
            if (HttpResult == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(urlConnexion.getInputStream(), "utf-8"));

                String line = null;
                while ((line = br.readLine()) != null) {
                    stringBuilder.append(line);
                }
                br.close();
                Log.i("retourServeur", "Réponse du serveur :" + stringBuilder.toString());

            } else
                Log.i("retourServeur", "Erreur :" + urlConnexion.getResponseMessage());

            // Gestion des exceptions pouvant survenir pendant la connexion vers le serveur
        } catch (MalformedURLException e) {
            Toast.makeText(activiteAppelante.get(), "URL malformée", Toast.LENGTH_SHORT).show();
            return false;

        } catch (IOException e) {
            Toast.makeText(activiteAppelante.get(), "IO error", Toast.LENGTH_SHORT).show();
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            // Ferme la connexion vers le serveur dans tous les cas
            if (urlConnexion != null)
                urlConnexion.disconnect();
        }

        return true;
    }

    // Utilisation de onProgress pour afficher des messages pendant le doInBackground
    @Override
    protected void onProgressUpdate(String... param) {
    }

    // MÈthode exÈcutÈe ‡ la fin de la mÈthode doInBackGroud()
    @Override
    protected void onPostExecute(Boolean result) {
        if (activiteAppelante.get() != null) {
            if (result) {
                Toast.makeText(activiteAppelante.get(), "Fin AsynckTask", Toast.LENGTH_SHORT).show();

                // Retour vers une mÈthode de la classe appelante
                if (nomClasseActiviteAppelante.contains("Authentification")) {
                    ((Authentification) activiteAppelante.get()).retourVersAuthentification(stringBuilder);
                }


                // A complÈter pour les activitÈs Import et Export

            } else
                Toast.makeText(activiteAppelante.get(), "Fin ko", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCancelled() {
        if (activiteAppelante.get() != null)
            Toast.makeText(activiteAppelante.get(), "Annulation", Toast.LENGTH_SHORT).show();
    }
}
