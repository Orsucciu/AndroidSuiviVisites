package slam.sio.llb.fr.suivivisitesgsb.métier;

import com.db4o.ObjectSet;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by btssio2 on 25/03/16.
 */
public class Visite {
    private String id, nom, prenom, adresse, tel;

    private Boolean present;
    private Date dateVisite;
    private float niveauConfiance;
    private String lisibilite;
    private String problemesUtilisation;

    @Override
    public String toString() {
        return "Visite{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", tel='" + tel + '\'' +
                ", present=" + present +
                ", dateVisite=" + dateVisite +
                ", niveauConfiance=" + niveauConfiance +
                ", lisibilite='" + lisibilite + '\'' +
                ", problemesUtilisation='" + problemesUtilisation + '\'' +
                '}';
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Boolean getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }

    public Date getDateVisite() {
        return dateVisite;
    }

    public void setDateVisite(Date dateVisite) {
        this.dateVisite = dateVisite;
    }

    public float getNiveauConfiance() {
        return niveauConfiance;
    }

    public void setNiveauConfiance(float niveauConfiance) {
        this.niveauConfiance = niveauConfiance;
    }

    public String getLisibilite() {
        return lisibilite;
    }

    public void setLisibilite(String lisibilite) {
        this.lisibilite = lisibilite;
    }

    public String getProblemesUtilisation() {
        return problemesUtilisation;
    }

    public void setProblemesUtilisation(String problemesUtilisation) {
        this.problemesUtilisation = problemesUtilisation;
    }

    public Visite(String id, String nom, String prenom, String adresse, String tel){
        this.present = false;
        this.dateVisite = new Date();
        this.niveauConfiance = 0;
        this.lisibilite = "";
        this.problemesUtilisation = "";
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;

    }

    public Visite copieVisite(Visite v){
        this.present = v.present;
        this.dateVisite = v.dateVisite;
        this.niveauConfiance = v.niveauConfiance;
        this.lisibilite = v.lisibilite;
        this.problemesUtilisation = v.problemesUtilisation;
        this.id = v.id;
        this.nom = v.nom;
        this.prenom = v.prenom;
        this.adresse = v.adresse;
        this.tel = v.tel;
        return v;
    }

}
