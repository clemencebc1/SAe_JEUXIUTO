package vue;
import javafx.beans.value.*;

import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ControleurFieldNB implements ChangeListener<Boolean>{
    private TextField tfId;
    private List<String> carac;
    public ControleurFieldNB(TextField id){
        this.tfId = id;
        this.carac = Arrays.asList("0","1","2","3","4","5","6","7","8","9");
        }
    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
    {  
        if (!newValue) { // si on a perdu le focus
            for (int i =0; i<this.tfId.getText().length();i++){
                Character c = this.tfId.getText().charAt(i);
                if (!(this.carac.contains(c.toString()))){
                    this.tfId.setStyle("-fx-background-color: lightpink; -fx-border-color: red;");
                }
                else {
                    this.tfId.setStyle(null);
                }
            }
    }
    }
}