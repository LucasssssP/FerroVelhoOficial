/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author lucas
 */
public class Entrada {
    private int id;
    private String nome;
    private int qt_kg_entrada;
    private float vl_kg_entrada;
    private Date data;
    private Time hora;
    
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQt_kg_entrada() {
        return qt_kg_entrada;
    }

    public void setQt_kg_entrada(int qt_kg_entrada) {
        this.qt_kg_entrada = qt_kg_entrada;
    }

    public float getVl_kg_entrada() {
        return vl_kg_entrada;
    }

    public void setVl_kg_entrada(float vl__kg_entrada) {
        this.vl_kg_entrada = vl__kg_entrada;
    }

    
    

 
    
}
