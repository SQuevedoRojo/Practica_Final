package Agenda;

import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Clase dia que se encarga de guardar las tareas de los eventos
 * 
 * @author Asier Sergio
 * @version 1.0
 */
public class Dia {
    /**
     * arraylist que guarda los eventos organizados por horas desde las 00:00 hasta las 23:30
     */
    private ArrayList<Evento>[] horas;
    /**
     * arraylist de eventos de dia entero
     */
    private ArrayList<Evento> eventosDiaEntero;
    /**
     * Fecha del dia correspondiente la cual guarda año mes y dia
     */
    private LocalDate fecha;
    /**
     * Constructor de dia
     * @param fecha Fecha del dia correspondiente la cual guarda año mes y dia
     */
    public Dia(LocalDate fecha) {
        this.fecha = fecha;
        horas = new ArrayList[48];
        eventosDiaEntero= new ArrayList<Evento>();
    }
    
    public ArrayList<Evento>[] getHoras() {
        return horas;
    }

    public ArrayList<Evento> getEventosDiaEntero() {
        return eventosDiaEntero;
    }
    
    /**
     * Metodo que crea un elemento recordatorio en el array list correspondiente
     * 
     * @param hora atributo que refleja la hora del evento
     * @param an booleano para controlar si es un evento anual
     * @param nombre nombre del evento
     * @param diaEnt booleano para controlar si en de dia entero
     */
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
    
    /**
     * Metodo que crea un elemento en el array list correspondiente sacandolo de un fichero
     * 
     * @param hora atributo que refleja la hora del evento
     * @param an booleano para controlar si es un evento anual
     * @param nombre nombre del evento
     * @param diaEnt booleano para controlar si en de dia entero
     * @param urg booleano para controlar si es una tarea urgente
     * @param tiempo tiempo que dura una tarea
     */
    public void crearEventosFichero(LocalTime hora,boolean an,String nombre,boolean diaEnt,boolean urg,LocalTime tiempo){
        
        boolean encontrado = false;
        if(diaEnt)
        {
            for (int i = 0; i < eventosDiaEntero.size() && !encontrado; i++)
                if(nombre.equalsIgnoreCase(eventosDiaEntero.get(i).getNombre()))
                    encontrado = true;
        }
        else
        {
            int pos = calculoPosicion(hora);
            if(horas[pos] != null)
                for (int i = 0; i < horas[pos].size() && !encontrado; i++)
                    if(nombre.equalsIgnoreCase(horas[pos].get(i).getNombre()))
                        encontrado = true;
        }
        if(!encontrado)
        {
            System.out.println("Evento añadido al programa");
            if(urg == false && tiempo == null)
                crearRecordatorio(hora, an, nombre, diaEnt);
            else
                crearTarea(hora, urg, nombre, diaEnt, tiempo);
        }
    }//crearRecordatorioFichero()
    
    
    /**
     * Metodo que crea un elemento tarea en el array list correspondiente
     * 
     * @param hora atributo que refleja la hora del evento
     * @param nombre nombre del evento
     * @param diaEnt booleano para controlar si en de dia entero
     * @param urg booleano para controlar si es una tarea urgente
     * @param tiempo tiempo que dura una tarea
     */
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
    
    /**
     * Metodo que calcula la posicion en el array list de horas para la creacion de eventos
     * 
     * @param h hora del evento
     * @return devuelve un entero 
     */
    public int calculoPosicion(LocalTime h){
        int res;
        res=(h.getHour()*2)+(h.getMinute()!=0?1:0);
        
        return res;
    }
    
    /**
     * Metodo el cual elimina un elemeno recordatorio del array list
     * 
     * @param id identificador del evento
     * @return devuelve un booleano
     */
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
    
    /**
     * Metodo el cual elimina un elemeno tarea del array list 
     * 
     * @param id identificador del evento
     * @return devuelve un booleano 
     */
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
    
    /**
     * Metodo el cual muesta la informacion de los array list
     */
    public void tratarInfo(){
        for (int i = 0; i < horas.length; i++) {
            if (horas[i]!=null)
                for (int j = 0; j < horas[i].size(); j++) {
                    horas[i].get(j).mostrarInformacion(fecha);
                }
        }
        for (int i = 0; i < eventosDiaEntero.size(); i++) {
                eventosDiaEntero.get(i).mostrarInformacion(fecha);
        }
    }
    
    /**
     * Metodo el cual muestra la informacion de una hora especifica
     * 
     * @param pos hora del evento a imprimir
     */
    public void tratarInfoEspecifico(LocalTime pos){
        int posicion=calculoPosicion(pos);
            if (horas[posicion]!=null)
                for (int j = 0; j < horas[posicion].size(); j++) {
                    horas[posicion].get(j).mostrarInformacion(fecha);
                }
    }
    
    /**
     * Metodo el cual imprime en el fichero los eventos
     * 
     * @param nombre nombre que indica si el fichero es de todo un año, un mes o un dia
     */
    public void imprimirEventos(String nombre)
    {
        for (ArrayList<Evento> hora : horas) {
            if(hora != null)
                for (Evento hora1 : hora) {
                        hora1.imprimirInfo(fecha, nombre);
                }
        }
        
        for (Evento e : eventosDiaEntero) {
            e.imprimirInfo(fecha,nombre);
        }
    }
}
