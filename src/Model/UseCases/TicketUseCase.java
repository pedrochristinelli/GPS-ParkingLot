package Model.UseCases;

import Model.Entities.Precos.Precos;
import Model.Entities.Ticket.TicketCliente;
import Utils.SqlConnection;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TicketUseCase {
    Connection connection;
    private PrecosUseCase precosUseCase = new PrecosUseCase();
    public TicketUseCase(){
        connection = SqlConnection.getConnection();
        if(connection == null) System.exit(1);
    }

    public void saveClientTicket(TicketCliente ticket)  {
        PreparedStatement preparedStatement;
        String sqlTicket = "INSERT INTO Ticket_Cliente(horarioEntrada, placa, descricaoCarro, telefone, pernoite, cpf) VALUES(?,?,?,?,?,?)";
        try{
            String currentTimeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(ticket.getHorarioEntrada());
            preparedStatement = connection.prepareStatement(sqlTicket);
            preparedStatement.setString(1, currentTimeStamp);
            preparedStatement.setString(2, ticket.getPlaca());
            preparedStatement.setString(3, ticket.getDescricaoCarro());
            preparedStatement.setString(4, ticket.getTelefone());
            preparedStatement.setBoolean(5, ticket.isPernoite());
            preparedStatement.setString(6, ticket.getCpf());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isClienteTicket(String cpf) {
        PreparedStatement preparedStatement;
        int id = 0;
        ResultSet resultSet = null;
        String sql = "SELECT id FROM ticket_cliente WHERE cpf = ? and horarioSaida is null";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cpf);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (id != 0 );
    }

    public void pagamentoCliente(String cpf) throws SQLException {
        Precos precos = precosUseCase.read();
        TicketCliente ticketCliente = getOpenClienteTicketByCpf(cpf);
        ticketCliente.setHorarioSaida(new Date());
        Long different = Math.abs(ticketCliente.getHorarioSaida().getTime() - ticketCliente.getHorarioEntrada().getTime() );
        ticketCliente.setTempo(different);
        double hoursInMilli = 3600000;
        double elapsedHours = ((double)different / hoursInMilli);

        if(ticketCliente.isPernoite()){
            ticketCliente.setValorTotal(precos.getPrecoPerNoite());
        }
        else if(elapsedHours <= 0.5){
            ticketCliente.setValorTotal(precos.getPreco30min());
        } else if (elapsedHours <= 1.083){
            ticketCliente.setValorTotal(precos.getPreco1hr());
        } else {
            elapsedHours = Math.ceil(elapsedHours);
            double calculoPreco = 0 + precos.getPreco1hr();
            elapsedHours = elapsedHours - 1;
            calculoPreco = calculoPreco + (elapsedHours * precos.getPrecoDemaisHoras());
            ticketCliente.setValorTotal(calculoPreco);
        }


        PreparedStatement preparedStatement;
        ResultSet resultSet = null;
        String sql = "UPDATE ticket_cliente SET horarioSaida = ?, tempo = ?, valorTotal = ? WHERE cpf = ? and horarioSaida is null";
        try{
            String currentTimeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(ticketCliente.getHorarioSaida());
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, currentTimeStamp);
            preparedStatement.setLong(2, ticketCliente.getTempo());
            preparedStatement.setDouble(3, ticketCliente.getValorTotal());
            preparedStatement.setString(4, cpf);

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TicketCliente getOpenClienteTicketByCpf(String cpf){
        PreparedStatement preparedStatement;
        TicketCliente ticketCliente = new TicketCliente();
        ResultSet resultSet = null;
        String sql = "SELECT * FROM ticket_cliente " +
                "WHERE cpf = ? and horarioSaida is null";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cpf);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                ticketCliente.setId(rs.getInt("id"));
                ticketCliente.setCpf(rs.getString("cpf"));
                ticketCliente.setPernoite(rs.getBoolean("pernoite"));
                ticketCliente.setPlaca(rs.getString("placa"));
                ticketCliente.setTelefone(rs.getString("telefone"));
                ticketCliente.setDescricaoCarro(rs.getString("descricaoCarro"));
                ticketCliente.setHorarioEntrada(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(rs.getString("horarioEntrada")));
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return ticketCliente;
    }

    public TicketCliente getClienteTicketById(int id){
        PreparedStatement preparedStatement;
        TicketCliente ticketCliente = new TicketCliente();
        ResultSet resultSet = null;
        String sql = "SELECT * FROM ticket_cliente " +
                "WHERE id = ?";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                ticketCliente.setId(rs.getInt("id"));
                ticketCliente.setCpf(rs.getString("cpf"));
                ticketCliente.setPernoite(rs.getBoolean("pernoite"));
                ticketCliente.setPlaca(rs.getString("placa"));
                ticketCliente.setTelefone(rs.getString("telefone"));
                ticketCliente.setValorTotal(rs.getDouble("valorTotal"));
                ticketCliente.setDescricaoCarro(rs.getString("descricaoCarro"));
                ticketCliente.setHorarioEntrada(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(rs.getString("horarioEntrada")));
                ticketCliente.setHorarioSaida(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(rs.getString("horarioSaida")));
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return ticketCliente;
    }


    public List<TicketCliente> getAllClienteTicketsOnDate(String date) {
        List<TicketCliente> tickets = new ArrayList<>();
        try {

            String sql = "SELECT * FROM ticket_cliente WHERE horarioSaida IS NOT NULL and horarioEntrada LIKE ?";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, date + "%");
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    TicketCliente ticketCliente = new TicketCliente();
                    ticketCliente.setId(rs.getInt("id"));
                    ticketCliente.setCpf(rs.getString("cpf"));
                    ticketCliente.setPernoite(rs.getBoolean("pernoite"));
                    ticketCliente.setPlaca(rs.getString("placa"));
                    ticketCliente.setTelefone(rs.getString("telefone"));
                    ticketCliente.setValorTotal(rs.getDouble("valorTotal"));
                    ticketCliente.setDescricaoCarro(rs.getString("descricaoCarro"));
                    ticketCliente.setHorarioEntrada(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(rs.getString("horarioEntrada")));
                    ticketCliente.setHorarioSaida(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(rs.getString("horarioSaida")));
                    ticketCliente.setTempo(rs.getLong("tempo"));
                    tickets.add(ticketCliente);
                }

                return tickets;
            } catch (SQLException | ParseException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tickets;
    }

    /*Retorna o número do próximo ticket*/
    public int idProximoTicketCliente(){
        PreparedStatement preparedStatement;
        String sql = "SELECT max(id) as number FROM ticket_cliente";
        int id = 0;
        try{
            preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                id = rs.getInt("number");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id+1;
    }

}
