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
import java.time.Month;
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
    /**
     * Atributo estatico y constante de la clase Principal para guardar los meses que tiene un año
     */
    private static final int MESES = 12;
    /**
     * Atributo estatico y constante de la clase Principal para guardar los dias que tiene un mes
     */
    private static final int DIAS = 31;
    /**
     * Atributo del objeto principal encargada de leer datos introducidos por teclado
     */
    private Scanner entrada = new Scanner(System.in);
    /**
     * Atributo constante del objeto principal encargado de controlar los dias de cada mes
     */
    private final int diasUtilizados[] = {31,diasDisponibles(),31,30,31,30,31,31,30,31,30,31};
    /**
     * Atributo del objeto principal encargado de guardar en un array bidimensional objetos Dias, y a su vez guardar los Eventos.
     */
    private Dia[][] dias;
    /**
     * Atributo del objeto principal que es un objeto menu para que el usuario decida una opcion especifica
     */
    private Menu menu;
    /**
     * Atributo del objeto principal para leer ficheros
     */
    private FileReader fr = null;
    /**
     * Atributo del objeto principal para escribir en ficheros
     */
    private FileWriter fw = null;
    /**
     * Atributo constante del objeto principal para guardar el año del cual quiere crear la agenda
     */
    private final int anno = comprobarScanner("\tIntroduce el año que desee para crear la agenda -> ");
    private ArrayList<Contacto> contactos;
    
    public Principal()
    {
        dias = new Dia[MESES][DIAS];
        menu = new Menu();
        contactos = new ArrayList<Contacto>();
    }
    
    public static void main(String[] args) throws AWTException, InterruptedException 
    {
        Principal p = new Principal();
        p.inicio();
    }//main()
    
    /**
     * Método encargado de la ejecución principal del programa
     * @throws AWTException Excepciones que sirven para limpiar la pantalla
     * @throws InterruptedException Excepciones que sirven para limpiar la pantalla 
     */
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
            System.out.println("\nNo se ha eliminado la Tarea porque no se ha encontado");
        else
            System.out.println("\nSe ha eliminado correctamente la Tarea");
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
        entrada.nextLine();
        String nomFic;
        System.out.print("Introduce el nombre del fichero incluyendo la extension del mismo -> ");
        nomFic=entrada.nextLine();
        try {
            fr = new FileReader("./src/FICHEROS/"+nomFic);
            BufferedReader br = new BufferedReader(fr);
            String info = br.readLine();
            while (info != null) 
            {
                String campos[] = info.split("\\|");
                saberCampos(campos);
                info = br.readLine();
            }
        } 
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if (fr != null) 
                    fr.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());                                                               
            }
        }
    }//leerEventosFichero()
    
    private void saberCampos(String campos[])
    {
        try
        {
            LocalDate fechaEvento;
            boolean diaentero = false;
            if(campos[0].trim().equalsIgnoreCase("Dia Entero"))
            {
                diaentero = true;
                String fecha[] = campos[1].split("\\-");
                fechaEvento = LocalDate.of(Integer.parseInt(fecha[0]), saberMes(fecha[1].trim()), Integer.parseInt(fecha[2]));
                if(campos[2].trim().equalsIgnoreCase("Recordatorio"))
                {
                    boolean anual = false;
                    String concepto = campos[3].trim();
                    if(campos[4].trim().equalsIgnoreCase("Es anual"))
                        anual = true;
                    annadirRecordatorio(diaentero, fechaEvento, anual, concepto, null);
                }
                else
                {
                    boolean urgente = false;
                    String concepto = campos[3].trim();
                    LocalTime duracion;
                    String tDuracion[] = campos[5].split("\\:");
                    if(campos[4].trim().equalsIgnoreCase("Es urgente"))
                        urgente = true;
                    duracion = LocalTime.of(Integer.parseInt(tDuracion[0]), Integer.parseInt(tDuracion[1]));
                    annadirTarea(diaentero, fechaEvento, urgente, concepto,null,duracion);
                }
            }
            else
            {
                String fecha[] = campos[0].split("\\-");
                fechaEvento = LocalDate.of(Integer.parseInt(fecha[0]), saberMes(fecha[1].trim()), Integer.parseInt(fecha[2]));
                String thora[] = campos[1].split("\\:");
                String concepto = campos[3].trim();
                LocalTime hora = LocalTime.of(Integer.parseInt(thora[0]), Integer.parseInt(thora[1]));
                if(campos[2].trim().equalsIgnoreCase("Recordatorio"))
                {
                    boolean anual = false;
                    if(campos[4].trim().equalsIgnoreCase("Es anual"))
                        anual = true;
                    annadirRecordatorio(diaentero, fechaEvento, anual, concepto, hora);
                }
                else
                {
                    boolean urgente = false;
                    String tDuracion[] = campos[5].split("\\:");
                    LocalTime duracion;
                    if(campos[4].trim().equalsIgnoreCase("Es urgente"))
                        urgente = true;
                    duracion = LocalTime.of(Integer.parseInt(tDuracion[0]), Integer.parseInt(tDuracion[1]));
                    annadirTarea(diaentero, fechaEvento, urgente, concepto, hora, duracion);
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("No se ha podido cargar el evento del fichero");
        }
    }//separarCampos()
    
    private void annadirRecordatorio(boolean diaentero,LocalDate fecha,boolean anual,String concepto,LocalTime hora)
    {
        if(diaentero)
            if(dias[fecha.getMonthValue()-1][fecha.getDayOfMonth()-1] != null)
                dias[fecha.getMonthValue()-1][fecha.getDayOfMonth()-1].crearRecordatorio(null, anual, concepto, diaentero);
            else
            {
                dias[fecha.getMonthValue()-1][fecha.getDayOfMonth()-1] = new Dia(fecha);
                dias[fecha.getMonthValue()-1][fecha.getDayOfMonth()-1].crearRecordatorio(null, anual, concepto, diaentero);
            }
        else
            if(dias[fecha.getMonthValue()-1][fecha.getDayOfMonth()-1] != null)
                dias[fecha.getMonthValue()-1][fecha.getDayOfMonth()-1].crearRecordatorio(hora, anual, concepto, diaentero);
            else
            {
                dias[fecha.getMonthValue()-1][fecha.getDayOfMonth()-1] = new Dia(fecha);
                dias[fecha.getMonthValue()-1][fecha.getDayOfMonth()-1].crearRecordatorio(hora, anual, concepto, diaentero);
            }
            
    }//annadirRecordatorio()
    
    private void annadirTarea(boolean diaentero,LocalDate fecha,boolean urgente,String concepto,LocalTime hora,LocalTime duracion)
    {
        if(diaentero)
            if(dias[fecha.getMonthValue()-1][fecha.getDayOfMonth()-1] != null)
                dias[fecha.getMonthValue()-1][fecha.getDayOfMonth()-1].crearTarea(null,urgente,concepto, diaentero,duracion);
            else
            {
                dias[fecha.getMonthValue()-1][fecha.getDayOfMonth()-1] = new Dia(fecha);
                dias[fecha.getMonthValue()-1][fecha.getDayOfMonth()-1].crearTarea(null,urgente,concepto, diaentero,duracion);
            }
        else
            if(dias[fecha.getMonthValue()-1][fecha.getDayOfMonth()-1] != null)
                dias[fecha.getMonthValue()-1][fecha.getDayOfMonth()-1].crearTarea(hora,urgente,concepto, diaentero,duracion);
            else
            {
                dias[fecha.getMonthValue()-1][fecha.getDayOfMonth()-1] = new Dia(fecha);
                dias[fecha.getMonthValue()-1][fecha.getDayOfMonth()-1].crearTarea(hora,urgente,concepto, diaentero,duracion);
            }
    }//annadirTarea()
    
    private Month saberMes(String mes)
    {
        Month enumMes = null;
        switch (mes) 
        {
            case "JANUARY" -> enumMes = Month.JANUARY;
            case "FEBRUARY" -> enumMes = Month.FEBRUARY;
            case "MARCH" -> enumMes = Month.MARCH;
            case "APRIL" -> enumMes = Month.APRIL;
            case "MAY" -> enumMes = Month.MAY;
            case "JUNE" -> enumMes = Month.JUNE;
            case "JULY" -> enumMes = Month.JULY;
            case "AUGUST" -> enumMes = Month.AUGUST;
            case "SEPTEMBER" -> enumMes = Month.SEPTEMBER;
            case "OCTOBER" -> enumMes = Month.OCTOBER;
            case "NOVEMBER" -> enumMes = Month.NOVEMBER;
            case "DECEMBER" -> enumMes = Month.DECEMBER;
        }
        return enumMes;
    }//saberMes()
    
    public void guardarEventosAnno()
    {
        for (int i = 0; i < MESES; i++)
            for (int j = 0; j < diasUtilizados[i]; j++)
                if(dias[i][j] != null)
                    dias[i][j].imprimirEventos();
    }//guardarEventosAnno()
    
    public void guardarEventosMes() //OPCIONALES
    {
        int mes;
        do{
            mes = comprobarScanner("Introduce el mes que quieres ver los Eventos Creados -> ");
        }while(!(mes >= 1 && mes <= 12));
        for (int i = 0; i < diasUtilizados[mes]; i++)
            if (dias[mes-1][i] != null)
                dias[mes-1][i].imprimirEventos();
    }//guardarEventosMes()
    
    public void guardarEventosDia() //OPCIONALES
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
        if (dias[mes-1][dia-1] != null)
            dias[mes-1][dia-1].imprimirEventos();
        
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
    
    /**
     * Método invocado desde la clase Menu para leer desde un fichero los contactos y guardarlo en un ArrayList
     */
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
                contactos.add(new Contacto(campos[1], campos[0], campos[2]));
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
        System.out.println("Se han guardado todos los contactos en el ArrayList");
    }//leerFicheroContactos()
    
    public void guardarContactos()
    {
        String cadena;
        try 
        {
            fw = new FileWriter("./src/FICHEROS/guardar-contactos.dat");
            PrintWriter pw = new PrintWriter(fw);
            for (Contacto contacto : contactos) 
            {
                cadena = contacto.getApellido() + "|" + contacto.getNombre() + "|" + contacto.getCorreo();
                pw.println(cadena);
            }
            pw.flush();
        } catch (FileNotFoundException e) {
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
        System.out.println("Se han guardado todos los contactos en el fichero llamado 'guardar-contactos.dat'");
    }//guardarContactos()
    
    public void listarContactos()
    {
        for (Contacto contacto : contactos)
            System.out.println(contacto.getApellido() + "|" + contacto.getNombre() + "|" + contacto.getCorreo());
    }//listarContactos()
    
    public void crearContacto()
    {
        entrada.nextLine();
        String nombre,apellidos,correo;
        boolean repetido = false;
        System.out.print("Introduce el nombre del contacto a crear -> ");
        nombre = entrada.nextLine();
        System.out.print("Introduce el apellido del contacto a crear -> ");
        apellidos = entrada.nextLine();
        System.out.print("Introduce el correo electronico del contacto a crear -> ");
        correo = entrada.nextLine();
        
        for (Contacto contacto : contactos)
            if(correo.equalsIgnoreCase(contacto.getCorreo()))
            {
                System.out.println("No se puede añadir el contacto porque ya existe ese correo electronico");
                repetido = true;
                break;
            }
        if(!repetido)
            contactos.add(new Contacto(nombre, apellidos, correo));
    }//crearContacto()
    
    public void buscarContacto()
    {
        entrada.nextLine();
        String correo;
        boolean encontrado = false;
        System.out.print("Introduce el correo electronico del contacto a buscar -> ");
        correo = entrada.nextLine();
        for (Contacto contacto : contactos)
            if(correo.equalsIgnoreCase(contacto.getCorreo()))
            {
                encontrado = true;
                System.out.println("Contacto encontrado");
                System.out.println(contacto.getApellido() + "|" + contacto.getNombre() + "|" + contacto.getCorreo());
            }
        if(encontrado == false)
            System.out.println("Contacto no encontrado");
    }//buscarContacto()
}//class
