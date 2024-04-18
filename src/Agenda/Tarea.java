package Agenda;

import java.time.LocalTime;

/**
 * 
 * @author Asier Sergio
 */
public class Tarea extends Evento{
    private boolean urgente;
    private LocalTime durEsti;
    
    public Tarea(boolean urgente, LocalTime durEsti, String nombre, LocalTime hora, int dia) {
        super(nombre, hora, dia);
        this.urgente=urgente;
        this.durEsti=durEsti;
    }
}
