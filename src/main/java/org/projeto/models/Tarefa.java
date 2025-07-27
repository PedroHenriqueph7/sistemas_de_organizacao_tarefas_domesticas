package org.projeto.models;

import org.projeto.models.enums.PrioridadeTarefa;
import org.projeto.models.enums.StatusTarefa;

public class Tarefa {
    private Integer id;
    private String task_name;
    private PrioridadeTarefa priority;
    private StatusTarefa status;
    private Pessoa person;
    private CategoriaTarefa category;

    public Tarefa(){}

    public Tarefa(String task_name, PrioridadeTarefa priority, StatusTarefa status, Pessoa person, CategoriaTarefa category) {
        this.task_name = task_name;
        this.priority = priority;
        this.status = status;
        this.person = person;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public PrioridadeTarefa getPriority() {
        return priority;
    }

    public void setPriority(PrioridadeTarefa priority) {
        this.priority = priority;
    }

    public StatusTarefa getStatus() {
        return status;
    }

    public void setStatus(StatusTarefa status) {
        this.status = status;
    }

    public Pessoa getPerson() {
        return person;
    }

    public void setPerson(Pessoa person) {
        this.person = person;
    }

    public CategoriaTarefa getCategory() {
        return category;
    }

    public void setCategory(CategoriaTarefa category) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((task_name == null) ? 0 : task_name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tarefa other = (Tarefa) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (task_name == null) {
            if (other.task_name != null)
                return false;
        } else if (!task_name.equals(other.task_name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Tarefa [id=" + id + ", task_name=" + task_name + ", priority=" + priority + ", status=" + status
                + ", responsible=" + person + ", category=" + category + "]";
    }
}