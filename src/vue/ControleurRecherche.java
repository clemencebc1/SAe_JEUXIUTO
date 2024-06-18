package vue;
import javafx.beans.value.*;

import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ControleurRecherche implements ChangeListener<Boolean>{
    private TextField tfRecherche;
    public ControleurRecherche(TextField r){
        this.tfRecherche = r;
        }
    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean
    newValue)
    {  
        if (!newValue) { // si on a perdu le focus
            
            }
    }
    }
