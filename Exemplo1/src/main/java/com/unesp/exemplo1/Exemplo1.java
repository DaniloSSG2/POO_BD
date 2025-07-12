/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.unesp.exemplo1;

import com.unesp.exemplo1.controller.UsuarioController;
import com.unesp.exemplo1.dao.Conexao;
import com.unesp.exemplo1.dao.UsuarioDAO;
import com.unesp.exemplo1.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author davi
 */
public class Exemplo1 {

    public static void main(String[] args) {
        try {
            /*
            // Faz o usu´rio a ser inserido. Pega de uma tela, recebe via HTTP, etc
            Usuario usuario = new Usuario(3, "José", "jose@hotmail.com");
            UsuarioController usuarioController = new UsuarioController();
            //usuarioController.insertUser(usuario);
            
            Usuario user = usuarioController.getUserById(2);
            System.out.println("Usuario obtido do BD: ");
            System.out.println(user.getEmail());
            */
            
            // Commit
            UsuarioController usuarioController = new UsuarioController();
            Usuario usuario1 = new Usuario(5, "Anderson", "anderson@hotmail.com");
            Usuario usuario2 = new Usuario(6, "Alfredo", "alfredo@hotmail.com");
            //usuarioController.insertDoisUsuariosAtomico(usuario1, usuario2);
            usuarioController.apagar(10);
           
        } catch (SQLException ex) {
            Logger.getLogger(Exemplo1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
