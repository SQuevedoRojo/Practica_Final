package Agenda;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Clase Recordatorio
 * @author Asier Sergio
 * @version 1.0
 */
public class Recordatorio extends Evento{
    /**
     * booleano el cual sirve para guardar si es un recordatorio anual
     */
    private boolean anual;

    public Recordatorio(boolean anual, String nombre, boolean diaEntero, LocalDateTime fechaHora) {
        super(nombre, diaEntero, fechaHora);
        this.anual = anual;
    }

    public Recordatorio(boolean anual, String nombre, boolean diaEntero) {
        super(nombre, diaEntero);
        this.anual = anual;
    }
    

    public boolean isAnual() {
        return anual;
    }
    /**
     * Metodo que imprime la informacion del evento
     */
    @Override
    public void mostrarInformacion()
    {
        super.mostrarInformacion();
        if(anual)
            System.out.println("RECORDATORIO ANUAL");
    }
    
    /**
     * Metodo el cual imprime por pantalla los datos del evento
     * 
     * @param fecha
     * @param nombre 
     */
    @Override
    public void imprimirInfo(LocalDate fecha,String nombre){
        FileWriter fw = null;
        try{
        LocalDateTime hoy = LocalDateTime.now();
        fw =new FileWriter("./src/FICHEROS/agenda-"+String.valueOf(hoy.getYear())+"-"+String.valueOf(hoy.getMonth())+"-"+String.valueOf(hoy.getDayOfMonth())+"_"+String.valueOf(hoy.getHour())+"-"+String.valueOf(hoy.getMinute())+"-"+nombre+".dat",true);
        PrintWriter pw = new PrintWriter(fw);
        
        if (this.isDiaEntero())
            pw.println("Dia Entero|"+ String.valueOf(fecha.getYear())+"-"+String.valueOf(fecha.getMonth())+"-"+ String.valueOf(fecha.getDayOfMonth()) +"|Recordatorio|"+this.getNombre()+"|"+comprobarAnual(anual));
        else
            pw.println(String.valueOf(this.getFechaHora().getYear())+"-"+String.valueOf(this.getFechaHora().getMonth()) + "-" +String.valueOf(this.getFechaHora().getDayOfMonth())+"|"+String.valueOf(this.getFechaHora().getHour())+":"+String.valueOf(this.getFechaHora().getMinute())+"|Recordatorio|"+this.getNombre()+"|"+comprobarAnual(anual));
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
     * Metodo que comprueba si un recordatorio es anual y devuelve un String
     * 
     * @param anu
     * @return 
     */
    public String comprobarAnual(boolean anu)
    {
        String cade;
        
        if (anu) 
        {
            cade="Es anual";    
        }
        else
        {
            cade="No es anual";
        }
        
        return cade;
    }
}
