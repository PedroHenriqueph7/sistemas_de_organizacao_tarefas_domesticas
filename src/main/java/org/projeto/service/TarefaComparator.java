package org.projeto.service;

import java.util.Comparator;

import org.projeto.models.Tarefa;

public class TarefaComparator implements Comparator<Tarefa>{

    @Override
    public int compare(Tarefa o1, Tarefa o2) {
         
        return o1.getPriority().compareTo(o2.getPriority());
    }
    
}
