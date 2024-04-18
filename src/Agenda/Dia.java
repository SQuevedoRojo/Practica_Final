package Agenda;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 * 
 * @author Asier Sergio
 */
public class Dia {
    private ArrayList<Evento>[] eventos = new ArrayList[48];

    public Dia() {
    }
    
    public void crearEventoDiaEnt(String nombre, LocalTime tiempo,int dia){
        int hora=tiempo.getHour(),min=tiempo.getMinute();
        int posArray=(hora*2)+(min==0?0:1);
        eventos[posArray].add(new Evento(nombre, tiempo, dia));
    }
    //falta por editar instrucciones
    public void crearRecordatorio(String nombre, LocalTime tiempo,int dia,boolean anual){
        int hora=tiempo.getHour(),min=tiempo.getMinute();
        int posArray=(hora*2)+(min==0?0:1);
        eventos[posArray].add(new Evento(nombre, tiempo, dia));
    }
    
    public void crearTarea(String nombre, LocalTime tiempo,int dia,LocalTime durEsti, boolean urgente){
        int hora=tiempo.getHour(),min=tiempo.getMinute();
        int posArray=(hora*2)+(min==0?0:1);
        eventos[posArray].add(new Evento(nombre, tiempo, dia));
    }
}
