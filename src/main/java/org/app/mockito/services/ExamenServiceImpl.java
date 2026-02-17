package org.app.mockito.services;

import org.app.mockito.models.Examen;
import org.app.mockito.repositories.ExamenRepository;
import org.app.mockito.repositories.PreguntaRepository;

import java.util.List;
import java.util.Optional;

public class ExamenServiceImpl implements ExamenService {

    private final ExamenRepository repository;
    private final PreguntaRepository preguntaRepository;


    public ExamenServiceImpl(ExamenRepository repository,
                             PreguntaRepository preguntaRepository) {
        this.repository = repository;
        this.preguntaRepository = preguntaRepository;
    }

    @Override
    public Optional<Examen> findExamenPorNombre(String nombre) {
        return repository.findAll()
                .stream()
                .filter(e -> e.getNombre().equals(nombre))
                .findFirst();
    }

    @Override
    public Optional<Examen> findExamenConPreguntasPorNombre(String nombre) {

        Optional<Examen> examenOptional = findExamenPorNombre(nombre);

        examenOptional.ifPresent(examen ->
                examen.setPreguntas(
                        preguntaRepository.findPreguntasPorExamenId(examen.getId())
                )
        );

        return examenOptional;
    }
}
