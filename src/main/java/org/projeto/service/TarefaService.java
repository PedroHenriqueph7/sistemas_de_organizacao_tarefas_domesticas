package org.projeto.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.projeto.models.Pessoa;
import org.projeto.models.Tarefa;
import org.projeto.models.enums.StatusTarefa;
import org.projeto.repository.TarefaRepository;

public class TarefaService {
    TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public void cadastrarTarefa(Tarefa tarefa) {
        tarefaRepository.insert(tarefa);
    }

    public List<Tarefa> filtrarTarefasPorStatusConcluido() {
        List<Tarefa> tarefasList = tarefaRepository.findAll();

        Predicate<Tarefa> filtroTarefasConcluidas = p -> p.getStatus().name().equalsIgnoreCase("CONCLUIDA");
        List<Tarefa> tarefasPendentes = tarefasList.stream().filter(filtroTarefasConcluidas).toList();
        return tarefasPendentes;
    }

    public List<Tarefa> filtrarTarefasPorStatusPendente() {
        List<Tarefa> tarefasList = tarefaRepository.findAll();

        Predicate<Tarefa> filtroTarefasPendentes = p -> p.getStatus().name().equalsIgnoreCase("PENDENTE");
        List<Tarefa> tarefasPendentes = tarefasList.stream().filter(filtroTarefasPendentes).toList();
        return tarefasPendentes;
    }

    public List<Tarefa> listarTarefasPorPrioridade() {
        List<Tarefa> listTarefas = tarefaRepository.findAll();

        listTarefas.sort(new TarefaComparator());
        return listTarefas;
    }

    public Map<Pessoa, Long> listarTotalTarefasConcluidasPorPessoa() {
        List<Tarefa> listTarefas = tarefaRepository.findAll();

        Map<Pessoa, Long> quantidadeTarefasConcluidaPorPessoa = listTarefas.stream()
                                                                            .filter(p -> p.getStatus().equals(StatusTarefa.CONCLUIDA))
                                                                             .collect(Collectors.groupingBy(Tarefa::getPerson, Collectors.counting()));
        return quantidadeTarefasConcluidaPorPessoa;
    }

    public void associacaoResponsavelATarefa(Integer id, String taskName) {
        boolean responsavelExiste = tarefaRepository.verificarPessoaNoBancoDados(id);

        if (responsavelExiste) {
            tarefaRepository.updateResponsible(id, taskName);
        } else {
            System.out.println("Responsavel não encontrado!");
        } 
    }

    public void marcarTarefaComoConcluida(Integer id) {
        StatusTarefa statusTarefa = null;
        boolean jaConcluida = tarefaRepository.verificarSeTarefaEstaConcluida(id);

        if (!jaConcluida) {
             tarefaRepository.updateStatusTarefa(id, statusTarefa.CONCLUIDA);
        } 
       
    }
    
    public void removerTarefa(Integer id) {
        tarefaRepository.deleteById(id);
    }
}
