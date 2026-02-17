package org.app.mockito.services;

import org.app.mockito.models.Examen;
import org.app.mockito.repositories.ExamenRepository;
import org.app.mockito.repositories.PreguntaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ExamenServiceImplTest {

    @Mock
    ExamenRepository repository;

    @Mock
    PreguntaRepository preguntaRepository;

    @InjectMocks
    ExamenServiceImpl service;

    @Test
    void findExamenPorNombre() {

        when(repository.findAll()).thenReturn(List.of(
                new Examen(5L, "Matemáticas"),
                new Examen(6L, "Lenguaje")
        ));

        Optional<Examen> examen = service.findExamenPorNombre("Matemáticas");

        assertTrue(examen.isPresent());
        assertEquals(5L, examen.get().getId());
        assertEquals("Matemáticas", examen.get().getNombre());

        verify(repository).findAll();
    }

    @Test
    void findExamenNoExiste() {

        when(repository.findAll()).thenReturn(List.of(
                new Examen(5L, "Historia")
        ));

        Optional<Examen> examen = service.findExamenPorNombre("Matemáticas");

        assertFalse(examen.isPresent());

        verify(repository).findAll();
        verifyNoInteractions(preguntaRepository);
    }

    @Test
    void findExamenConPreguntasPorNombre() {

        when(repository.findAll()).thenReturn(List.of(
                new Examen(5L, "Matemáticas")
        ));

        when(preguntaRepository.findPreguntasPorExamenId(5L))
                .thenReturn(List.of("aritmética", "integrales", "derivadas"));

        Optional<Examen> examen = service.findExamenConPreguntasPorNombre("Matemáticas");

        assertTrue(examen.isPresent());
        assertEquals(3, examen.get().getPreguntas().size());

        verify(repository).findAll();
        verify(preguntaRepository).findPreguntasPorExamenId(5L);
    }

    @Test
    void testPreguntasExamen() {

        Examen examen = new Examen(5L, "Matemáticas");

        when(repository.findAll()).thenReturn(List.of(examen));
        when(preguntaRepository.findPreguntasPorExamenId(5L))
                .thenReturn(List.of(
                        "aritmética",
                        "integrales",
                        "derivadas",
                        "trigonometría"
                ));

        Optional<Examen> resultado = service.findExamenConPreguntasPorNombre("Matemáticas");

        assertTrue(resultado.isPresent());
        assertEquals(4, resultado.get().getPreguntas().size());

        verify(repository).findAll();
        verify(preguntaRepository).findPreguntasPorExamenId(5L);
        verifyNoMoreInteractions(repository, preguntaRepository);
    }

    @Test
    void testNoDebeBuscarPreguntasSiNoExisteExamen() {

        when(repository.findAll()).thenReturn(List.of());

        Optional<Examen> resultado = service.findExamenConPreguntasPorNombre("Matemáticas");

        assertFalse(resultado.isPresent());

        verify(repository).findAll();
        verify(preguntaRepository, never()).findPreguntasPorExamenId(anyLong());
    }

    @Test
    void testArgumentCaptor() {

        Examen examen = new Examen(5L, "Matemáticas");

        when(repository.findAll()).thenReturn(List.of(examen));
        when(preguntaRepository.findPreguntasPorExamenId(anyLong()))
                .thenReturn(List.of("aritmética"));

        service.findExamenConPreguntasPorNombre("Matemáticas");

        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        verify(preguntaRepository).findPreguntasPorExamenId(captor.capture());

        assertEquals(5L, captor.getValue());
    }

    @Test
    void testOrdenDeEjecucion() {

        Examen examen = new Examen(5L, "Matemáticas");

        when(repository.findAll()).thenReturn(List.of(examen));
        when(preguntaRepository.findPreguntasPorExamenId(5L))
                .thenReturn(List.of("aritmética"));

        service.findExamenConPreguntasPorNombre("Matemáticas");

        InOrder inOrder = inOrder(repository, preguntaRepository);

        inOrder.verify(repository).findAll();
        inOrder.verify(preguntaRepository).findPreguntasPorExamenId(5L);
    }
}
