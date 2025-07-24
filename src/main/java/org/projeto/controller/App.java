package org.projeto.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.projeto.dataBase.DBException;
import org.projeto.factory.DaoFactory;
import org.projeto.models.Pessoa;
import org.projeto.service.PessoaService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException
    {
        try {
          PessoaService pessoaService = new PessoaService(DaoFactory.createPessoaRepository());
          pessoaService.cadastrarMoradores(new Pessoa("Name2", 30));

        } catch (IOException e) {
            throw new DBException(e.getMessage());
        }

    }
}
