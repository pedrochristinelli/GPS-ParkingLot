package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class WindowAdminController {
    @FXML
    JFXButton btnDeslogar;
    @FXML
    Label lblNumFuncionarios;
    @FXML
    Label lblNumMensalistas;
    @FXML
    Label lblNumVagasDisponiveis;
    @FXML
    Label lblNumVagas;
    @FXML
    PieChart graphVagas;
    @FXML
    TableView<String> carrosEstacionadosTable;
    @FXML
    TableColumn<String, String> vagaColum;
    @FXML
    TableColumn<String, String> cpfColum;
    @FXML
    JFXTextField filterCpf;

    public void deslogaAdmin(ActionEvent actionEvent) {
    }

    public void openTelaPrecos(ActionEvent actionEvent) {
    }

    public void openTelaPagamento(ActionEvent actionEvent) {
    }

    public void openTelaTicket(ActionEvent actionEvent) {
    }

    public void openTelaCRUDFuncionarios(ActionEvent actionEvent) {
    }

    public void abrirPagamento(MouseEvent mouseEvent) {
    }

    public void searchCpfFormatter(KeyEvent keyEvent) {
    }
}