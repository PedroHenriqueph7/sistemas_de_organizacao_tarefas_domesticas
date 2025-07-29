package org.projeto.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.projeto.dataBase.DB;
import org.projeto.dataBase.DBException;
import org.projeto.models.Pessoa;

public class PessoaRepository {
    private Connection connection;

    public PessoaRepository(Connection connection){
        this.connection = connection;
    }

    public void insert(Pessoa person){
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                "INSERT INTO pessoa "
                + "(name, age) "
                + "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS
                );

                preparedStatement.setString(1, person.getName());
                preparedStatement.setInt(2, person.getAge());

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    ResultSet resultSet = preparedStatement.getGeneratedKeys();
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        person.setId(id);
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
                "DELETE FROM pessoa "
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
