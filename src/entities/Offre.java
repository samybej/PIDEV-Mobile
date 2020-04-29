/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class Offre {
    
    private int id;
    private int nbPlace;
    private String depart;
    private String arrive;
    private String date;
    private String time;
    private float tarif;
    private int idOffreur;
    private int idClient;
    private String vehicule;
    private String bagage;

    public Offre() {
    }

    public Offre(int nbPlace, String depart, String arrive, String date, String time, float tarif, int idOffreur, int idClient, String vehicule, String bagage) {
        this.nbPlace = nbPlace;
        this.depart = depart;
        this.arrive = arrive;
        this.date = date;
        this.time = time;
        this.tarif = tarif;
        this.idOffreur = idOffreur;
        this.idClient = idClient;
        this.vehicule = vehicule;
        this.bagage = bagage;
    }

    public Offre(int id, int nbPlace, String depart, String arrive, String date, String time, float tarif, int idOffreur, int idClient, String vehicule, String bagage) {
        this.id = id;
        this.nbPlace = nbPlace;
        this.depart = depart;
        this.arrive = arrive;
        this.date = date;
        this.time = time;
        this.tarif = tarif;
        this.idOffreur = idOffreur;
        this.idClient = idClient;
        this.vehicule = vehicule;
        this.bagage = bagage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getArrive() {
        return arrive;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getTarif() {
        return tarif;
    }

    public void setTarif(float tarif) {
        this.tarif = tarif;
    }

    public int getIdOffreur() {
        return idOffreur;
    }

    public void setIdOffreur(int idOffreur) {
        this.idOffreur = idOffreur;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getVehicule() {
        return vehicule;
    }

    public void setVehicule(String vehicule) {
        this.vehicule = vehicule;
    }

    public String getBagage() {
        return bagage;
    }

    public void setBagage(String bagage) {
        this.bagage = bagage;
    }

    @Override
    public String toString() {
        return "Offre{" + "id=" + id + ", nbPlace=" + nbPlace + ", depart=" + depart + ", arrive=" + arrive + ", date=" + date + ", time=" + time + ", tarif=" + tarif + ", idOffreur=" + idOffreur + ", idClient=" + idClient + ", vehicule=" + vehicule + ", bagage=" + bagage + '}';
    }
    
    
    
    
    
}
