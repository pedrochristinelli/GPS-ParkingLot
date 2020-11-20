package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class WindowTicketController {
    @FXML
    Label lblErro;

    @FXML
    JFXButton btnCancelaTicket;

    @FXML
    JFXButton btnGerarTicket;

    @FXML
    JFXTextArea taDescCarroCliente;

    @FXML
    JFXTextField tfContatoCliente;

    @FXML
    JFXTextField tfPlacaCliente;

    @FXML
    JFXCheckBox cbTaxaPerNoite;

    @FXML
    JFXTextField tfCpfCliente;

    @FXML
    JFXTextField tfVagaOcupada;

    @FXML
    TableView<String> vagasDisponiveisTable;

    @FXML
    TableColumn<String, Integer> vagasDisponiveisColum;

    public void formatterCpfClient(KeyEvent keyEvent) {
    }

    public void geraTicket(ActionEvent actionEvent) {
    }

    public void selecionaVaga(MouseEvent mouseEvent) {
    }

    public void formatterTellClient(KeyEvent keyEvent) {
    }

    public void cancelaTicket(ActionEvent actionEvent) {
    }
}