package org.projeto.service;

import org.projeto.models.Pessoa;
import org.projeto.repository.PessoaRepository;

public class PessoaService {
    
    PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository){
        this.pessoaRepository = pessoaRepository;
    }

    public void cadastrarMoradores(Pessoa person){
        pessoaRepository.insert(person);
        System.out.println("Insert Sucessed");
    }
}
