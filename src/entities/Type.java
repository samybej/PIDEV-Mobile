/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Asus
 */
public class Type {
    private int id;
    private int vitesse;
    private int nbrArrets;
    private int tmpArret;
    private int idOffre;

    public Type() {
    }

    public Type(int vitesse, int nbrArrets, int tmpArret, int idOffre) {
        this.vitesse = vitesse;
        this.nbrArrets = nbrArrets;
        this.tmpArret = tmpArret;
        this.idOffre = idOffre;
    }

    public Type(int id, int vitesse, int nbrArrets, int tmpArret, int idOffre) {
        this.id = id;
        this.vitesse = vitesse;
        this.nbrArrets = nbrArrets;
        this.tmpArret = tmpArret;
        this.idOffre = idOffre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public int getNbrArrets() {
        return nbrArrets;
    }

    public void setNbrArrets(int nbrArrets) {
        this.nbrArrets = nbrArrets;
    }

    public int getTmpArret() {
        return tmpArret;
    }

    public void setTmpArret(int tmpArret) {
        this.tmpArret = tmpArret;
    }

    public int getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(int idOffre) {
        this.idOffre = idOffre;
    }

    @Override
    public String toString() {
        return "Type{" + "id=" + id + ", vitesse=" + vitesse + ", nbrArrets=" + nbrArrets + ", tmpArret=" + tmpArret + ", idOffre=" + idOffre + '}';
    }
    
    
}
