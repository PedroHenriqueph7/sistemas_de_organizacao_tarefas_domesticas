package org.projeto.factory;

import java.io.IOException;
import java.sql.SQLException;

import org.projeto.dataBase.DB;
import org.projeto.models.CategoriaTarefa;
import org.projeto.models.Tarefa;
import org.projeto.repository.CategoriaTarefaRepository;
import org.projeto.repository.PessoaRepository;
import org.projeto.repository.TarefaRepository;

public class DaoFactory {

    public static PessoaRepository createPessoaRepository() throws IOException, SQLException{
        return new PessoaRepository(DB.getConnection());
    }

    public static CategoriaTarefaRepository createCategoriaTarefaRepository() throws IOException, SQLException {
        return new CategoriaTarefaRepository(DB.getConnection());
    }

    public static TarefaRepository  createTarefaRepository() throws IOException, SQLException {
        return new TarefaRepository(DB.getConnection());
    }
}
