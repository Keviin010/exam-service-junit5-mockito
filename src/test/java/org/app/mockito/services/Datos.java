package org.app.mockito.services;

import org.app.mockito.models.Examen;
import java.util.List;

public class Datos {

    public static final List<Examen> EXAMENES = List.of(
            new Examen(5L, "Matemáticas"),
            new Examen(6L, "Lenguaje")
    );

    public static final List<String> PREGUNTAS = List.of(
            "aritmética",
            "integrales",
            "derivadas",
            "trigonometría"
    );

}
