package org.projeto.factory;

import java.io.IOException;
import java.sql.SQLException;

import org.projeto.repository.DB;
import org.projeto.repository.PessoaRepository;

public class DaoFactory {

    public static PessoaRepository createPessoaRepository() throws IOException, SQLException{
        return new PessoaRepository(DB.getConnection());
    }
}
