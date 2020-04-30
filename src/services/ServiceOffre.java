/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionListener;
import entities.Client;
import entities.Offre;
import entities.Type;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.Statics;

/**
 *
 * @author Asus
 */
public class ServiceOffre {
    
    public ArrayList<Offre> offres;
    public ArrayList<Type> types;
    public ArrayList<Client> clients;
    public Map<Integer,List> map;
    public Map<Offre,Client> mapRecherche = new HashMap<>();
    
    public static ServiceOffre instance=null;
    public boolean resultOK;
    public float id;
    private ConnectionRequest req;

    private ServiceOffre() {
         req = new ConnectionRequest();
    }

    public static ServiceOffre getInstance() {
        if (instance == null) {
            instance = new ServiceOffre();
        }
        return instance;
    }
    
    public boolean inscriOffre(int idOffre,int idClient,int idOffreur)
    {
        String url = Statics.BASE_URL + "api/inscriCovoiturage/" + idOffre + "/" + idClient + "/" + idOffreur;
        req.setUrl(url);
        
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
        
    }
     public int ajoutOffre(Offre o)  {
        String url = Statics.BASE_URL + "api/ajoutCovoiturages/" + String.valueOf(o.getNbPlace())+ "/" + o.getDepart()+ "/" + o.getArrive()+ "/" + o.getDate()+ "/" + o.getTime()+ "/" + String.valueOf(o.getTarif())+ "/" + String.valueOf(o.getIdClient())+ "/" + o.getVehicule()+ "/" + o.getBagage();
        req.setUrl(url);
       //float id;
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                if (resultOK)
                {
                    String jsonText = new String(req.getResponseData());
                    
                    JSONParser j = new JSONParser();
                    Map<String,Object> offresListJson = new HashMap<>();
                    
                    try {
                      offresListJson  = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

                    } catch (IOException ex) {
                  
                    }
                    id = Float.parseFloat(offresListJson.get("id").toString());
                    
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return (int)id;
    }
     
     
     public Map<Offre,Client> rechercherCovoiturage(String date,String depart,String arrive)
     {
        String url = Statics.BASE_URL+"api/rechercherCovoiturage/" + date + "/" + depart + "/" + arrive;
        req.setUrl(url);
        req.setPost(false);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                try {
                    mapRecherche = parseRecherche(new String(req.getResponseData()));          
                    req.removeResponseListener(this);
                } catch (IOException ex) {
                 
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
         
         return mapRecherche;
     }
     
     public Map<Offre,Client> parseRecherche(String jsonText) throws IOException
     {
         offres=new ArrayList<>();
         clients = new ArrayList<>();
         map = new HashMap<>();
         
         JSONParser j = new JSONParser();
         Map<String,Object> offresListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
         
         List<Map<String,Object>> list = (List<Map<String,Object>>)offresListJson.get("root");
         
         for(Map<String,Object> obj : list){
             Offre o = new Offre();
             Client c = new Client();
             
             //Données de l'offre
             float id = Float.parseFloat(obj.get("id").toString());
             float nbPlace = Float.parseFloat(obj.get("nbPlace").toString());
             String depart = obj.get("depart").toString();
             String arrive = obj.get("arrive").toString();
             String date = obj.get("date").toString();
             String time = obj.get("time").toString();
             float tarif = Float.parseFloat(obj.get("tarif").toString());
             String vehicule = obj.get("vehicule").toString();
             String bagage = obj.get("bagage").toString();
             
             //Données du client
             float idClient = Float.parseFloat(((Map)obj.get("idClient")).get("id").toString()) ;
             String nom = ((Map)obj.get("idClient")).get("nom").toString();
             String prenom = ((Map)obj.get("idClient")).get("prenom").toString();
             float tel = Float.parseFloat(((Map)obj.get("idClient")).get("tel").toString()) ;
             String adresse = ((Map)obj.get("idClient")).get("adresse").toString();
             String mdp = ((Map)obj.get("idClient")).get("mdp").toString();
             String mail = ((Map)obj.get("idClient")).get("mail").toString();
             
             o.setId((int)id);
             o.setNbPlace((int)nbPlace);
             o.setDepart(depart);
             o.setArrive(arrive);
             o.setDate(date);
             o.setTime(time);
             o.setTarif(tarif);
             o.setVehicule(vehicule);
             o.setBagage(bagage);
             o.setIdOffreur((int)idClient);
             o.setIdClient((int)idClient);
             
             c.setId((int)idClient);
             c.setNom(nom);
             c.setPrenom(prenom);
             c.setTel((int)tel);
             c.setAdresse(adresse);
             c.setMdp(mdp);
             c.setMail(mail);
             
             offres.add(o);
             clients.add(c);
             
             mapRecherche.put(o, c);
             
             
         }
         return mapRecherche;
     }
     
     public Map<Integer,List> getCovoiturages()
     {
         String url = Statics.BASE_URL+"api/covoiturages/209";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    
                    map = parseOffres(new String(req.getResponseData()));
                    
                    req.removeResponseListener(this);
                } catch (ParseException ex) {
                    System.out.println("ahja");
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return map;
     }
     
       public Map<Integer,List> parseOffres(String jsonText) throws ParseException{
        try {
            offres=new ArrayList<>();
            types=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> offresListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)offresListJson.get("root");
            for(Map<String,Object> obj : list){
                Offre o = new Offre();
                
                Type t = new Type();
                
                //Les donnees mte3 el type
                float id = Float.parseFloat(obj.get("id").toString());
                float idOffre = Float.parseFloat(((Map)obj.get("idOffre")).get("id").toString());
                float vitesse = Float.parseFloat(obj.get("vitesse").toString());
                float nbrArrets = Float.parseFloat(obj.get("nbrArrets").toString());
                float tmpArret = Float.parseFloat(obj.get("tmpArret").toString());
                
                //les donnes mte3 el offre
                float nbPlace = Float.parseFloat(((Map)obj.get("idOffre")).get("nbPlace").toString());
                float idOffreur = Float.parseFloat(((Map<Object,Map>)obj.get("idOffre")).get("idOffreur").get("id").toString());
                float idClient = Float.parseFloat(((Map<Object,Map>)obj.get("idOffre")).get("idClient").get("id").toString());
                String date = ((Map)obj.get("idOffre")).get("date").toString();
                Date date_new = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                
                
                
                o.setId((int)idOffre);
                o.setNbPlace((int)nbPlace);
                o.setDepart(((Map)obj.get("idOffre")).get("depart").toString());
                o.setArrive(((Map)obj.get("idOffre")).get("arrive").toString());
                o.setDate(date);
                o.setTime(((Map)obj.get("idOffre")).get("time").toString());
                o.setIdOffreur((int)idOffreur);
                o.setIdClient((int)idClient);
                o.setVehicule(((Map)obj.get("idOffre")).get("vehicule").toString());
                o.setBagage(((Map)obj.get("idOffre")).get("bagage").toString());
                
                t.setId((int)id);
                t.setVitesse((int)vitesse);
                t.setNbrArrets((int)nbrArrets);
                t.setTmpArret((int)tmpArret);
                t.setIdOffre((int)idOffre);
                
               // System.out.println(t);
                
                offres.add(o);
                types.add(t);
                
                map.put(1,offres);
                map.put(2,types);
                
            }
            
            
        } catch (IOException ex) {
            
        }
     
        return map;
    }

}
