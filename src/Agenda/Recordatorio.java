package Agenda;

import java.time.LocalTime;

/**
 * 
 * @author Asier Sergio
 */
public class Recordatorio extends Evento{
    private boolean anual;

    public Recordatorio(boolean anual, String nombre, LocalTime hora, int dia) {
        super(nombre, hora, dia);
        this.anual = anual;
    }
    
}
