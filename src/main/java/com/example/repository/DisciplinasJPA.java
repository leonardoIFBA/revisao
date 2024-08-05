package com.example.repository;

import java.util.List;

import com.example.JpaUtil;
import com.example.model.Disciplina;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class DisciplinasJPA {
    private EntityManager manager;

    public DisciplinasJPA(){
        this.manager = JpaUtil.getEntityManager();
    }
    public void adicionar(Disciplina disciplina){
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.persist(disciplina);
        tx.commit();
    }
    public void atualizar(Disciplina disciplina){
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.merge(disciplina);
        tx.commit();
    }
    public void excluir(Disciplina disciplina){
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.persist(disciplina);
        tx.commit();
    }

    public List<Disciplina> listarTodos(){
        TypedQuery<Disciplina> query = manager.createQuery("from Disciplina", Disciplina.class);
        return query.getResultList();
    }
    public Disciplina porId(Integer id){
        return manager.find(Disciplina.class, id);
    }
}
