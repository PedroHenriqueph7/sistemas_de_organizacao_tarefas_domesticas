package org.projeto.service;

import org.projeto.models.CategoriaTarefa;
import org.projeto.repository.CategoriaTarefaRepository;

public class CategoriaTarefaService {
    CategoriaTarefaRepository categoriaTarefaRepository;

    public CategoriaTarefaService(CategoriaTarefaRepository categoriaTarefaRepository){
        this.categoriaTarefaRepository = categoriaTarefaRepository;
    }

    public void cadastrarCategoriasTarefas(CategoriaTarefa categoriaTarefa){
        categoriaTarefaRepository.insert(categoriaTarefa);
    }

    public void removerCategoriasTarefas(Integer id){
        categoriaTarefaRepository.deleteById(id);
    }

}
