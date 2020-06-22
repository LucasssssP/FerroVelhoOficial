/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;



/**
 *
 * @author lucas
 */
public class ProdutoDAO {

    public void create(Material p) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO material(nm_material, qt_kg_estq) VALUES (?,?)");
            stmt.setString(1, p.getNome());
            stmt.setInt(2, p.getQt_estq());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao salvar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Material> read() {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Material> produtos = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM material");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Material produto = new Material();

                produto.setId(rs.getInt("id_material"));
                produto.setNome(rs.getString("nm_material"));
                produto.setQt_estq(rs.getInt("qt_kg_estq"));
                produtos.add(produto);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar materiais:" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return produtos;
    }

    public void update(Material p) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE material SET nm_material = ?, qt_kg_estq = ? WHERE id_material = ?");
            stmt.setString(1, p.getNome());
            stmt.setInt(2, p.getQt_estq());
            stmt.setInt(3, p.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar:" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public void delete(Material p) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM material WHERE id_material = ?");
            stmt.setInt(1, p.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public boolean login(String s) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            
            stmt = con.prepareStatement("SELECT * FROM login WHERE senha = ?");
            stmt.setString(1, s);
            rs = stmt.executeQuery();            
            
            if(rs.next()){
                check = true;
            }
            
            

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de SQL" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return check;
    }
    
    public void venda(int m,float qt_kg, float vl_kg) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE material SET qt_kg_estq = qt_kg_estq - ? WHERE id_material = ?;");
            stmt.setFloat(1, qt_kg);
            stmt.setInt(2, m);
            stmt.executeUpdate();
            
            float valor = qt_kg * vl_kg;
            
            stmt = con.prepareStatement("INSERT INTO saida(qt_kg_saida, vl_kg_saida, id_material, dt_saida, hr_saida) VALUES (?,?,?,CURDATE(),CURTIME())");
            stmt.setFloat(1, qt_kg);
            stmt.setFloat(2, valor);
            stmt.setInt(3, m);
            stmt.executeUpdate();
            

            JOptionPane.showMessageDialog(null, "Vendido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar:" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    
    public void compra(int m,float qt_kg, float vl_kg) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE material SET qt_kg_estq = qt_kg_estq + ? WHERE id_material = ?;");
            stmt.setFloat(1, qt_kg);
            stmt.setInt(2, m);
            stmt.executeUpdate();
            
            float valor = qt_kg * vl_kg;
            //valor = valor *(-1);
            
            stmt = con.prepareStatement("INSERT INTO entrada(qt_kg_entrada, vl_kg_entrada, id_material, dt_entrada, hr_entrada) VALUES (?,?,?,CURDATE(),CURTIME())");
            stmt.setFloat(1, qt_kg);
            stmt.setFloat(2, valor);
            stmt.setInt(3, m);
            stmt.executeUpdate();
            

            JOptionPane.showMessageDialog(null, "Comprado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar:" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    
    public List<Entrada> readlogentrada() {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Entrada> produtos = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT m.nm_material, e.qt_kg_entrada, e.vl_kg_entrada, e.dt_entrada, e.hr_entrada FROM material m, entrada e WHERE e.id_material = m.id_material;");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Entrada log = new Entrada();
                log.setNome(rs.getString("nm_material"));
                log.setQt_kg_entrada(rs.getInt("qt_kg_entrada"));
                log.setVl_kg_entrada(rs.getFloat("vl_kg_entrada"));
                log.setData((rs.getDate("dt_entrada")));
                log.setHora((rs.getTime("hr_entrada")));
                produtos.add(log);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar materiais:" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return produtos;
    }
    public List<Entrada> readlogsaida() {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Entrada> produtos = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT m.nm_material, e.qt_kg_saida, e.vl_kg_saida, e.dt_saida,"
                    + " e.hr_saida FROM material m, saida e WHERE e.id_material = m.id_material;");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Entrada log = new Entrada();
                log.setNome(rs.getString("nm_material"));
                log.setQt_kg_entrada(rs.getInt("qt_kg_saida"));
                log.setVl_kg_entrada(rs.getFloat("vl_kg_saida"));
                log.setData((rs.getDate("dt_saida")));
                log.setHora((rs.getTime("hr_saida")));
                produtos.add(log);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar materiais:" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return produtos;
    }
}
