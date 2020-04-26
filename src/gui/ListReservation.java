/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import services.ServiceReservation;
/**
 *
 * @author Iyadhtr
 */
public class ListReservation  extends Form {
    public ListReservation()
    {
        SpanLabel sp = new SpanLabel();
        sp.setText(ServiceReservation.getInstance().getReservations().toString());
        add(sp);
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
}
