package Agenda;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
    
    @Override
    public void imprimirInfo(){
        FileWriter fw = null;
        try{
        LocalDateTime hoy = LocalDateTime.now();
        fw =new FileWriter("./src/FICHEROS/agenda-"+String.valueOf(hoy.getYear())+"-"+String.valueOf(hoy.getMonth())+"-"+String.valueOf(hoy.getDayOfMonth())+"_"+String.valueOf(hoy.getHour())+"-"+String.valueOf(hoy.getMinute())+".dat",true);
        PrintWriter pw = new PrintWriter(fw);
        
        if (this.isDiaEntero())
            pw.println("Dia Entero | Tarea | "+this.getNombre()+" | "+comprobarUrgente(urgente)+" | "+String.valueOf(tiempoEstimado.getHour())+":"+String.valueOf(tiempoEstimado.getMinute()));
        else
            pw.println(String.valueOf(this.getFechaHora().getYear())+"-"+String.valueOf(this.getFechaHora().getMonth())+"|"+this.getNombre()+" | Tarea | "+comprobarUrgente(urgente));
        
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
