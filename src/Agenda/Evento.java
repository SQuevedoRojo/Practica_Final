package Agenda;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Clase Padre de Tarea y Recordatorio
 * @author Asier Sergio
 * @version 1.0
 */
public abstract class Evento {
    /**
     * variable la cual sirve como identificador del evento
     */
    private int id;
    /**
     * variable estatica la cual sirve para el control del identificador de los eventos
     */
    private static int sigId=1;
    /**
     * Nombre del evento
     */
    private String nombre;
    /**
     * booleano el cual dice si es de un dia entero o si se hace a una hora especifica
     */
    private boolean diaEntero;
    /**
     * guarda un valor de tipo fecha el cual guarda la hora y minuto
     */
    private LocalDateTime fechaHora;

    public Evento(String nombre, boolean diaEntero, LocalDateTime fechaHora) {
        this.nombre = nombre;
        this.diaEntero = diaEntero;
        this.fechaHora = fechaHora;
        this.id=sigId;
        this.sigId++;
    }

    public Evento(String nombre, boolean diaEntero) {
        this.nombre = nombre;
        this.diaEntero = diaEntero;
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
    /**
     * Metodo que imprime la informacion del evento
     * 
     */
    public void mostrarInformacion()
    {
        
        if(diaEntero)
        {
            System.out.println("ID -> " + id);
            System.out.println("ESTA DESTINADO PARA EL DIA ENTERO");
        }
        else
        {
            System.out.println("\nEVENTO CON FECHA Y HORA -> " + fechaHora);
            System.out.println("ID -> " + id);
            System.out.println("NO ESTA DESTINADO PARA EL DIA ENTERO");
        }
        System.out.println("EL CONCEPTO ES : \n\t" + nombre);
    }
    
    /**
     * Metodo el cual imprime por pantalla los datos del evento
     * 
     * @param fecha
     * @param nombre 
     */
    public abstract void imprimirInfo(LocalDate fecha,String nombre);
}
