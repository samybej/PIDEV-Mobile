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
import com.codename1.ui.events.ActionListener;
import entities.Reservation;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import utils.Statics;
/**
 *
 * @author Iyadhtr
 */
public class ServiceReservation {
    
    
      public ArrayList<Reservation> reservations;
     
     
    public static ServiceReservation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceReservation() {
         req = new ConnectionRequest();
    }

    public static ServiceReservation getInstance() {
        if (instance == null) {
            instance = new ServiceReservation();
        }
        return instance;
    }
    
    public boolean ajoutReservation(Reservation r) {
        String url = Statics.BASE_URL + "/api/reservations/" + r.getDepart()+ "/" + r.getArrive()+ "/" + r.getDate()+ "/" + r.getType()+ "/" + "/" + r.getTarif()+ "/" + r.getIdChauffeur()+ "/" + r.getIdClient();
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
    
    
     public ArrayList<Reservation> getReservations()
     {
     
        String url = Statics.BASE_URL+"api/reservations/209";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    reservations = parseReservations(new String(req.getResponseData()));
                } catch (ParseException ex) {
                   
                }
                    req.removeResponseListener(this);
             
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reservations;
        
     }
     
     
     public  ArrayList<Reservation> parseReservations(String jsonText) throws ParseException{
        try {
            reservations=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> reservationsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)reservationsListJson.get("root");
            for(Map<String,Object> obj : list){
                Reservation r = new Reservation();
                
                float id = Float.parseFloat(obj.get("id").toString());
                 float distance = Float.parseFloat(obj.get("distance").toString());
                 float tarif = Float.parseFloat(obj.get("tarif").toString());
                float idChauffeur = Float.parseFloat(((Map)obj.get("idChauffeur")).get("id").toString());
                float idClient = Float.parseFloat(((Map)obj.get("idClient")).get("id").toString());
                
                String date = obj.get("date").toString();
                String type = obj.get("type").toString();
                Date date_new = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                r.setId((int)id);
               r.setDistance(distance);
                r.setDepart(obj.get("depart").toString());
                r.setArrive(obj.get("arrive").toString());
                
                r.setDate(date_new);
              r.setType(type);
              r.setTarif(tarif);
                r.setIdChauffeur((int)idChauffeur);
                r.setIdClient((int)idClient);
               
                System.out.println(r);
                
                reservations.add(r);
            }
            
            
        } catch (IOException ex) {
            
        }
        return reservations;
    }

    
}
