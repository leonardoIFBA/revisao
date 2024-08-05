package com.example.repository;

import java.util.List;

import com.example.JpaUtil;
import com.example.model.Professor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class ProfessoresJPA {
    private EntityManager manager;

    public ProfessoresJPA(){
        this.manager = JpaUtil.getEntityManager();
    }
    public void adicionar(Professor professor){
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.persist(professor);
        tx.commit();
    }
    public void atualizar(Professor professor){
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.merge(professor);
        tx.commit();
    }
    public void excluir(Professor professor){
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.persist(professor);
        tx.commit();
    }

    public List<Professor> listarTodos(){
        TypedQuery<Professor> query = manager.createQuery("from Professor", Professor.class);
        return query.getResultList();
    }
    public Professor porId(Integer id){
        return manager.find(Professor.class, id);
    }
}
