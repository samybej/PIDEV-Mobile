/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entities.Offre;
import entities.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author Asus
 */
public class ServiceType {
    
    public ArrayList<Offre> offres;
    public ArrayList<Type> types;
    public Map<Integer,List> map = new HashMap();
    
    public static ServiceType instance=null;
    public boolean resultOK;
    public float id;
    private ConnectionRequest req;

    private ServiceType() {
         req = new ConnectionRequest();
    }

    public static ServiceType getInstance() {
        if (instance == null) {
            instance = new ServiceType();
        }
        return instance;
    }
    
       public boolean addType(Type t) {
        String url = Statics.BASE_URL + "api/type/" + t.getVitesse()+ "/" + t.getNbrArrets()+ "/" + t.getTmpArret()+ "/" + t.getIdOffre();
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
}
