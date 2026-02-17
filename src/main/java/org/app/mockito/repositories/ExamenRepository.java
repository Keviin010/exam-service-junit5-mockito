package org.app.mockito.repositories;

import org.app.mockito.models.Examen;

import java.util.List;

public interface ExamenRepository {

    List<Examen> findAll();

}
