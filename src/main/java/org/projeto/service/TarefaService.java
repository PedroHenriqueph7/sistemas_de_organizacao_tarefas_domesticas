package org.projeto.service;

import java.util.List;
import java.util.function.Predicate;

import org.projeto.models.Tarefa;
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

    public void removerTarefa(Integer id) {
        tarefaRepository.deleteById(id);
    }
}
