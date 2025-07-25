package org.projeto.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.projeto.dataBase.DB;
import org.projeto.dataBase.DBException;
import org.projeto.models.Tarefa;

public class TarefaRepository {
    private Connection connection;

    public TarefaRepository(Connection connection) {
        this.connection = connection;
    }

    public void insert(Tarefa task){
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                "INSERT INTO tarefa "
                +"(task_name, priority, status, responsible_personnel_id, category_id) "
                +"VALUES (?, ?, ?, ?, ?) ",
                Statement.RETURN_GENERATED_KEYS
            );

            preparedStatement.setString(1, task.getTask_name());
            preparedStatement.setString(2, task.getPriority().name());
            preparedStatement.setString(3, task.getStatus().name());
            preparedStatement.setInt(4, task.getPerson().getId());
            preparedStatement.setInt(4, task.getCategory().getId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        task.setId(id);
                    }
                System.out.println("One rows affected");
            } else {
                System.out.println("No rows affected");
            }

        } catch (Exception e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }
    }

    

    public void deleteById(Integer id) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                "DELETE FROM tarefa "
                 +"WHERE id = (?) "
            );

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("One row affected!");
            } else {
                System.out.println("No row affected!");
            }
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }
    }
}
