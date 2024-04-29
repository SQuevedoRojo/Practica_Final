package Agenda;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * Clase Recordatorio
 * @author Asier Sergio
 * @version 1.0
 */
public class Recordatorio extends Evento{
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
    
    @Override
    public void imprimirInfo(){
        FileWriter fw = null;
        try{
        LocalDateTime hoy = LocalDateTime.now();
        fw =new FileWriter("./src/FICHEROS/agenda-"+hoy+"-dat");
        PrintWriter pw = new PrintWriter(fw,true);
        
        if (this.isDiaEntero())
            pw.println(String.valueOf(this.getFechaHora().getYear())+"-"+String.valueOf(this.getFechaHora().getMonth())+"|"+this.getNombre()+" | Recordatorio | "+comprobarAnual(anual));
        else
            pw.println("Dia Entero | Recordatorio | "+this.getNombre()+" | "+comprobarAnual(anual));
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
