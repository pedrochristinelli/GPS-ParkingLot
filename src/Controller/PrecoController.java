package Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PrecoController {
    @FXML
    JFXTextField tfTaxa30min;
    @FXML
    JFXTextField tfTaxa1hora;
    @FXML
    JFXTextField tfTaxaDemaisHrs;
    @FXML
    JFXTextField tfTaxaPernoite;
    @FXML
    JFXTextField tfTaxaMensalista;
    @FXML
    Label lblAviso;

    public void salvaPrecosEstacionamento(ActionEvent actionEvent) {
    }
}
