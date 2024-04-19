package Agenda;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 
 * @author Asier Sergio
 */
public class Tarea extends Evento{
    private boolean urgente;
    private LocalTime tiempoEstimado;

    public Tarea(boolean urgente, LocalTime tiempoEstimado, String nombre, boolean diaEntero, LocalDateTime fechaHora) {
        super(nombre, diaEntero, fechaHora);
        this.urgente = urgente;
        this.tiempoEstimado = tiempoEstimado;
    }
    
}
