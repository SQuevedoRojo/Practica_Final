package Agenda;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Clase Tarea
 * @author Asier Sergio
 * @version 1.0
 */
public class Tarea extends Evento{
    private boolean urgente;
    private LocalTime tiempoEstimado;

    public Tarea(boolean urgente, LocalTime tiempoEstimado, String nombre, boolean diaEntero, LocalDateTime fechaHora) {
        super(nombre, diaEntero, fechaHora);
        this.urgente = urgente;
        this.tiempoEstimado = tiempoEstimado;
    }

    public Tarea(boolean urgente, LocalTime tiempoEstimado, String nombre, boolean diaEntero) {
        super(nombre, diaEntero);
        this.urgente = urgente;
        this.tiempoEstimado = tiempoEstimado;
    }

    public boolean isUrgente() {
        return urgente;
    }

    public LocalTime getTiempoEstimado() {
        return tiempoEstimado;
    }
    /**
     * Metodo que imprime la informacion del evento
     */
    @Override
    public void mostrarInformacion()
    {
        super.mostrarInformacion();
        if(urgente)
        {
            System.out.println("TAREA URGENTE");
            System.out.println("DURACION APROXIMADA DE " + tiempoEstimado);
        }
        else
            System.out.println("TAREA NO URGENTE");
    }
}
