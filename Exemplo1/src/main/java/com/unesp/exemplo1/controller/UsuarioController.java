/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unesp.exemplo1.controller;

import com.unesp.exemplo1.dao.Conexao;
import com.unesp.exemplo1.dao.UsuarioDAO;
import com.unesp.exemplo1.model.Usuario;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author davi
 */
public class UsuarioController {
    public void insertUser(Usuario user) throws SQLException{
        Connection conexao = new Conexao().getConnection();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
        usuarioDAO.insert(user);        
    }
    
    public Usuario getUserById(int id) throws SQLException{
        Connection conexao = new Conexao().getConnection();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
        return usuarioDAO.getUserById(id);           
    }
    
    public void insertDoisUsuariosAtomico(Usuario usuario1, Usuario usuario2) throws SQLException{
        Connection conexao = new Conexao().getConnection();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);        
        usuarioDAO.insertDoisUsuariosAtomico(usuario1, usuario2);
    }
    public void atualizar(Usuario usuario) throws SQLException{
        Connection conexao = new Conexao().getConnection();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
        usuarioDAO.atualizar(usuario);  
    }
     public void apagar(int id) throws SQLException{
        Connection conexao = new Conexao().getConnection();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
        usuarioDAO.apagar(id);  
    }
    
}
