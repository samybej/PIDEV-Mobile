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
import com.codename1.ui.events.ActionListener;
import entities.Offre;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author Asus
 */
public class ServiceOffre {
    
    public ArrayList<Offre> offres;
    
    public static ServiceOffre instance=null;
    public boolean resultOK;
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
    
     public boolean ajoutOffre(Offre o) {
        String url = Statics.BASE_URL + "/api/covoiturages/" + o.getNbPlace()+ "/" + o.getDepart()+ "/" + o.getArrive()+ "/" + o.getDate()+ "/" + o.getTime()+ "/" + o.getTarif()+ "/" + o.getIdOffreur()+ "/" + o.getIdClient()+ "/" + o.getVehicule()+ "/" + o.getBagage();
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
     
     public ArrayList<Offre> getCovoiturages()
     {
         String url = Statics.BASE_URL+"/tasks/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                offres = parseOffres(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return offres;
     }
     
       public ArrayList<Offre> parseOffres(String jsonText){
        try {
            offres=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> offresListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)offresListJson.get("root");
            for(Map<String,Object> obj : list){
                Offre o = new Offre();
                float id = Float.parseFloat(obj.get("id").toString());
                float nbPlace = Float.parseFloat(obj.get("nbPlace").toString());
                String date = obj.get("date").toString();
                Date date_new = Date.valueOf(date);
                o.setId((int)id);
                o.setNbPlace((int)nbPlace);
                o.setDepart(obj.get("depart").toString());
                o.setArrive(obj.get("arrive").toString());
                o.setDate(date_new);
                
                offres.add(o);
            }
            
            
        } catch (IOException ex) {
            
        }
        return offres;
    }

}
