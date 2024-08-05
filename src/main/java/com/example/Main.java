package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.SQLException;

import com.example.model.Disciplina;
import com.example.model.Professor;
import com.example.repository.DisciplinasJDBC;
import com.example.repository.DisciplinasJPA;
import com.example.repository.ProfessoresJDBC;
import com.example.repository.ProfessoresJPA;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");
        Professor p = new Professor();
        p.setId(4);
        p.setNome("Paloma");
        p.setTelefone("99999-6666");
        p.setCurso("Espanhol");

        Disciplina d = new Disciplina();
        d.setId(4);
        d.setNome("Filosofia");
        d.setTurno("Vespertino");

        ProfessoresJPA professores = new ProfessoresJPA();
        professores.adicionar(p);
        professores.listarTodos();

        DisciplinasJPA disciplinas = new DisciplinasJPA();
        disciplinas.adicionar(d);
        disciplinas.listarTodos();

        Professor p2 = new Professor();
        p2.setId(5);
        p2.setNome("Thiago");
        p2.setTelefone("99999-5555");
        p2.setCurso("Filosofia");
        ProfessoresJDBC.inserir(p2);
        System.out.println(ProfessoresJDBC.listarTodos());

        Disciplina d2 = new Disciplina();
        d2.setId(5);
        d2.setNome("Espanhol");
        d2.setTurno("Vespertino");
        DisciplinasJDBC.inserir(d2);
        System.out.println(DisciplinasJDBC.listarTodos());
    }

    public static Connection getConexao() throws Exception {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/professorjpa?";
            String usuario = "root";
            String senha = "root";
            con = DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException e) {
           
            System.out.println("Não foi possível conectar ao BD");
            e.printStackTrace();
        }
        return con;
    }
}