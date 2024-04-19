package Agenda;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 
 * @author Asier Sergio
 */
public class Evento {
    private int id;
    private int sigId=1;
    private String nombre;
    private boolean diaEntero;
    private LocalDateTime fechaHora;

    public Evento(String nombre, boolean diaEntero, LocalDateTime fechaHora) {
        this.nombre = nombre;
        this.diaEntero = diaEntero;
        this.fechaHora = fechaHora;
        this.id=sigId;
        this.sigId++;
    }
    
    
}
