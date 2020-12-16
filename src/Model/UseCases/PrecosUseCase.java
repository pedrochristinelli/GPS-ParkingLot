package Model.UseCases;

import Model.Entities.Precos.Precos;
import Utils.SqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrecosUseCase {
    Connection connection;

    public PrecosUseCase(){
        connection = SqlConnection.getConnection();
        if(connection == null) System.exit(1);
    }

    public void update(Precos precos) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            String sql2 = "UPDATE precos SET uma_hora = ?, meia_hora = ?, demais_horas = ?, taxa_noite = ?";
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setDouble(1, precos.getPreco1hr());
            preparedStatement.setDouble(2, precos.getPreco30min());
            preparedStatement.setDouble(3, precos.getPrecoDemaisHoras());
            preparedStatement.setDouble(4, precos.getPrecoPerNoite());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(Precos precos) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            String sql2 = "INSERT INTO precos(uma_hora, meia_hora, demais_horas, taxa_noite) VALUES(?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setDouble(1, precos.getPreco1hr());
            preparedStatement.setDouble(2, precos.getPreco30min());
            preparedStatement.setDouble(3, precos.getPrecoDemaisHoras());
            preparedStatement.setDouble(4, precos.getPrecoPerNoite());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Precos read() throws SQLException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM precos";
        Precos precos = null;
        try{
            preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                precos = new Precos(rs.getDouble("meia_hora"),rs.getDouble("uma_hora"),rs.getDouble("demais_horas"),rs.getDouble("taxa_noite"));
            }   else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return precos;
    }
}
