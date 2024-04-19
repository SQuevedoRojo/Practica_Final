package Agenda;

import java.time.LocalDateTime;

/**
 * 
 * @author Asier Sergio
 */
public class Recordatorio extends Evento{
    private boolean anual;

    public Recordatorio(boolean anual, String nombre, boolean diaEntero, LocalDateTime fechaHora) {
        super(nombre, diaEntero, fechaHora);
        this.anual = anual;
    }

    public boolean isAnual() {
        return anual;
    }
    
    public void mostrarInformacion()
    {
        super.mostrarInformacion();
        if(anual)
            System.out.println("RECORDATORIO ANUAL");
    }
}
