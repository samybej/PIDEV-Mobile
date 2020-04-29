/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.SOUTH;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextComponent;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.TextModeLayout;
import entities.Offre;
import entities.Type;
import java.util.Date;
import services.ServiceOffre;
import services.ServiceType;

/**
 *
 * @author Asus
 */
public class AddTypeForm extends Form {
    
    public AddTypeForm(int idCovoiturage)
    {
        TextComponent vitesse = new TextComponent().labelAndHint("Vitesse");
        
        TextComponent nbrArrets = new TextComponent().labelAndHint("Nombre d'arrets / Pauses");
        
        TextComponent tmpArret = new TextComponent().labelAndHint("Temps total d'arrets");
        
         Button save = new Button("Save");
        save.setUIID("InputAvatarImage");
        
        TextModeLayout tl = new TextModeLayout(4, 2);
        Container comps = new Container(tl);
        comps.add(tl.createConstraint().widthPercentage(70), vitesse);
        comps.add(tl.createConstraint().widthPercentage(30), nbrArrets);
       // comps.add(tl.createConstraint().horizontalSpan(2), depart);
        comps.add(tl.createConstraint().horizontalSpan(2), tmpArret);
        
        comps.setScrollableY(true);
        comps.setUIID("PaddedContainer");
        
        Container content = BorderLayout.center(comps);
           content.add(SOUTH, save);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 try {
         
                        Type t = new Type(Integer.parseInt(vitesse.getText()),Integer.parseInt(nbrArrets.getText())
                        ,Integer.parseInt(tmpArret.getText()),idCovoiturage);
                        if( ServiceType.getInstance().addType(t))
                        {
                           Dialog.show("Success","Offre Ajoutée !",new Command("OK"));
                            new ListeCovoituragesForm(new BorderLayout()).show(); 
                        }                         
                           
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                       System.out.println(t);
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
            }
        }
            
        );
        
         content.setUIID("InputContainerForeground");

        this.addAll(content);
        
    }
}
