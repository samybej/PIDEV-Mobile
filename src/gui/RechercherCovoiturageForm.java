/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextComponent;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.spinner.Picker;
import services.ServiceOffre;

/**
 *
 * @author Asus
 */
public class RechercherCovoiturageForm extends Form{
    
    public RechercherCovoiturageForm()
    {
         Picker date = new Picker();
         date.setFormatter(new SimpleDateFormat("yyyy-MM-dd"));
        
         TextComponent depart = new TextComponent().labelAndHint("Lieu de depart");
        
         TextComponent arrive = new TextComponent().labelAndHint("Lieu d'arrivee");
         
         Button save = new Button("rechercher");
        save.setUIID("InputAvatarImage");
        
        TextModeLayout tl = new TextModeLayout(4, 2);
        Container comps = new Container(tl);
        comps.add(tl.createConstraint().widthPercentage(30), depart);
       // comps.add(tl.createConstraint().horizontalSpan(2), depart);
        comps.add(tl.createConstraint().horizontalSpan(2), arrive);
        comps.add(tl.createConstraint().horizontalSpan(2), date);
        comps.add(tl.createConstraint().horizontalSpan(2), save);
          
        comps.setScrollableY(true);
        comps.setUIID("PaddedContainer");
        
        Container content = BorderLayout.center(comps);
        
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
      
                    try {
                        
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        String dateString = format.format(date.getDate());
                                              
                         new ResultatCovoiturageForm(dateString,depart.getText(),arrive.getText()).show();
                   
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                
                
                
            }
        });
        
        addAll(content);
         
        
    }
}
