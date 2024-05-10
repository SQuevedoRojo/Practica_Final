package Agenda;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase que se encarga de las opciones correspondientes a la Agenda
 * @author Asier Sergio
 * @version 1.0
 */
public class Menu 
{
    /**
     * atributo scanner
     */
    private Scanner entrada = new Scanner(System.in);
    
    /**
     * Método llamado desde la clase Principal para que el usuario elija entre diferentes opciones
     * @param p Objeto de la clase Principal
     * @throws AWTException Excepciones para poder limpiar la pantalla
     * @throws InterruptedException Excepciones para poder limpiar la pantalla 
     */
    public void opcionesPrincipales(Principal p) throws AWTException, InterruptedException
    {
        int opcionPrincipal;
        do {            
            limpiar();
            System.out.println("MENU");
            System.out.println("\t1.- Menú de Eventos y Tareas");
            System.out.println("\t2.- Menú de Lista de Contactos");
            System.out.println("\t3.- Menú para Imprimir los Dias de Calendario de un Mes");
            System.out.println("\t4.- Salir");
            opcionPrincipal = comprobarScanner(4);
            switch (opcionPrincipal) {
                case 1 -> {opcionesEventosTareas(p);continuar();}
                case 2 -> {opcionesContactos(p);continuar();}
                case 3 -> {opcionesImprimirDias(p);continuar();}
            }
        }while (opcionPrincipal != 4);
        System.out.println("|PROGRAMA TERMINADO|");
    }//opciones()
    
    /**
     * Método para comprobar que el usuario introduzca solamente numeros enteros
     * @param opMax Numero entero que regula la opcion máxima que puede elegir el usuario
     * @return Numero entero introducido por el usuario
     */
    private int comprobarScanner(int opMax)
    {
        int opcion = -1;
        do{
            try 
            {
                System.out.print("\n\t\tElige la opcion correspondiente [1.."+opMax+"] -> ");
                opcion = entrada.nextInt();
            } 
            catch (InputMismatchException e)
            {
                System.out.println("\n\t\tNo has introducido un número entero");
                entrada.nextLine();
            }
            if (!(opcion >= 1 && opcion <= opMax))
                System.out.println("\n\t\tIntroduce una opcion en el rango 1.."+opMax);
        }while(!(opcion >= 1 && opcion <= opMax));
        return opcion;
    }//comprobarScanner()
    
    /**
     * Método para que al finalizar la opción de un submenu el usuario pulse una tecla para continuar
     */
    private void continuar()
    {
        String enter;
        System.out.println("\nPulsa enter para continuar");
        entrada.nextLine();
        enter = entrada.nextLine();
    }//continuar()
    
    /**
     * Método encargado de limpiar la pantalla
     * @throws AWTException Excepciones para poder limpiar la pantalla
     * @throws InterruptedException Excepciones para poder limpiar la pantalla 
     */
    public void limpiar() throws AWTException, InterruptedException
    {
        Robot limpiar = new Robot();
        limpiar.keyPress(KeyEvent.VK_CONTROL);
        limpiar.keyPress(KeyEvent.VK_L);
        limpiar.keyRelease(KeyEvent.VK_CONTROL);
        limpiar.keyRelease(KeyEvent.VK_L);
        Thread.sleep(250);
    }//limpiar()
    
    /**
     * Método encargado de ser un submenu de las opciones referidas a Eventos
     * @param p Objeto de la clase Principal
     * @throws AWTException Excepciones para poder limpiar la pantalla
     * @throws InterruptedException Excepciones para poder limpiar la pantalla 
     */
    private void opcionesEventosTareas(Principal p) throws AWTException, InterruptedException
    {
        int op;
        limpiar();
        System.out.println("\n\t1.- Crear un Evento Recordatorio");
        System.out.println("\t2.- Crear un Evento Tarea");
        System.out.println("\t3.- Borrar un Evento Recordatorio");
        System.out.println("\t4.- Borrar un Evento Tarea");
        System.out.println("\t5.- Imprimir todos los eventos de un día determinado");
        System.out.println("\t6.- Imprimir todos los eventos de un mes determinado");
        System.out.println("\t7.- Imprimir todos los eventos de la agenda");
        System.out.println("\t8.- Imprimir un evento por mes, día y hora");
        System.out.println("\t9.- Leer de un fichero todos los eventos de un año");
        System.out.println("\t10.- Guardar de un fichero de texto todos los eventos de un año");
        System.out.println("\t11.- Guardar en un fichero de texto todos los eventos de un mes");
        System.out.println("\t12.- Guardar en un fichero de texto todos los eventos de un día");
        System.out.println("\t13.- Salir");
        op = comprobarScanner(13);
        limpiar();
        switch (op) 
        {
            case 1 -> p.crearEventos(1);
            case 2 -> p.crearEventos(2);
            case 3 -> p.borrarRecordatorio();
            case 4 -> p.borrarTarea();
            case 5 -> p.imprimirEventosDia();
            case 6 -> p.imprimirEventosMes();
            case 7 -> p.imprimirTodosLosEventos();
            case 8 -> p.imprimirEventoEspecifico();
            case 9 -> p.leerEventosFichero();
            case 10 -> p.guardarEventosAnno();
            case 11 -> p.guardarEventosMes();
            case 12 -> p.guardarEventosDia();
        }
        
    }//opcionesEventosTareas()
    
    /**
     * Método que se encarga de llamar a un método de la clase Principal para poder imprimir un mes en forma de calendario
     * @param p Objeto de la clase Principal
     * @throws AWTException Excepciones para poder limpiar la pantalla
     * @throws InterruptedException Excepciones para poder limpiar la pantalla 
     */
    private void opcionesImprimirDias(Principal p) throws AWTException, InterruptedException
    {
        limpiar();
        p.imprimirMesCalendario();
    }//opcionesImprimirDias()
    
    /**
     * Método encargado de ser un submenu para las opciones referidas a los contactos
     * @param p Objeto de la clase Principal
    * @throws AWTException Excepciones para poder limpiar la pantalla
     * @throws InterruptedException Excepciones para poder limpiar la pantalla 
     */
    private void opcionesContactos(Principal p) throws AWTException, InterruptedException
    {
        int op;
        limpiar();
        System.out.println("\n\t1.- Leer Contactos");
        System.out.println("\t2.- Crear un Nuevo Contacto");
        System.out.println("\t3.- Listar Todos los Contactos");
        System.out.println("\t4.- Buscar un Contacto");
        System.out.println("\t5.- Guardar Contactos");
        System.out.println("\t6.- Salir");
        op = comprobarScanner(6);
        limpiar();
        switch (op) 
        {
            case 1 -> p.leerFicheroContactos();
            case 2 -> p.crearContacto();
            case 3 -> p.listarContactos();
            case 4 -> p.buscarContacto();
            case 5 -> p.guardarContactos();
        }
    }//opcionesContactos()
}//class
