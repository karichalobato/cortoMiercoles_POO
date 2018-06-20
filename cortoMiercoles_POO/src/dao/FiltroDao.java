/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conexion;
import interfaces.metodos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Filtro;
/**
 *
 * @author LN710Q
 */
public class FiltroDao implements metodos<Filtro> {
    private static final String SQL_INSERT="INSERT INTO movie (idMovie, nombre, director, pais,clasificacion, anio, en_proyeccion)VALUES(?,?,?,?)";
    private static final String SQL_UPDATE="UPDATE movie SET nombre=?,anio=?,existencia=? WHERE nombre=?";
    private static final String SQL_DELETE="DELETE FROM movie WHERE nombre=?";
    private static final String SQL_READ="SELECT * FROM movie WHERE nombre=?";
    private static final String SQL_READALL="SELECT * FROM movie";
    private static final Conexion con = Conexion.conectar();
    @Override
    public boolean create(Filtro g) {
        PreparedStatement ps;
        try{
            ps= con.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1, g.getNombre());
            ps.setString(2, g.getPais());
            ps.setInt(3,g.getAño());
            ps.setString(4, g.getClasificacion());
            ps.setBoolean(5, true);
            if(ps.executeUpdate()>0){
                return true;
            }            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
        }
        return false;        
    }

    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try{
            ps=con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());
            if(ps.executeUpdate()>0){
                return true;
            }            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
        }
        return false;   
    }

    @Override
    public boolean update(Filtro c) {
        PreparedStatement ps;
        try{
            System.out.println(c.getNombre());
            ps=con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getDirector());
            ps.setInt(2,c.getAño());
            ps.setString(3,c.getClasificacion());
            if(ps.executeUpdate()>0){
                return true;
            }            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
        }
        return false;   
    }

    @Override
    public Filtro read(Object key) {
        Filtro f=null;
        PreparedStatement ps;
        ResultSet rs;
        try{
            ps=con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            rs=ps.executeQuery();
            
            while(rs.next()){
                f=new Filtro(rs.getString(1), rs.getInt(3),rs.getString(2), rs.getString(4),rs.getBoolean(5));
            }
            rs.close();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
        }
        return f;   
    }

    @Override
    public ArrayList<Filtro> readAll() {
        ArrayList<Filtro> all = new ArrayList<>();
        Statement s;
        ResultSet rs;
        try{
            s=con.getCnx().prepareStatement(SQL_READALL);
        }catch(SQLException ex){
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return all;
    }
}
