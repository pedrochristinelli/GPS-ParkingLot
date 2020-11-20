package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;

import java.sql.SQLException;

public class FuncionarioController {
    @FXML
    JFXButton btnCancelar;
    @FXML
    JFXTextField tfEnderecoFuncionario;
    @FXML
    JFXTextField tfTelefoneFuncionario;
    @FXML
    JFXTextField tfNomeFuncionario;
    @FXML
    JFXTextField tfCPFFuncionario;
    @FXML
    JFXComboBox<String> cbFuncFuncionario;
    @FXML
    JFXTextField tfSenhaFuncionario;
    @FXML
    Label lblAviso;


    public void salvarFuncionario(ActionEvent actionEvent){

    }

    public void cancelaOp(ActionEvent actionEvent) {

    }

    @FXML public void initialize(){

    }

    public void formataCpf(KeyEvent keyEvent) {
    }

    public void formataTelefone(KeyEvent keyEvent) {
    }
}
