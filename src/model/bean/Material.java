/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author lucas
 */
public class Material {
    private int id;
    private String nome;
    private int qt_estq;

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

    public int getQt_estq() {
        return qt_estq;
    }

    public void setQt_estq(int qt_estq) {
        this.qt_estq = qt_estq;
    }

    @Override
    public String toString() {
        return  getNome(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
