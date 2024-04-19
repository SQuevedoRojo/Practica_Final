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

    public String getNombre() {
        return nombre;
    }

    public boolean isDiaEntero() {
        return diaEntero;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public int getId() {
        return id;
    }
    
    public void mostrarInformacion()
    {
        System.out.println("\nEVENTO CON FECHA Y HORA -> " + fechaHora);
        System.out.println("ID -> " + id);
        if(diaEntero)
            System.out.println("ESTA DESTINADO PARA EL DIA ENTERO");
        else
            System.out.println("NO ESTA DESTINADO PARA EL DIA ENTERO");
        System.out.println("EL CONCEPTO ES : \n\t" + nombre);
    }
}
