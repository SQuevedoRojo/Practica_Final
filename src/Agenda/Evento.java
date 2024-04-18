package Agenda;

import java.time.LocalTime;

/**
 * 
 * @author Asier Sergio
 */
public class Evento {
    private int id;
    private static int sigId=1;
    private String nombre;
    private LocalTime hora;
    private int dia;

    public Evento(String nombre, LocalTime hora, int dia) {
        this.nombre = nombre;
        this.hora = hora;
        this.dia = dia;
        id=sigId;
        sigId++;
    }
    
    
}
