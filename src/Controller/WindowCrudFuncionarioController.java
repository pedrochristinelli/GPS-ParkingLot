package Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class WindowCrudFuncionarioController {
    @FXML
    TableView<String> tableFuncionario;
    @FXML
    TableColumn<String, String> funcaoColum;
    @FXML
    TableColumn<String, String> telefoneColum;
    @FXML
    TableColumn<String, String> enderecoColum;
    @FXML
    TableColumn<String, String> nomeColum;
    @FXML
    TableColumn<String, String> cpfColum;

    @FXML
    JFXTextField tfBuscaFuncionario;

    public void telaNovoFuncionario(ActionEvent actionEvent) {
    }

    public void editaFuncionario(ActionEvent actionEvent) {
    }

    public void removeFuncionario(ActionEvent actionEvent) {
    }
}
