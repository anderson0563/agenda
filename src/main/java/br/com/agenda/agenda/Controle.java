package br.com.agenda.agenda;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import Banco.AgendaDAO;
import Design.PaginaAdministrativa;
import Modelo.Agenda;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "controle", value = "/controle")
public class Controle extends HttpServlet {
    private String message;

    public void init() {
        message = "Minha Agenda";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String dpa="";
        {
            dpa = (new PaginaAdministrativa()).cabecalho;
        }
        out.println(dpa);
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

    public void destroy() {
    }
}