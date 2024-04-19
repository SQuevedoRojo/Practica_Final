package Agenda;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Clase principal que regula el comportamiento de la estructura de datos del programa
 * @author Asier Sergio
 * @version 1.0
 */
public class Principal 
{
    
    private static final int MESES = 12;
    private static final int DIAS = 31;
    private Dia[][] dias;
    private Menu menu;
    private Scanner entrada = new Scanner(System.in);
    private int diasUtilizados[] = {31,diasDisponibles(),31,30,31,30,31,31,30,31,30,31};
    private FileWriter fw = null;
    private int anno;
    
    public Principal()
    {
        dias = new Dia[MESES][DIAS];
        menu = new Menu();
    }
    
    public static void main(String[] args) throws AWTException, InterruptedException 
    {
        Principal p = new Principal();
        p.inicio();
    }//main()
    
    private void inicio() throws AWTException, InterruptedException
    {
        menu.opcionesPrincipales(this);

    }//inicio()
    
    private int diasDisponibles()
    {
        anno = comprobarScanner("\tIntroduce el año que desee para crear la agenda -> ");
        int diasFebrero = 28;
        if (annoBisiesto(anno))
            diasFebrero++;
        return diasFebrero;
    }//diasDisponibles()
    
    private boolean annoBisiesto(int anno)
    {
        boolean bisiesto = false;
        LocalDate fecha = LocalDate.of(anno,01,01);
        return fecha.isLeapYear();
    }//annoBisiesto()
    
    private int comprobarScanner(String mensaje)
    {
        int opcion = -1;
        do{
            try 
            {
                System.out.print(mensaje);
                opcion = entrada.nextInt();
            } 
            catch (InputMismatchException e)
            {
                System.out.println("\n\t\tNo has introducido un número entero");
                entrada.nextLine();
            }
        }while(!(opcion > 0));
        return opcion;
    }//comprobarScanner()
    
    public void crearEventos(int op) throws AWTException, InterruptedException
    {
        int mes = comprobarScanner("Introduce el mes que quieres crear el evento -> ");
        int dia;
        do{
            dia = comprobarScanner("Introduce el dia que quieres crear el evento -> ");
            if (!(dia-1 >= diasUtilizados[mes-1] && dia-1 <= diasUtilizados[mes-1]))
            {
                System.out.println("Los dias seleccionados no estan disponibles para el mes " + mes);
            }
        }while(!(dia-1 >= diasUtilizados[mes-1] && dia-1 <= diasUtilizados[mes-1]));
        LocalDate fecha = LocalDate.of(anno, mes, dia);
        if(dias[(mes-1)][(dia-1)] != null)
        {
            menu.limpiar();
            if(op == 1)
                crearRecordatorio(fecha,mes-1,dia-1);
            else
                crearTarea(fecha,mes-1,dia-1);
        }
        else
        {
            menu.limpiar();
            dias[(mes-1)][(dia-1)] = new Dia(fecha);
            if(op == 1)
                crearRecordatorio(fecha,mes-1,dia-1);
            else
                crearTarea(fecha,mes-1,dia-1);
        }
    }//crearEventos()
    
    private void crearRecordatorio(LocalDate fecha,int mes,int dia)
    {
        int hora,minutos,anual,diaEntero;
        boolean anno = false,diaentero = false;
        String concepto;
        do{
            hora = comprobarScanner("Introduce la hora para crear el Recordatorio [0..23]");
        }while(!(hora >= 0 && hora <= 23));
        do{
            minutos = comprobarScanner("Introduce los minutos para crear el Recordatorio [0 ò 30]");
            if(minutos != 0 || minutos != 30)
                System.out.println("SOLO SE ACEPTAN INTERVALOS DE MEDIA HORA");
        }while(!(minutos == 0 || minutos == 30));
        LocalTime tiempo = LocalTime.of(hora, minutos);
        do{
            anual = comprobarScanner("Introduce 1 si el Recordatorio es de recursividad anual, sino introduzca 0 -> ");
        }while(!(anual == 1 || anual == 0));
        do{
            diaEntero = comprobarScanner("Introduce 1 si el Recordatorio es para el Dia Entero, sino introduzca 0 -> ");
        }while(!(diaEntero == 1 || diaEntero == 0));
        System.out.println("Introduzca el concepto del recordatorio -> ");
        concepto = entrada.nextLine();
        anno = anual == 1;
        diaentero = diaEntero == 1;
        dias[mes][dia].crearRecordatorio(tiempo, anno, concepto, diaentero);
    }//crearRecordatorio()
    
    private void crearTarea(LocalDate fecha,int mes,int dia)
    {
        int hora,minutos,diaEntero,horaEstimada,minutosEstimados,urgente;
        boolean diaentero = false,urg = false;
        LocalTime horaEst,horaEvento;
        String concepto;
        do{
            hora = comprobarScanner("Introduce la hora para crear el Recordatorio [0..23]");
        }while(!(hora >= 0 && hora <= 23));
        do{
            minutos = comprobarScanner("Introduce los minutos para crear el Recordatorio [0 ò 30]");
            if(minutos != 0 || minutos != 30)
                System.out.println("SOLO SE ACEPTAN INTERVALOS DE MEDIA HORA");
        }while(!(minutos == 0 || minutos == 30));
        LocalTime tiempo = LocalTime.of(hora, minutos);
        do{
            urgente = comprobarScanner("Introduce 1 si el Recordatorio urgente, sino introduzca 0 -> ");
        }while(!(urgente == 1 || urgente == 0));
        do{
            diaEntero = comprobarScanner("Introduce 1 si el Recordatorio es para el Dia Entero, sino introduzca 0 -> ");
        }while(!(diaEntero == 1 || diaEntero == 0));
        do{
            horaEstimada = comprobarScanner("Introduce la hora para crear el Recordatorio [0..23]");
        }while(!(horaEstimada >= 0 && horaEstimada <= 23));
        do{
            minutosEstimados = comprobarScanner("Introduce los minutos para crear el Recordatorio [0 ò 30]");
            if(minutosEstimados != 0 || minutosEstimados != 30)
                System.out.println("SOLO SE ACEPTAN INTERVALOS DE MEDIA HORA");
        }while(!(minutosEstimados == 0 || minutosEstimados == 30));
        System.out.println("Introduzca el concepto del recordatorio -> ");
        concepto = entrada.nextLine();
        diaentero = diaEntero == 1;
        urg = urgente == 1;
        horaEst = LocalTime.of(horaEstimada, minutosEstimados);
        horaEvento = LocalTime.of(hora, minutos);
        dias[mes][dia].crearTarea(horaEvento,urg,concepto, diaentero,horaEst);
    }//crearTarea()
    
    public void borrarRecordatorio()
    {
        
    }//borrarRecordatorio()
    
    public void borrarTarea()
    {
        
    }//borrarTarea()
    
    public void imprimirEventosDia()
    {
        
    }//imprimirEventosDia()
    
    public void imprimirEventosMes()
    {
        
    }//imprimirEventosMes()
    
    public void imprimirEventoEspecifico()
    {
        
    }//imprimirEventoEspecifico()
    
    public void leerEventosFichero()
    {
        
    }//leerEventosFichero()
    
    public void guardarEventosAnno()
    {

    }//guardarEventosAnno()
    
    public void guardarEventosMes()
    {
        
    }//guardarEventosMes()
    
    public void guardarEventosDia()
    {
        
    }//guardarEventosDia()
    
    
}//class
