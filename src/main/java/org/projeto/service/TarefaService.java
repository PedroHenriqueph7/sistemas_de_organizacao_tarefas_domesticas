package org.projeto.service;

import java.util.List;

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

    public void removerTarefa(Integer id) {
        tarefaRepository.deleteById(id);
    }
}
