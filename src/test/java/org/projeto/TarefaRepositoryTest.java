package org.projeto;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.projeto.dataBase.DB;
import org.projeto.repository.TarefaRepository;

public class TarefaRepositoryTest {
    TarefaRepository tarefaRepository;

    @BeforeEach
    public void instanciaTarefaRepository() throws IOException, SQLException{
        tarefaRepository = new TarefaRepository(DB.getConnection());
    }

    @Test
    public void verificarPessoaNoBancoDadosTest(){
        boolean resultadoBooleano = tarefaRepository.verificarPessoaNoBancoDados(8);
        Assertions.assertTrue(resultadoBooleano);
    }

    @Test
    public void verificarStatusTarefaConcluidaBancoDados() {
        boolean resultadoBooleano2 = tarefaRepository.verificarSeTarefaEstaConcluida(1);
        Assertions.assertFalse(resultadoBooleano2);
        System.out.println(resultadoBooleano2);
    }



}
