/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unesp.exemplo1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author davi
 */
public class Conexao {
       public Connection getConnection() throws SQLException{
           Connection conexao = DriverManager.getConnection(
                   "jdbc:postgresql://localhost:5432/nome_do_banco_de_dados", "postgres", "postgres"
           );
           return conexao;
       }
}
