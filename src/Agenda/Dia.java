package Agenda;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * 
 * @author Asier Sergio
 */
public class Dia {
    private ArrayList<Evento>[] horas = new ArrayList[48];
    private ArrayList<Evento> eventosDiaEntero= new ArrayList<Evento>();
    private LocalDate fecha;

    public Dia(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    public void crearRecordatorio(LocalTime hora,boolean an,String nombre,boolean diaEnt){
        LocalDateTime fechaHora =LocalDateTime.of(fecha,hora);
        horas[calculoPosicion(hora)].add(new Recordatorio(an, nombre, diaEnt,fechaHora));
    }
    
    public void crearTarea(LocalTime hora,boolean urg,String nombre,boolean diaEnt,LocalTime tiempo){
        LocalDateTime fechaHora =LocalDateTime.of(fecha,hora);
        horas[calculoPosicion(hora)].add(new Tarea(urg, tiempo, nombre, diaEnt, fechaHora));
    }
    
    private int calculoPosicion(LocalTime h){
        int res;
        res=(h.getHour()*2)+(h.getMinute()!=0?1:0);
        
        return res;
    }
    
}
