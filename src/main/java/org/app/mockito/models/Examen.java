package org.app.mockito.models;

import java.util.ArrayList;
import java.util.List;

public class Examen {

    private Long id;
    private String nombre;
    private List<String> preguntas = new ArrayList<>();

    public Examen(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<String> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<String> preguntas) {
        this.preguntas = preguntas != null ? preguntas : new ArrayList<>();
    }
}
