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
    private ArrayList<Evento>[] horas;
    private ArrayList<Evento> eventosDiaEntero;
    private LocalDate fecha;

    public Dia(LocalDate fecha) {
        this.fecha = fecha;
        horas = new ArrayList[48];
        eventosDiaEntero= new ArrayList<Evento>();
    }
    
    public void crearRecordatorio(LocalTime hora,boolean an,String nombre,boolean diaEnt){
        LocalDateTime fechaHora =LocalDateTime.of(fecha,hora);
        if(horas[calculoPosicion(hora)] == null)
        {
            horas[calculoPosicion(hora)] = new ArrayList<>();
            horas[calculoPosicion(hora)].add(new Recordatorio(an, nombre, diaEnt,fechaHora));
        }
        else
            horas[calculoPosicion(hora)].add(new Recordatorio(an, nombre, diaEnt,fechaHora));
    }
    
    public void crearTarea(LocalTime hora,boolean urg,String nombre,boolean diaEnt,LocalTime tiempo){
        LocalDateTime fechaHora =LocalDateTime.of(fecha,hora);
        if(horas[calculoPosicion(hora)] == null)
        {
            horas[calculoPosicion(hora)] = new ArrayList<>();
            horas[calculoPosicion(hora)].add(new Tarea(urg, tiempo, nombre, diaEnt, fechaHora));
        }
        else
            horas[calculoPosicion(hora)].add(new Tarea(urg, tiempo, nombre, diaEnt, fechaHora));
    }
    
    public int calculoPosicion(LocalTime h){
        int res;
        res=(h.getHour()*2)+(h.getMinute()!=0?1:0);
        
        return res;
    }

    public static int calculoPosicionHora(LocalTime h){
        int res;
        res=(h.getHour()*2)+(h.getMinute()!=0?1:0);
        
        return res;
    }
    
    public ArrayList<Evento>[] getHoras() {
        return horas;
    }

    public ArrayList<Evento> getEventosDiaEntero() {
        return eventosDiaEntero;
    }
    
    
}
