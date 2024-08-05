package com.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.Main;
import com.example.model.Professor;

public class ProfessoresJDBC {
    public static List<Professor> listarTodos() throws SQLException, Exception{
        String sql = "select * from professor";
        List<Professor> professor = new ArrayList<>();

        try(Connection con = Main.getConexao()){
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Professor p = new Professor();
                p.setId(rs.getInt("Id"));
                p.setNome(rs.getString("nome"));
                p.setTelefone(rs.getString("telefone"));
                p.setCurso(rs.getString("curso"));
                professor.add(p);
            }
        }catch(SQLException e){
            System.out.println("Não foi possível listar os professores");
            e.printStackTrace();
        }
        return professor;
    }

    public static void inserir(Professor p) throws SQLException{
        String sql = "insert into professor (id, nome, telefone, curso)" + "values (?, ?, ?, ?)";
        try(Connection con = Main.getConexao()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,p.getId());
            ps.setString(2,p.getNome());
            ps.setString(3,p.getTelefone());
            ps.setString(4,p.getCurso());
            
            int resultado = ps.executeUpdate();
            if(resultado > 0){
                System.out.println("Professor cadastrado com sucesso!!!");
            }
        }catch(Exception e){
            System.out.println("Erro ao inserir um professor");
            e.printStackTrace();
        }
    }

    public static void alterar(Professor p, Integer id){
        String sql = "update professor set nome = ?, telefone = ?, curso = ?" + "where id = ?";
        try(Connection con = Main.getConexao()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,p.getNome());
            ps.setString(2,p.getTelefone());
            ps.setString(3,p.getCurso());
            ps.setInt(4, id);
            
            int resultado = ps.executeUpdate();
            if(resultado > 0){
                System.out.println("Professor alterado com sucesso!!!");
            }
        }catch(Exception e){
            System.out.println("Erro ao alterar um professor");
            e.printStackTrace();
        }
    }

    public static void excluir(Integer id){
        String sql = "delete from professor where id = ?";
        try(Connection con = Main.getConexao()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            
            int resultado = ps.executeUpdate();
            if(resultado > 0){
                System.out.println("Professor excluido com sucesso!!!");
            }
        }catch(Exception e){
            System.out.println("Erro ao excluir um professor");
            e.printStackTrace();
        }
    }
}
