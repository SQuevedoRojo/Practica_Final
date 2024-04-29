package Agenda;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
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
    private FileReader fr = null;
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
    
    /**
     * Método que comprueba si el año introducido por el usuario es bisiesto
     * @return Dias que tiene el mes de febrero dependiendo del año
     */
    private int diasDisponibles()
    {
        anno = comprobarScanner("\tIntroduce el año que desee para crear la agenda -> ");
        int diasFebrero = 28;
        if (annoBisiesto(anno))
            diasFebrero++;
        return diasFebrero;
    }//diasDisponibles()
    
    /**
     * Metodo para comprobar si el año es bisiesto
     * @param anno Año introducido por el usuario
     * @return True -> Año biesto ; False -> Año no bisiesto
     */
    private boolean annoBisiesto(int anno)
    {
        return Year.of(anno).isLeap();
    }//annoBisiesto()
    
    /**
     * Método que comprueba los datos cuando se tiene que llamar a un metodo de la clase Scanner, solo permite introducir numeros en un rango especifico
     * @param mensaje Mensaje para el usuario
     * @return Numero entero que cumple con las condiciones 
     */
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
    
    /**
     * Método que se invoca desde la clase menu
     * @param op Entero que sirve para distinguir de una Tarea o un Evento
     * @throws AWTException Excepciones que sirven para limpiar la pantalla
     * @throws InterruptedException Excepciones que sirven para limpiar la pantalla 
     */
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
    
    /**
     * Método para crear un Recordatorio
     * @param mes Mes introducido por el usuario
     * @param dia Dia introducido por el usuario
     */
    private void crearRecordatorio(int mes,int dia)
    {
        int hora,minutos,anual,diaEntero;
        boolean boolAnno,diaentero;
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
            boolAnno = anual == 1;
            dias[mes][dia].crearRecordatorio(tiempo, boolAnno, concepto, diaentero);
        }
        else
        {
            do{
                anual = comprobarScanner("Introduce 1 si el Recordatorio es de recursividad anual, sino introduzca 0 -> ");
            }while(!(anual == 1 || anual == 0));
            System.out.println("Introduzca el concepto del recordatorio -> ");
            entrada.nextLine();
            concepto = entrada.nextLine();
            boolAnno = anual == 1;
            LocalTime l = null;
            dias[mes][dia].crearRecordatorio(l, boolAnno, concepto, diaentero);
        }
        
    }//crearRecordatorio()
    
    /**
     * Método para crear una Tarea
     * @param mes Mes introducido por el usuario
     * @param dia Dia introducido por el usuario
     */
    private void crearTarea(int mes,int dia)
    {
        int hora,minutos,diaEntero,horaEstimada,minutosEstimados,urgente;
        boolean diaentero,urg;
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
    
    /**
     * Método invocado desde la clase Menu para borrar un Recordatorio con un ID
     */
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
    
    /**
     * Método invocado desde la clase Menu para borrar una Tarea con un ID
     */
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
    
    /**
     * Metodo llamado desde la clase Menu para imprimir los eventos que hay en un dia en especifico
     */
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
    
    /**
     * Método llamado desde la clase Menu para imprimir todods los eventos que haya un mes 
     */
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
    
    /**
     * Método llamado desde la clase Menu para imprimir todos los eventos que se hayan creado en toda la agenda
     */
    public void imprimirTodosLosEventos()
    {
        for (int i = 0; i < MESES; i++)
            for (int j = 0; j < diasUtilizados[i]; j++)
                if(dias[i][j] != null)
                    dias[i][j].tratarInfo();
    }//imprimirTodosLosEventos()
    
    /**
     * Metodo llamado desde la clase Menu para imprimir los eventos que haya en una hora especifica y en un dia especifico
     */
    public void imprimirEventoEspecifico()
    {
        int mes,dia,hora,minutos;
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
    
    public void guardarEventosMes() //OPCIONALES
    {
        
    }//guardarEventosMes()
    
    public void guardarEventosDia() //OPCIONALES
    {
        
    }//guardarEventosDia()
    
    /**
     * Método llamado desde la clase Menu para imprimir un mes en forma de un calendario
     */
    public void imprimirMesCalendario()
    {
        int mes;
        do{
            mes = comprobarScanner("Introduce el mes del que quieres imprimir el calendario -> ");
        }while(!(mes >= 1 && mes <= 12));
        Calendario.calendarioPorMes(mes, anno);
    }//imprimirMesCalendario()
    
    
    public void leerFicheroContactos()
    {
        try
        {
            fr = new FileReader("./src/FICHEROS/contactos.dat");
            BufferedReader br = new BufferedReader(fr);
            String cadena = br.readLine();
            while(cadena != null)
            {
                String campos[] = cadena.split("\\|");
                System.out.println(campos[0] + " *** " + campos[1] + " *** " + campos[2]);
                cadena = br.readLine();
            }
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e) 
        {
            System.out.println(e.getMessage());
        }
        finally 
        {
            try 
            {
                if (fr != null) 
                    fr.close();
            } 
            catch (IOException e) 
            {
                System.out.println(e.getMessage());                                                               
            }
        }
    }//leerFicheroContactos()
}//class
