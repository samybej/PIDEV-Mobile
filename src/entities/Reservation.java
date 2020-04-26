/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.util.Date;
/**
 *
 * @author Iyadhtr
 */
public class Reservation {
    
     private int id;
     private float distance;
     private String depart;
     private String arrive;
     private Date date;
     private String type;
     private float tarif;
     private int etat = '1';
     private int idChauffeur;
     private int idClient;

     
     public Reservation (){
         
     }
     
     public Reservation ( float distance, String depart,String arrive, Date date, String type, float tarif, int etat, int idChauffeur, int idClient ){
      
        this.distance =distance;
        this.depart = depart;
        this.arrive = arrive;
        this.date = date;
        this.type = type;
        this.tarif = tarif;
        this.etat = etat;
        this.idChauffeur = idChauffeur;
        this.idClient = idClient;
       
     }
     
     public Reservation (int id, float distance, String depart,String arrive, Date date, String type, float tarif, int etat, int idChauffeur, int idClient ){
          this.id = id;
        this.distance =distance;
        this.depart = depart;
        this.arrive = arrive;
        this.date = date;
        this.type = type;
        this.tarif = tarif;
        this.etat = etat;
        this.idChauffeur = idChauffeur;
        this.idClient = idClient;
       
     }
     
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getTarif() {
        return tarif;
    }

    public void setTarif(float tarif) {
        this.tarif = tarif;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getIdChauffeur() {
        return idChauffeur;
    }

    public void setIdChauffeur(int idChauffeur) {
        this.idChauffeur = idChauffeur;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", distance=" + distance + ", depart=" + depart + ", arrive=" + arrive + ", date=" + date + ", type=" + type + ", tarif=" + tarif + ", etat=" + etat + ", idChauffeur=" + idChauffeur + ", idClient=" + idClient + '}';
    }
    

    
   

   

  
   

    
    

    
}
