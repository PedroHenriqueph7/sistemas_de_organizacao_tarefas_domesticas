package org.projeto.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.projeto.dataBase.DB;
import org.projeto.dataBase.DBException;
import org.projeto.models.CategoriaTarefa;

public class CategoriaTarefaRepository {
     private Connection connection;

    public CategoriaTarefaRepository(Connection connection){
        this.connection = connection;
    }

    public void insert(CategoriaTarefa categoriaTarefa){
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                "INSERT INTO categoria_tarefa "
                + "(category_name) "
                + "VALUES (?) ", Statement.RETURN_GENERATED_KEYS
                );

                preparedStatement.setString(1, categoriaTarefa.getCategory_name());
              

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    ResultSet resultSet = preparedStatement.getGeneratedKeys();
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        categoriaTarefa.setId(id);
                        System.out.println("One rows affected!");
                    }
                    DB.closeResultSet(resultSet);
                } else {
                    System.out.println("No affected rows! Erro in insert person");
                }

        } catch (Exception e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }
    }

    public void deleteById(Integer id){
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                "DELETE FROM categoria_tarefa "
                + "WHERE id = (?)"
            );

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("One rows affected!");
            } else {
                System.out.println("No rows affected!");
            }
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }
    }
}
