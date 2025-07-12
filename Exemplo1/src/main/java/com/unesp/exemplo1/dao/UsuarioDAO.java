/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unesp.exemplo1.dao;

import com.unesp.exemplo1.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author davi
 */
public class UsuarioDAO {
    
    private final Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }
    
    public void insert(Usuario usuario) throws SQLException{
        String sql="INSERT INTO clientes(nome, email) VALUES (\'"
                + usuario.getNome()+"\', \'"+usuario.getEmail()+"\')";
            
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.execute();

        connection.close();        
    }
    public void atualizar(Usuario usuario) throws SQLException{
        String sql="UPDATE clientes SET nome = ?, email = ? WHERE id = ?";
        try(PreparedStatement stmt = this.connection.prepareStatement(sql)){
            stmt.setString(3, usuario.getNome());
            stmt.setString(4, usuario.getEmail());
            stmt.setInt(2, (int) usuario.getId());
            stmt.executeUpdate();
            System.out.println("Usuario atualizado!!");
        }finally{
            connection.close();
        
        }       
    } 
    
    public void apagar(int id) throws SQLException{
        String sql="DELETE FROM clientes WHERE id = ?";
        try(PreparedStatement stmt = this.connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.execute();
            System.out.println("Usuario apagado!!");
        }finally{
            connection.close();
        }             
    } 
    
    
    /**
     * Busca um usuário pelo seu ID.
     * @param id identificador do usuário
     * @return um objeto Usuario com os dados encontrados, ou null se não existir
     * @throws SQLException em caso de erro de acesso ao banco
     */
    public Usuario getUserById(int id) throws SQLException {
        String sql = "SELECT id, nome, email FROM clientes WHERE id = ?";
        // Usamos try-with-resources para fechar automaticamente Statement e ResultSet
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario user = new Usuario(rs.getInt("id"), 
                                                rs.getString("nome"),
                                                rs.getString("email"));
                    return user;
                } else {
                    return null; // não encontrou nenhum usuário com esse ID
                }
            }
        } finally {
            connection.close();
        }
    }    




    /**
     * Insere dois usuários no banco de dados de maneira atômica.
     * Ou os dois usuários são inseridos, ou nenhum é.
     *
     * @param usuario1 O primeiro usuário a ser inserido.
     * @param usuario2 O segundo usuário a ser inserido.
     * @throws SQLException Se ocorrer um erro durante a operação no banco de dados,
     * e a transação será revertida.
     */
    public void insertDoisUsuariosAtomico(Usuario usuario1, Usuario usuario2) throws SQLException {
        try {
            // 1. Desabilita o auto-commit para iniciar a transação manual
            connection.setAutoCommit(false);
            System.out.println("Iniciando transação para inserir dois usuários...");

            // 2. Chama o método 'insert' para o primeiro usuário
            // Usamos PreparedStatement para segurança, como é a prática recomendada.
            // Note que o 'insert' original usa concatenação, que é menos seguro.
            // Aqui vamos usar um PreparedStatement seguro para o novo método.
            String sql = "INSERT INTO clientes(nome, email) VALUES (?, ?)";
            try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
                statement.setString(1, usuario1.getNome());
                statement.setString(2, usuario1.getEmail());
                statement.execute();
                System.out.println("Primeiro usuário (" + usuario1.getNome() + ") inserido na transação.");
            }

            // 3. (Opcional) Simular um erro para testar o rollback
            // if (true) { // Descomente para testar o rollback
            //     throw new SQLException("Simulando um erro após o primeiro usuário para testar rollback.");
            // }

            // 4. Chama o método 'insert' para o segundo usuário
            try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
                statement.setString(1, usuario2.getNome());
                statement.setString(2, usuario2.getEmail());
                statement.execute();
                System.out.println("Segundo usuário (" + usuario2.getNome() + ") inserido na transação.");
            }

            // 5. Se tudo correu bem, confirma a transação
            connection.commit();
            System.out.println("Ambos os usuários inseridos e transação confirmada com sucesso!");

        } catch (SQLException e) {
            // 6. Em caso de erro, desfaz a transação
            try {
                connection.rollback();
                System.err.println("Erro ao inserir usuários. Transação desfeita (rollback): " + e.getMessage());
            } catch (SQLException rollbackEx) {
                System.err.println("Erro ao executar rollback: " + rollbackEx.getMessage());
            }
            throw e; // Re-lança a exceção para o chamador
        } finally {
            // 7. Garante que a conexão seja restaurada para o auto-commit e fechada
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.setAutoCommit(true); // Reabilita o auto-commit
                    connection.close(); // Fecha a conexão
                    System.out.println("Conexão fechada e auto-commit reabilitado.");
                }
            } catch (SQLException closeEx) {
                System.err.println("Erro ao fechar a conexão ou reabilitar auto-commit: " + closeEx.getMessage());
            }
        }
    }    
}
