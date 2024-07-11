package br.com.agenda.agenda;

import Banco.AgendaDAO;
import Modelo.Agenda;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "remover", value = "/remover")
public class Remover extends HttpServlet {

    private String message;

    public void init() {
        message = "Minha Agenda";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String indice = request.getParameter("id");
        try {
            if(new AgendaDAO().remover(Integer.parseInt(indice))){
                out.println("Removido com sucesso");
            }else{
                out.println("Erro!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        out.println("<h1>" + message + "</h1>");

        out.println("<table border=1>");
        out.println("<tr><th>Nome</th><th>Telefone</th><th>Operações</th></tr>");
        try {
            AgendaDAO banco = new AgendaDAO();
            ArrayList<Agenda> listaContatos = banco.listar();
            for(Agenda contato: listaContatos){
                out.println("<tr><td>"+contato.getNome()+"</td><td>"+contato.getTelefone()+"</td><td><a href=remover?id="+contato.getIndice()+">Remover</a><a href=atualizar?id="+contato.getIndice()+">Atualizar</a></td></tr>");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        out.println("<form action=inserir method=post>");
        out.println("<tr><th><input type=text name=nomea></th><th><input type=text name=telefone></th><th><input type=submit value=inserir></th></tr>");
        out.println("</form></table>");

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
