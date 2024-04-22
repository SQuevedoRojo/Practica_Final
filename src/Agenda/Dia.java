package Agenda;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Clase dia que se encarga de guardar las tareas de los eventos
 * @author Asier Sergio
 * @version 1.0
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
        
        if (diaEnt)
            eventosDiaEntero.add(new Recordatorio(an, nombre, diaEnt));
        else
        {
            LocalDateTime fechaHora =LocalDateTime.of(fecha,hora);
            if(horas[calculoPosicion(hora)] == null)
            {
                horas[calculoPosicion(hora)] = new ArrayList<>();
                horas[calculoPosicion(hora)].add(new Recordatorio(an, nombre, diaEnt,fechaHora));
            }
            else
                horas[calculoPosicion(hora)].add(new Recordatorio(an, nombre, diaEnt,fechaHora));
        }
    }
    
    public void crearTarea(LocalTime hora,boolean urg,String nombre,boolean diaEnt,LocalTime tiempo){
        
        if (diaEnt)
            eventosDiaEntero.add(new Tarea(urg, tiempo, nombre, diaEnt));
        else
        {
            LocalDateTime fechaHora =LocalDateTime.of(fecha,hora);
            if(horas[calculoPosicion(hora)] == null)
            {
                horas[calculoPosicion(hora)] = new ArrayList<>();
                horas[calculoPosicion(hora)].add(new Tarea(urg, tiempo, nombre, diaEnt, fechaHora));
            }
            else
                horas[calculoPosicion(hora)].add(new Tarea(urg, tiempo, nombre, diaEnt, fechaHora));
        }
    }
    
    public int calculoPosicion(LocalTime h){
        int res;
        res=(h.getHour()*2)+(h.getMinute()!=0?1:0);
        
        return res;
    }
    
    public boolean eliminarRecordatorio(int id){
        boolean ok=false;
        
        for (int i = 0; i < horas.length && !ok; i++) {
            if (horas[i]!=null) 
                for (int j = 0; j < horas[i].size(); j++) {
                    if(id == horas[i].get(j).getId() && horas[i].get(j) instanceof Recordatorio){
                        horas[i].remove(j);
                        ok = true;   
                    }
                }
        }
        for (int i = 0; i < eventosDiaEntero.size(); i++) {
            if(id == eventosDiaEntero.get(i).getId() && eventosDiaEntero.get(i) instanceof Recordatorio){
                        eventosDiaEntero.remove(i);
                        ok = true;   
                    }
        }
        
        return ok;
    }
    
    public boolean eliminarTarea(int id){
        boolean ok=false;
        
        for (int i = 0; i < horas.length && !ok; i++) {
            if (horas[i]!=null) 
                for (int j = 0; j < horas[i].size(); j++) {
                    if(id == horas[i].get(j).getId() && horas[i].get(j) instanceof Tarea){
                        horas[i].remove(j);
                        ok = true;   
                    }
                }
        }
        for (int i = 0; i < eventosDiaEntero.size(); i++) {
            if(id == eventosDiaEntero.get(i).getId() && eventosDiaEntero.get(i) instanceof Tarea){
                        eventosDiaEntero.remove(i);
                        ok = true;   
                    }
        }
        
        return ok;
    }
    
    public void tratarInfo(){
        for (int i = 0; i < horas.length; i++) {
            if (horas[i]!=null)
                for (int j = 0; j < horas[i].size(); j++) {
                    horas[i].get(j).mostrarInformacion();
                }
        }
        for (int i = 0; i < eventosDiaEntero.size(); i++) {
                eventosDiaEntero.get(i).mostrarInformacion();
        }
    }
    
    public void tratarInfoEspecifico(LocalTime pos){
        int posicion=calculoPosicion(pos);
            if (horas[posicion]!=null)
                for (int j = 0; j < horas[posicion].size(); j++) {
                    horas[posicion].get(j).mostrarInformacion();
                }
    }
    
    public ArrayList<Evento>[] getHoras() {
        return horas;
    }

    public ArrayList<Evento> getEventosDiaEntero() {
        return eventosDiaEntero;
    }
    
    
}
