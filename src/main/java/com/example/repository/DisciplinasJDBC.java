package com.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.Main;
import com.example.model.Disciplina;

public class DisciplinasJDBC {
    public static List<Disciplina> listarTodos() throws SQLException, Exception{
        String sql = "select * from disciplina";
        List<Disciplina> disciplina = new ArrayList<>();

        try(Connection con = Main.getConexao()){
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Disciplina d = new Disciplina();
                d.setId(rs.getInt("Id"));
                d.setNome(rs.getString("nome"));
                d.setTurno(rs.getString("turno"));
                disciplina.add(d);
            }
        }catch(SQLException e){
            System.out.println("Não foi possível listar as disciplinas");
            e.printStackTrace();
        }
        return disciplina;
    }

    public static void inserir(Disciplina d) throws SQLException{
        String sql = "insert into disciplina (id, nome, turno)" + "values (?, ?, ?)";
        try(Connection con = Main.getConexao()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,d.getId());
            ps.setString(2,d.getNome());
            ps.setString(3,d.getTurno());
            
            int resultado = ps.executeUpdate();
            if(resultado > 0){
                System.out.println("Disciplina cadastrada com sucesso!!!");
            }
        }catch(Exception e){
            System.out.println("Erro ao inserir uma disciplina");
            e.printStackTrace();
        }
    }

    public static void alterar(Disciplina d, Integer id){
        String sql = "update disciplina set nome = ?, turno = ?" + "where id = ?";
        try(Connection con = Main.getConexao()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,d.getNome());
            ps.setString(2,d.getTurno());
            ps.setInt(3, id);
            
            int resultado = ps.executeUpdate();
            if(resultado > 0){
                System.out.println("Disciplina alterada com sucesso!!!");
            }
        }catch(Exception e){
            System.out.println("Erro ao alterar uma disciplina");
            e.printStackTrace();
        }
    }

    public static void excluir(Integer id){
        String sql = "delete from disciplina where id = ?";
        try(Connection con = Main.getConexao()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            
            int resultado = ps.executeUpdate();
            if(resultado > 0){
                System.out.println("Disciplina excluida com sucesso!!!");
            }
        }catch(Exception e){
            System.out.println("Erro ao excluir uma disciplina");
            e.printStackTrace();
        }
    }
}