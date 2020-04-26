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
    public Map<Integer,List> map = new HashMap();
    
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
        return offres;
     }
     
       public Map<Integer,List> parseOffres(String jsonText) throws ParseException{
        try {
            offres=new ArrayList<>();
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
                o.setDate(date_new);
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
