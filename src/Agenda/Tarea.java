package Agenda;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Clase Tarea
 * @author Asier Sergio
 * @version 1.0
 */
public class Tarea extends Evento{
    /**
     * booleano el cual guarda si es urgente
     */
    private boolean urgente;
    /**
     * variable la cual sirve para guardar un tiempo estimado de la tarea
     */
    private LocalTime tiempoEstimado;
    /**
     * constructor de tarea
     * @param urgente booleano el cual indica si es una tarea urgente
     * @param tiempoEstimado tiempo que se terda en hacer la tarea
     * @param nombre Nombre del evento
     * @param diaEntero booleano el cual dice si es de un dia entero o si se hace a una hora especifica
     * @param fechaHora guarda un valor de tipo fecha el cual guarda la hora y minuto 
     */
    public Tarea(boolean urgente, LocalTime tiempoEstimado, String nombre, boolean diaEntero, LocalDateTime fechaHora) {
        super(nombre, diaEntero, fechaHora);
        this.urgente = urgente;
        this.tiempoEstimado = tiempoEstimado;
    }
    /**
     * constructor de tarea
     * @param urgente booleano el cual indica si es una tarea urgente
     * @param tiempoEstimado tiempo que se terda en hacer la tarea
     * @param nombre Nombre del evento
     * @param diaEntero booleano el cual dice si es de un dia entero o si se hace a una hora especifica
     */
    public Tarea(boolean urgente, LocalTime tiempoEstimado, String nombre, boolean diaEntero) {
        super(nombre, diaEntero);
        this.urgente = urgente;
        this.tiempoEstimado = tiempoEstimado;
    }
    /**
     * Getter de urgente
     * @return devuelve el atributo urgente
     */
    public boolean isUrgente() {
        return urgente;
    }
    /**
     * Getter de tiempoEstimado
     * @return devuelve el atributo tiempoEstimado
     */
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
            if(!this.isDiaEntero())
                System.out.println("DURACION APROXIMADA DE " + tiempoEstimado);
        }
        else
            System.out.println("TAREA NO URGENTE");
    }
    
    /**
     * Metodo el cual imprime por pantalla los datos del evento
     * 
     * @param fecha  dia del cual se imprime el evento
     * @param nombre nombre del evento a imprimir
     */
    @Override
    public void imprimirInfo(LocalDate fecha,String nombre){
        FileWriter fw = null;
        try{
        LocalDateTime hoy = LocalDateTime.now();
        fw =new FileWriter("./src/FICHEROS/agenda-"+String.valueOf(hoy.getYear())+"-"+String.valueOf(hoy.getMonth())+"-"+String.valueOf(hoy.getDayOfMonth())+"_"+String.valueOf(hoy.getHour())+"-"+String.valueOf(hoy.getMinute())+"-"+nombre+".dat",true);
        PrintWriter pw = new PrintWriter(fw);
        
        if (this.isDiaEntero())
            pw.println("Dia Entero|"+ String.valueOf(fecha.getYear())+"-"+String.valueOf(fecha.getMonth())+"-"+ String.valueOf(fecha.getDayOfMonth()) +"|Tarea|"+this.getNombre()+"|"+comprobarUrgente(urgente)+"|"+String.valueOf(tiempoEstimado.getHour())+":"+String.valueOf(tiempoEstimado.getMinute()));
        else
            pw.println(String.valueOf(this.getFechaHora().getYear())+"-"+String.valueOf(this.getFechaHora().getMonth()) + "-" +String.valueOf(this.getFechaHora().getDayOfMonth())+"|"+String.valueOf(this.getFechaHora().getHour())+":"+String.valueOf(this.getFechaHora().getMinute())+"|Tarea|"+this.getNombre()+"|"+comprobarUrgente(urgente)+"|"+String.valueOf(tiempoEstimado.getHour())+":"+String.valueOf(tiempoEstimado.getMinute()));
        
         pw.flush();
        
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());                                                                   
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if (fw != null) 
                    fw.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());                                                               
            }
        }
    }
    /**
     * Metodo que comprueba si la tarea es urgente y devuelve un String
     * @param urg booleano que indica si es urgente
     * @return devuelve un String
     */
    public String comprobarUrgente(boolean urg)
    {
        String cade;
        
        if (urg) 
        {
            cade="Es urgente";    
        }
        else
        {
            cade="No es urgente";
        }
        
        return cade;
    }
}
