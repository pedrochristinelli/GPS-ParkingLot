package Controller;

import Model.Entities.Funcionarios.Administrador;
import Model.Entities.Vagas.Vagas;
import Model.UseCases.FuncionarioCRUDUseCase;
import Model.UseCases.RegistroHoraFuncionarioUseCase;
import Model.UseCases.VagasUseCase;
import Utils.MaskFieldUtil;
import View.loaders.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.sql.SQLException;


public class WindowAdminController {
    public Administrador adm;

    @FXML
    JFXButton btnDeslogar;
    @FXML
    Label lblNumFuncionarios;
    @FXML
    Label lblNumVagasDisponiveis;
    @FXML
    Label lblNumVagas;
    @FXML
    PieChart graphVagas;
    @FXML
    JFXTextField filterCpf;
    @FXML
    TableView<Vagas> carrosEstacionadosTable;
    @FXML
    TableColumn<Vagas, String> vagaColum;
    @FXML
    TableColumn<Vagas, String> cpfColum;

    private ObservableList<Vagas> values;

    public void openTelaTicket(ActionEvent actionEvent) throws SQLException {
        WindowTicket w = new WindowTicket();
        w.startModal();
        reloader();
    }

    public void deslogaAdmin(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) btnDeslogar.getScene().getWindow();
        stage.close();
        WindowStart windowStart = new WindowStart();
        windowStart.start(new Stage());
    }

    public void openTelaPrecos(ActionEvent actionEvent) throws SQLException {
        WindowAlteraPrecos w = new WindowAlteraPrecos();
        w.startModal();
        reloader();
    }

    public void openTelaCRUDFuncionarios(ActionEvent actionEvent) throws Exception{
        WindowCrudFuncionario w = new WindowCrudFuncionario();
        w.startModal();
        reloader();
    }

    public void openTelaPagamento(ActionEvent actionEvent) throws SQLException {
        WindowGeraPagamento windowGeraPagamento = new WindowGeraPagamento();
        windowGeraPagamento.startModal(null);
        reloader();
    }

    public void setVagasDisponiveis() throws SQLException {
        VagasUseCase vagasUseCase = new VagasUseCase();
        lblNumVagasDisponiveis.setText("Vagas Disponíveis: "+vagasUseCase.numeroVagasDisponiveis());
    }

    public void setVagasTotais() throws SQLException {
        VagasUseCase vagasUseCase = new VagasUseCase();
        lblNumVagas.setText("Número de vagas: "+vagasUseCase.numeroVagasTotais());
    }

    public void setGraphVagas() throws SQLException {
        VagasUseCase vagasUseCase = new VagasUseCase();
        int numeroVagasDisponiveis = vagasUseCase.numeroVagasDisponiveis();
        int numeroOcupadas = vagasUseCase.numeroVagasTotais() - numeroVagasDisponiveis;
        graphVagas.getData().clear();
        graphVagas.getData().addAll(new PieChart.Data("Vagas Utilizadas", numeroOcupadas),
                new PieChart.Data("Vagas Disponiveis", numeroVagasDisponiveis));
    }

    public void reloader() throws SQLException {
        setVagasTotais();
        setGraphVagas();
        setVagasDisponiveis();
        initialize();
    }

    public void setAdm(Administrador adm) {
        this.adm = adm;
    }

    public void searchCpfFormatter(KeyEvent keyEvent) {
        MaskFieldUtil.cpfField(filterCpf);
    }

    public void abrirPagamento(MouseEvent mouseEvent) throws SQLException {
        String cpf = carrosEstacionadosTable.getSelectionModel().getSelectedItem().getCpf_ocupante();
        if(cpf.equals("Disponível"))
            return;
        WindowGeraPagamento windowGeraPagamento = new WindowGeraPagamento();
        windowGeraPagamento.startModal(cpf);
        reloader();
    }

    @FXML
    private void initialize() throws SQLException {
        vagaColum.setCellValueFactory(new PropertyValueFactory<>("id_vaga"));
        cpfColum.setCellValueFactory(new PropertyValueFactory<>("cpf_ocupante"));
        values = FXCollections.observableArrayList();
        carrosEstacionadosTable.setItems(values);
        loadTableView();
    }

    private void loadTableView() throws SQLException {
        VagasUseCase vagasUseCase = new VagasUseCase();

        values.setAll(vagasUseCase.readAll());

        FilteredList<Vagas> filteredData = new FilteredList<>(values, b ->  true);

        filterCpf.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredData.setPredicate(vaga -> {

                if(newValue == null || newValue.isEmpty())
                    return  true;

                String lowerCaseFilter = newValue.toLowerCase();

                if(vaga.getCpf_ocupante().indexOf(lowerCaseFilter) != -1){
                    return  true;
                }
                else
                    return false;
            });

            SortedList<Vagas> sortedData = new SortedList<>(filteredData);

            carrosEstacionadosTable.setItems(sortedData);

        }));

    }
}