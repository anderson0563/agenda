package Banco;

import Modelo.Agenda;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;


public class AgendaDAO {
    Connection banco;

    public AgendaDAO() throws SQLException, ClassNotFoundException {
        banco = (new Banco.Conexao().Conexao());
    }

    public boolean inserir(Agenda agenda) throws SQLException {
        PreparedStatement statement = banco.prepareStatement("INSERT INTO CONTATO (`nome`, `telefone`) VALUES (?, ?)");
        statement.setString(1, agenda.getNome());
        statement.setString(2, agenda.getTelefone());
        return statement.executeUpdate()>0;
    }
    public boolean remover(int indice) throws SQLException {
        PreparedStatement statement = banco.prepareStatement("DELETE FROM CONTATO WHERE `indice`=?");
        statement.setInt(1, indice);
        return statement.executeUpdate()>0;
    }
    public boolean atualizar(int indice, Agenda agenda) throws SQLException {
        PreparedStatement statement = banco.prepareStatement("UPDATE CONTATO SET `nome`=?, `telefone`=? WHERE `indice`=?");
        statement.setString(1, agenda.getNome());
        statement.setString(2, agenda.getTelefone());
        statement.setInt(3, indice);
        return statement.executeUpdate()>0;
    }

    public ArrayList<Agenda> listar() throws SQLException {
        PreparedStatement statement = banco.prepareStatement("SELECT * FROM CONTATO");

        ResultSet resultado = statement.executeQuery();
        ArrayList lista = new ArrayList<>();
        Agenda contato;
        while(resultado.next()){
            contato = new Agenda(resultado.getInt("indice"),
                                 resultado.getString("nome"),
                                 resultado.getString("telefone"));
            lista.add(contato);
        }
        return lista;
    }

    public Agenda pegarContato(int indice) throws SQLException {
        PreparedStatement statement = banco.prepareStatement("SELECT * FROM CONTATO WHERE id=?");
        statement.setInt(1, indice);
        ResultSet resultado = statement.executeQuery();
        resultado.next();
        Agenda contato = new Agenda(resultado.getInt("indice"),
                    resultado.getString("nome"),
                    resultado.getString("telefone"));
        return contato;
    }

}
