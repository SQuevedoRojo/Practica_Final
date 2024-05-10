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
    /**
     * Constructor de evento si es para una hora especifica
     * @param nombre Nombre del evento
     * @param diaEntero booleano el cual dice si es de un dia entero o si se hace a una hora especifica
     * @param fechaHora guarda un valor de tipo fecha el cual guarda la hora y minuto
     */
    public Evento(String nombre, boolean diaEntero, LocalDateTime fechaHora) {
        this.nombre = nombre;
        this.diaEntero = diaEntero;
        this.fechaHora = fechaHora;
        this.id=sigId;
        this.sigId++;
    }
    /**
     * Constructor de evento si es para un dia entero
     * @param nombre Nombre del evento
     * @param diaEntero booleano el cual dice si es de un dia entero o si se hace a una hora especifica
     */
    public Evento(String nombre, boolean diaEntero) {
        this.nombre = nombre;
        this.diaEntero = diaEntero;
        this.id=sigId;
        this.sigId++;
    }
    /**
     * Getter de nombre
     * @return devuelve el atributo nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Getter de diaEntero
     * @return devuelve el atributo diaEntero
     */
    public boolean isDiaEntero() {
        return diaEntero;
    }
    /**
     * Getter de fechaHora
     * @return devuelve el atributo fechaHora
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    /**
     * Getter de id
     * @return devuelve el atributo id
     */
    public int getId() {
        return id;
    }
    /**
     * Metodo que imprime la informacion del evento
     * 
     * 
     * @param fecha Fecha del Evento si se trata de dia entero
     */
    public void mostrarInformacion(LocalDate fecha)
    {
        
        if(diaEntero)
        {
            System.out.println("ID -> " + id);
            System.out.println("ESTA DESTINADO PARA EL DIA ENTERO " + fecha);
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
     * @param fecha  dia del cual se imprime el evento
     * @param nombre nombre del evento a imprimir
     */
    public abstract void imprimirInfo(LocalDate fecha,String nombre);
}
