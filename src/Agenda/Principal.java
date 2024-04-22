package Agenda;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
    private Scanner entrada = new Scanner(System.in);
    private final int diasUtilizados[] = {31,diasDisponibles(),31,30,31,30,31,31,30,31,30,31};
    private Dia[][] dias;
    private Menu menu;
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
        }while(!(opcion >= 0));
        return opcion;
    }//comprobarScanner()
    
    public void crearEventos(int op) throws AWTException, InterruptedException
    {
        int mes,dia;
        do{
            mes = comprobarScanner("Introduce el mes que quieres crear el evento -> ");
        }while(!(mes >= 1 && mes <= 12));
        do{
            dia = comprobarScanner("Introduce el dia que quieres crear el evento -> ");
            if (!(dia >= 1 && dia <= diasUtilizados[mes-1]))
                System.out.println("Los dias seleccionados no estan disponibles para el mes " + mes);
        }while(!(dia >= 1 && dia <= diasUtilizados[mes-1]));
        LocalDate fecha = LocalDate.of(anno, mes, dia);
        if(dias[(mes-1)][(dia-1)] != null)
        {
            menu.limpiar();
            if(op == 1)
                crearRecordatorio(mes-1,dia-1);
            else
                crearTarea(mes-1,dia-1);
        }
        else
        {
            menu.limpiar();
            dias[(mes-1)][(dia-1)] = new Dia(fecha);
            if(op == 1)
                crearRecordatorio(mes-1,dia-1);
            else
                crearTarea(mes-1,dia-1);
        }
    }//crearEventos()
    
    private void crearRecordatorio(int mes,int dia)
    {
        int hora,minutos,anual,diaEntero;
        boolean anno = false,diaentero = false;
        String concepto;
        do{
            diaEntero = comprobarScanner("Introduce 1 si el Recordatorio es para el Dia Entero, sino introduzca 0 -> ");
        }while(!(diaEntero == 1 || diaEntero == 0));
        diaentero = diaEntero == 1;
        if(!diaentero)
        {
            do{
                hora = comprobarScanner("Introduce la hora para crear el Recordatorio [0..23] -> ");
            }while(!(hora >= 0 && hora <= 23));
            do{
                minutos = comprobarScanner("Introduce los minutos para crear el Recordatorio [0 ò 30] -> ");
            }while(!(minutos == 0 || minutos == 30));
            do{
                anual = comprobarScanner("Introduce 1 si el Recordatorio es de recursividad anual, sino introduzca 0 -> ");
            }while(!(anual == 1 || anual == 0));
            System.out.println("Introduzca el concepto del recordatorio -> ");
            entrada.nextLine();
            concepto = entrada.nextLine();
            LocalTime tiempo = LocalTime.of(hora, minutos);
            anno = anual == 1;
            dias[mes][dia].crearRecordatorio(tiempo, anno, concepto, diaentero);
        }
        else
        {
            do{
                anual = comprobarScanner("Introduce 1 si el Recordatorio es de recursividad anual, sino introduzca 0 -> ");
            }while(!(anual == 1 || anual == 0));
            System.out.println("Introduzca el concepto del recordatorio -> ");
            entrada.nextLine();
            concepto = entrada.nextLine();
            anno = anual == 1;
            LocalTime l = null;
            dias[mes][dia].crearRecordatorio(l, anno, concepto, diaentero);
        }
        
    }//crearRecordatorio()
    
    private void crearTarea(int mes,int dia)
    {
        int hora,minutos,diaEntero,horaEstimada,minutosEstimados,urgente;
        boolean diaentero = false,urg = false;
        LocalTime horaEst,horaEvento;
        String concepto;
        do{
            diaEntero = comprobarScanner("Introduce 1 si el Recordatorio es para el Dia Entero, sino introduzca 0 -> ");
        }while(!(diaEntero == 1 || diaEntero == 0));
        diaentero = diaEntero == 1;
        if(!diaentero)
        {
            do{
                hora = comprobarScanner("Introduce la hora para crear la Tarea [0..23] -> ");
            }while(!(hora >= 0 && hora <= 23));
            do{
                minutos = comprobarScanner("Introduce los minutos para crear la Tarea [0 ò 30] -> ");
            }while(!(minutos == 0 || minutos == 30));
            do{
                urgente = comprobarScanner("Introduce 1 si el Recordatorio urgente, sino introduzca 0 -> ");
            }while(!(urgente == 1 || urgente == 0));

            do{
                horaEstimada = comprobarScanner("Introduce las horas que durara el Recordatorio [0..23] -> ");
            }while(!(horaEstimada >= 0 && horaEstimada <= 23));
            do{
                minutosEstimados = comprobarScanner("Introduce los minutos que durara el Recordatorio [0 ò 30] -> ");
            }while(!(minutosEstimados == 0 || minutosEstimados == 30));
            System.out.println("Introduzca el concepto del recordatorio -> ");
            entrada.nextLine();
            concepto = entrada.nextLine();
            urg = urgente == 1;
            horaEst = LocalTime.of(horaEstimada, minutosEstimados);
            horaEvento = LocalTime.of(hora, minutos);
            dias[mes][dia].crearTarea(horaEvento,urg,concepto, diaentero,horaEst);
        }
        else
        {
            do{
                urgente = comprobarScanner("Introduce 1 si el Recordatorio urgente, sino introduzca 0 -> ");
            }while(!(urgente == 1 || urgente == 0));

            do{
                horaEstimada = comprobarScanner("Introduce las horas que durara el Recordatorio [0..23] -> ");
            }while(!(horaEstimada >= 0 && horaEstimada <= 23));
            do{
                minutosEstimados = comprobarScanner("Introduce los minutos que durara el Recordatorio [0 ò 30] -> ");
            }while(!(minutosEstimados == 0 || minutosEstimados == 30));
            System.out.println("Introduzca el concepto del recordatorio -> ");
            entrada.nextLine();
            concepto = entrada.nextLine();
            urg = urgente == 1;
            horaEst = LocalTime.of(horaEstimada, minutosEstimados);
            LocalTime l = null;
            dias[mes][dia].crearTarea(l,urg,concepto, diaentero,horaEst);
        }
    }//crearTarea()
    
    public void borrarRecordatorio()
    {
        int id;
        boolean encontrado = false;
        do{
            id = comprobarScanner("Introduce el ID del recordatorio a borrar -> ");
        }while(!(id > 0));
        for (int i = 0; i < MESES && !encontrado; i++)
            for (int j = 0; j < diasUtilizados[i] && !encontrado; j++)
                if(dias[i][j] != null)
                    encontrado = dias[i][j].eliminarRecordatorio(id);
        if(!encontrado)
            System.out.println("\nNo se ha eliminado el Recordatorio porque no se ha encontado");
        else
            System.out.println("\nSe ha eliminado correctamente el Recordatorio");
    }//borrarRecordatorio()
    
    public void borrarTarea()
    {
        int id;
        boolean encontrado = false;
        do{
            id = comprobarScanner("Introduce el ID de la tarea a borrar -> ");
        }while(!(id > 0));
        for (int i = 0; i < MESES && !encontrado; i++)
            for (int j = 0; j < diasUtilizados[i] && !encontrado; j++)
                if(dias[i][j] != null)
                    encontrado = dias[i][j].eliminarTarea(id);
        if(!encontrado)
            System.out.println("\nNo se ha eliminado el Recordatorio porque no se ha encontado");
        else
            System.out.println("\nSe ha eliminado correctamente el Recordatorio");
    }//borrarTarea()
    
    public void imprimirEventosDia()
    {
        int mes,dia;
        do{
            mes = comprobarScanner("Introduce el mes que quieres ver los Eventos Creados -> ");
        }while(!(mes >= 1 && mes <= 12));
        do{
            dia = comprobarScanner("Introduce el dia que quieres crear el evento -> ");
            if (!(dia-1 >= 1 && dia-1 <= diasUtilizados[mes-1]))
                System.out.println("Los dias seleccionados no estan disponibles para el mes " + mes);
        }while(!(dia-1 >= 1 && dia-1 <= diasUtilizados[mes-1]));
            if(dias[mes-1][dia-1] != null)
                dias[mes-1][dia-1].tratarInfo();
    }//imprimirEventosDia()
    
    public void imprimirEventosMes()
    {
        int mes;
        do{
            mes = comprobarScanner("Introduce el mes que quieres ver los Eventos Creados -> ");
        }while(!(mes >= 1 && mes <= 12));
        for (int i = 0; i < diasUtilizados[mes-1]; i++)
            if(dias[mes-1][i] != null)
                dias[mes-1][i].tratarInfo();
    }//imprimirEventosMes()
    
    public void imprimirTodosLosEventos()
    {
        for (int i = 0; i < MESES; i++)
            for (int j = 0; j < diasUtilizados[i]; j++)
                if(dias[i][j] != null)
                    dias[i][j].tratarInfo();
    }//imprimirTodosLosEventos()
    
    public void imprimirEventoEspecifico()
    {
        int mes,dia,hora,minutos,pos;
        do{
            mes = comprobarScanner("Introduce el mes que quieres ver los Eventos Creados -> ");
        }while(!(mes >= 1 && mes <= 12));
        do{
            dia = comprobarScanner("Introduce el dia que quieres crear el evento -> ");
            if (!(dia-1 >= 1 && dia-1 <= diasUtilizados[mes-1]))
                System.out.println("Los dias seleccionados no estan disponibles para el mes " + mes);
        }while(!(dia-1 >= 1 && dia-1 <= diasUtilizados[mes-1]));
        do{
            hora = comprobarScanner("Introduce las horas que durara el Recordatorio [0..23]");
        }while(!(hora >= 0 && hora <= 23));
        do{
            minutos = comprobarScanner("Introduce los minutos que durara el Recordatorio [0 ò 30]");
        }while(!(minutos == 0 || minutos == 30));
        LocalTime tiempo = LocalTime.of(hora, minutos);
        if(dias[mes-1][dia-1] != null)
            dias[mes-1][dia-1].tratarInfoEspecifico(tiempo);
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
