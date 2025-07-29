package org.projeto.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.projeto.dataBase.DBException;
import org.projeto.factory.DaoFactory;
import org.projeto.models.CategoriaTarefa;
import org.projeto.models.Pessoa;
import org.projeto.models.Tarefa;
import org.projeto.models.enums.PrioridadeTarefa;
import org.projeto.models.enums.StatusTarefa;
import org.projeto.service.CategoriaTarefaService;
import org.projeto.service.PessoaService;
import org.projeto.service.TarefaService;

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
          //pessoaService.cadastrarMoradores(new Pessoa("Name4", 21));
          //pessoaService.removerMorador(5);
          CategoriaTarefaService categoriaTarefaService = new CategoriaTarefaService(DaoFactory.createCategoriaTarefaRepository());
          // categoriaTarefaService.cadastrarCategoriasTarefas(new CategoriaTarefa("Domesticas"));
          // categoriaTarefaService.cadastrarCategoriasTarefas(new CategoriaTarefa("Serviços"));
          // categoriaTarefaService.cadastrarCategoriasTarefas(new CategoriaTarefa("tarefas"));
          // categoriaTarefaService.removerCategoriasTarefas(3);
          
          TarefaService tarefaService = new TarefaService(DaoFactory.createTarefaRepository());

          //tarefaService.cadastrarTarefa(new Tarefa("limpar as janelas", PrioridadeTarefa.MEDIO, StatusTarefa.CONCLUIDA, new Pessoa(3), new CategoriaTarefa(1 )));
          //tarefaService.cadastrarTarefa(new Tarefa("Passear com o Dog", PrioridadeTarefa.ALTO, StatusTarefa.CONCLUIDA, new Pessoa(4), new CategoriaTarefa(2 )));
          //List<Tarefa> listTarefasConcluidas = tarefaService.filtrarTarefasPorStatusConcluido();

          //System.out.println(listTarefasConcluidas);

          List<Tarefa> listTarefasPendentes = tarefaService.filtrarTarefasPorStatusPendente();
          System.out.println(listTarefasPendentes);
        } catch (IOException e) {
            throw new DBException(e.getMessage());
        }

    }
}
