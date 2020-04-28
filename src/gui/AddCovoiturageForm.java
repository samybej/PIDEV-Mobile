/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.SOUTH;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextComponent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.Validator;
import static java.lang.Float.NaN;

/**
 *
 * @author Asus
 */
public class AddCovoiturageForm extends Form {
    
    public AddCovoiturageForm()
    {
        TextComponent nbPlace = new TextComponent().labelAndHint("Nombre de places");
        
        TextComponent depart = new TextComponent().labelAndHint("Lieu de depart");
        
        TextComponent arrive = new TextComponent().labelAndHint("Lieu d'arrivee");
        
       // FontImage.setMaterialIcon(name.getField().getHintLabel(), FontImage.);
       
       PickerComponent date = PickerComponent.createDate(null).label("Date");
       PickerComponent time = PickerComponent.createTime(60).label("Heure");
        
       TextComponent tarif = new TextComponent().labelAndHint("Tarif");
    
       FontImage.setMaterialIcon(tarif.getField().getHintLabel(), FontImage.MATERIAL_MONEY);
       
       TextComponent vehicule = new TextComponent().labelAndHint("Vehicule");
       FontImage.setMaterialIcon(vehicule.getField().getHintLabel(), FontImage.MATERIAL_CAR_RENTAL);
       
       Picker bagage = new Picker();
       bagage.setType(Display.PICKER_TYPE_STRINGS);
       bagage.setSelectedString("Pas de bagage");
       bagage.setStrings("Pas de bagage","entre 1 et 5 kg","entre 5 et 10kg"," > 10kg ");
       Label errorNbPlace = new Label(" ");
       Validator val = new Validator();
       val.setValidationFailureHighlightMode(Validator.HighlightMode.UIID);
       val.addConstraint(nbPlace, new NumericConstraint(true, 1, 5, "Nombre de places"));
       
       
       Button save = new Button("Save");
        save.setUIID("InputAvatarImage");
        
         TextModeLayout tl = new TextModeLayout(4, 2);
        Container comps = new Container(tl);
        comps.add(tl.createConstraint().widthPercentage(70), nbPlace);
        comps.add(tl.createConstraint().widthPercentage(30), depart);
       // comps.add(tl.createConstraint().horizontalSpan(2), depart);
        comps.add(tl.createConstraint().horizontalSpan(2), arrive);
        comps.add(tl.createConstraint().horizontalSpan(2), date);
        comps.add(tl.createConstraint().horizontalSpan(2), time);
        comps.add(tl.createConstraint().horizontalSpan(2), tarif);
        comps.add(tl.createConstraint().horizontalSpan(2), vehicule);
        comps.add(tl.createConstraint().horizontalSpan(2), bagage);
        
        comps.setScrollableY(true);
        comps.setUIID("PaddedContainer");
        
        Container content = BorderLayout.center(comps);
        
        content.add(SOUTH, save);
        save.addActionListener(e -> {
            ToastBar.showMessage("Save pressed...", FontImage.MATERIAL_INFO);
        });
        
        content.setUIID("InputContainerForeground");
        
        addAll(content);
       
        
        
        
    }
    
   /* public Container createCovoiturage(Form parent)
    {    
        
    }*/
}
