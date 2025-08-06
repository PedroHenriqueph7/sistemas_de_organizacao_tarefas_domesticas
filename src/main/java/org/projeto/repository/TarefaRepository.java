package org.projeto.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.projeto.dataBase.DB;
import org.projeto.dataBase.DBException;
import org.projeto.models.CategoriaTarefa;
import org.projeto.models.Pessoa;
import org.projeto.models.Tarefa;
import org.projeto.models.enums.PrioridadeTarefa;
import org.projeto.models.enums.StatusTarefa;

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
            preparedStatement.setInt(5, task.getCategory().getId());

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

    public List<Tarefa> findAll() {

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                "SELECT t.*, p.name, ct.category_name FROM tarefa t "
                   +"INNER JOIN pessoa p ON t.responsible_personnel_id = p.id "
                   + "INNER JOIN categoria_tarefa ct ON t.category_id = ct.id");
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Tarefa> tasks = new ArrayList<>();
            Map<Integer, Pessoa> responsible = new HashMap<>();
            Map<Integer, CategoriaTarefa> category = new HashMap<>();

            while (resultSet.next()) {
                Integer categoryId = resultSet.getInt("category_id");
                Integer personId = resultSet.getInt("responsible_personnel_id");

                CategoriaTarefa categoryTask = category.get(categoryId);
                if (categoryTask == null) {
                    categoryTask = instantiatingCategory(resultSet);
                    category.put(categoryId, categoryTask);
                }

                Pessoa responsiblePersonnel = responsible.get(personId);
                if (responsiblePersonnel == null) {
                    responsiblePersonnel = instantiatingPerson(resultSet);
                    responsible.put(personId, responsiblePersonnel);
                }

                Tarefa task = instantiatingTask(resultSet, responsiblePersonnel, categoryTask);
                tasks.add(task);
            }

            return tasks;
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    public void update(Pessoa pessoa, String taskName) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                "UPDATE tarefa "
                +"SET responsible_personnel_id = (?) "
                +"WHERE task_name = (?) "
            );

            preparedStatement.setInt(1, pessoa.getId());
            preparedStatement.setString(2, taskName);

            boolean verificadorPessoa = verificarPessoaNoBancoDados(pessoa.getId());

            if (verificadorPessoa == false) {
                throw new DBException("Pessoa não encontrada na tabela!");
            } else {
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("One rows Affected!!");
                }
            }
            
        } catch (Exception e) {
            throw new DBException(e.getMessage());
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

    public Pessoa instantiatingPerson(ResultSet resultSet) throws SQLException {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(resultSet.getInt("responsible_personnel_id"));
        pessoa.setName(resultSet.getString("name"));

        return pessoa;
    }

    public CategoriaTarefa instantiatingCategory(ResultSet resultSet) throws SQLException {
        CategoriaTarefa categoriaTarefa = new CategoriaTarefa();
        categoriaTarefa.setId(resultSet.getInt("category_id"));
        categoriaTarefa.setCategory_name(resultSet.getString("category_name"));

        return categoriaTarefa;
    }
    public Tarefa instantiatingTask(ResultSet resultSet, Pessoa pessoa, CategoriaTarefa categoriaTarefa) throws SQLException{
        Tarefa tarefa = new Tarefa();
        tarefa.setId(resultSet.getInt("id"));
        tarefa.setTask_name(resultSet.getString("task_name"));
        tarefa.setPriority(PrioridadeTarefa.valueOf(resultSet.getString("priority")));
        tarefa.setStatus(StatusTarefa.valueOf(resultSet.getString("status")));
        tarefa.setCategory(categoriaTarefa);
        tarefa.setPerson(pessoa);
        return tarefa;
    }

    public Boolean verificarPessoaNoBancoDados(Integer id) {

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                "SELECT FROM pessoa "
                   +"WHERE id = (?) "
            );
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Pessoa person = instantiatingPerson(resultSet);
                if (!person.equals(null)) {
                    return true;
                }
            }
            return false;

        } catch (Exception e) {
             throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }
    }
       
}
