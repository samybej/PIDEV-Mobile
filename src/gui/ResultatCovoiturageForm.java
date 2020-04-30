/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import entities.Client;
import entities.Offre;
import java.util.List;
import java.util.Map;
import services.ServiceOffre;

/**
 *
 * @author Asus
 */
public class ResultatCovoiturageForm extends Form{
    
    public ResultatCovoiturageForm(String date,String depart,String arrive)
    {
        
         Map<Offre,Client> mapResultat = ServiceOffre.getInstance().rechercherCovoiturage(date, depart, arrive);
         
         Container c1 = new Container(BoxLayout.y());
         
         for (Map.Entry<Offre,Client> entry : mapResultat.entrySet())
         {
             Offre o = entry.getKey();
             Client c = entry.getValue();
             
             Label lbDepart = new Label(o.getDepart());
             Label lbArrive = new Label(o.getArrive());
             Label lbTarif = new Label(String.valueOf(o.getTarif()));
             Label lbVehicule = new Label(o.getVehicule());
             Label lbBagage = new Label(o.getBagage());
             
             Container c2 = new Container(BoxLayout.y());
             c2.add("Depart : " + lbDepart.getText() + " , Arrivee : " + lbArrive.getText());
             c2.add("Tarif : "+ lbTarif.getText());
             c2.add("Vehicule : "+ lbVehicule.getText());
             c2.add("Bagage : " + lbBagage.getText());
             
             Button btn = new Button("S'inscrire");
             c2.add(btn);
             
             
             
             btn.addPointerPressedListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent evt) {
                      
                 if (ServiceOffre.getInstance().inscriOffre(o.getId(), 209, o.getIdOffreur()))  
                    {
                       Dialog.show(" Success "," Inscription terminée ! ",new Command("OK"));
                    }
                 else 
                 {
                     Dialog.show(" Failed "," Erreur ! ",new Command("OK"));
                     
                 }
                 
                 }
             });
             
             
             c1.add(c2);
         }
         
         getToolbar().addCommandToSideMenu("rechercher", null, new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
                new RechercherCovoiturageForm().show();
             }
         });
         getToolbar().addCommandToSideMenu("ajouter", null, new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
                new AddCovoiturageForm().show();
             }
         });
         getToolbar().addCommandToSideMenu("mes covoiturages", null, new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
                 new ListeCovoituragesForm(new BorderLayout()).show();
             }
         });
         
         setBackCommand(new Command("back"){
             @Override
            public void actionPerformed(ActionEvent evt) {
                new RechercherCovoiturageForm().showBack();
            }
         });
         add(c1);
     
    }
    
}
