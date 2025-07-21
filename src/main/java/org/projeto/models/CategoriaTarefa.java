package org.projeto.models;

public class CategoriaTarefa {
    private Integer id;
    private String category_name;

    public CategoriaTarefa(){}

    public CategoriaTarefa(String category_name) {
        this.category_name = category_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((category_name == null) ? 0 : category_name.hashCode());
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
        CategoriaTarefa other = (CategoriaTarefa) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (category_name == null) {
            if (other.category_name != null)
                return false;
        } else if (!category_name.equals(other.category_name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CategoriaTarefa [id=" + id + ", category_name=" + category_name + "]";
    }
}