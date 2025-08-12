package org.projeto.repository;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.projeto.dataBase.DB;
import org.projeto.dataBase.DBException;
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

    @ParameterizedTest
    @CsvSource({
        "2", "4", "1", "6", "8", "10", "1"
    })
    public void verificarStatusTarefaConcluidaBancoDadosReturnTrue(Integer id) {
        boolean resultadoBooleano2 = tarefaRepository.verificarSeTarefaEstaConcluida(id);
        Assertions.assertTrue(resultadoBooleano2);
        System.out.println(resultadoBooleano2);
    }

    @ParameterizedTest
    @CsvSource({
        "5", "7", "9"
    })
    public void verificarStatusTarefaConcluidaBancoDadosReturnFalse(Integer id) {
        boolean resultadoBooleano2 = tarefaRepository.verificarSeTarefaEstaConcluida(id);
        Assertions.assertFalse(resultadoBooleano2);
        
        System.out.println(resultadoBooleano2);
    }

       // Simulação de teste com Exception
//    @Test
//     public void verificarAtivacaoDaExceptionEmIdsPassadosInvalidos() {
//     Integer idInvalido = -1; 

//     RuntimeException exception = Assertions.assertThrows(
//         DBException.class,
//         () -> tarefaRepository.deleteById(idInvalido) // precisa ser lambda
//     );

//     // Se quiser validar a mensagem da exceção
//     Assertions.assertEquals("No row affected!", exception.getMessage());
}