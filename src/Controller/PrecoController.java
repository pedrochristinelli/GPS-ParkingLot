package Controller;

import Model.Entities.Precos.Precos;
import Model.UseCases.PrecosUseCase;
import javafx.fxml.FXML;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.sql.SQLException;

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
    Label lblAviso;

    private Precos preco;
    PrecosUseCase precosUseCase = new PrecosUseCase();

    public void salvaPrecosEstacionamento() throws SQLException{
        String taxa30min = tfTaxa30min.getText();
        String taxa1hr = tfTaxa1hora.getText();
        String taxaDemais = tfTaxaDemaisHrs.getText();
        String taxaPerNoite = tfTaxaPernoite.getText();

        if (!taxa30min.equals("") && !taxa1hr.equals("") && !taxaDemais.equals("") && !taxaPerNoite.equals("")){
            try {
                if(preco!=null){
                    precosUseCase.update(new Precos(Double.parseDouble(taxa30min) , Double.parseDouble(taxa1hr), Double.parseDouble(taxaDemais), Double.parseDouble(taxaPerNoite)));
                }   else{
                    precosUseCase.save(new Precos(Double.parseDouble(taxa30min) , Double.parseDouble(taxa1hr), Double.parseDouble(taxaDemais), Double.parseDouble(taxaPerNoite)));
                }
                ((Stage)tfTaxaDemaisHrs.getScene().getWindow()).close();
            }   catch (Exception e){
                lblAviso.setText("Preenchimento Inválido!");
            }
        }   else{
            lblAviso.setText("Preencha todos os campos!");
        }
    }

    public Precos readPrecos() throws SQLException {
        return precosUseCase.read();
    }

    public void editaPrecos(Precos precos) throws SQLException {
        tfTaxa30min.setText(precos.getPreco30min().toString());
        tfTaxa1hora.setText(precos.getPreco1hr().toString());
        tfTaxaDemaisHrs.setText(precos.getPrecoDemaisHoras().toString());
        tfTaxaPernoite.setText(precos.getPrecoPerNoite().toString());
    }

    public void setPreco(Precos preco) {
        this.preco = preco;
    }
}
