package org.app.mockito.services;

import org.app.mockito.models.Examen;

import java.util.Optional;

public interface ExamenService {

    Optional<Examen> findExamenPorNombre(String nombre);

    Optional<Examen> findExamenConPreguntasPorNombre(String nombre);

}
