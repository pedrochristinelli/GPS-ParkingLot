package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;


public class WindowGeraPagamentoController {

    @FXML
    JFXTextField tfCpfPagamento;

    @FXML
    JFXTextField tfHorarioEntrada;
    @FXML
    JFXTextField tfHorarioSaida;
    @FXML
    JFXTextField tfPlaca;
    @FXML
    JFXButton btnGeraPagamento;

    @FXML
    Label errorLabel;

    public void formatarCpf(KeyEvent keyEvent) {
    }

    public void buscarTicket(ActionEvent actionEvent) {
    }

    public void gerarPagamento(ActionEvent actionEvent) {
    }

    public void cancelar(ActionEvent actionEvent) {
    }
}
