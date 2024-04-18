package Agenda;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * @author Asier Sergio
 */
public class Menu 
{
    private Scanner entrada = new Scanner(System.in);
    public Menu() {}
    
    public void opcionesPrincipales() throws AWTException, InterruptedException
    {
        int opcionPrincipal = -1;
        do {            
            limpiar();
            System.out.println("MENU");
            System.out.println("\t1.- Menú de Eventos y Tareas");
            System.out.println("\t2.- Menú de Lista de Contactos");
            System.out.println("\t3.- Menú para Imprimir los Dias de Calendario de un Mes");
            System.out.println("\t4.- Salir");
            opcionPrincipal = comprobarScanner(4);
            switch (opcionPrincipal) {
                case 1:opcionesEventosTareas();break;
                case 2:opcionesContactos();break;
                case 3:opcionesImprimirDias();break;
            }
        }while (opcionPrincipal != 4);
        System.out.println("|PROGRAMA TERMINADO|");
    }//opciones()
    
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
    
    private void limpiar() throws AWTException, InterruptedException
    {
        Robot limpiar = new Robot();
        limpiar.keyPress(KeyEvent.VK_CONTROL);
        limpiar.keyPress(KeyEvent.VK_L);
        limpiar.keyRelease(KeyEvent.VK_CONTROL);
        limpiar.keyRelease(KeyEvent.VK_L);
        Thread.sleep(250);
    }//limpiar()
    
    private void opcionesEventosTareas() throws AWTException, InterruptedException
    {
        int op = -1;
        limpiar();
        System.out.println("\n\t1.- Crear un Evento Recordatorio");
        System.out.println("\t2.- Crear un Evento Tarea");
        System.out.println("\t3.- Borrar un Evento Recordatorio");
        System.out.println("\t4.- Borrar un Evento Tarea");
        System.out.println("\t5.- Imprimir todos los eventos de un día determinado");
        System.out.println("\t6.- Imprimir todos los eventos de un mes determinado");
        System.out.println("\t7.- Imprimir un evento por mes, día y hora");
        System.out.println("\t8.- Leer de un fichero todos los eventos de un año");
        System.out.println("\t9.- Guardar de un fichero de texto todos los eventos de un año");
        System.out.println("\t10.- Guardar en un fichero de texto todos los eventos de un mes");
        System.out.println("\t11.- Guardar en un fichero de texto todos los eventos de un día");
        System.out.println("\t12.- Salir");
        op = comprobarScanner(12);
        switch (op) 
        {
            case 1: break;
            case 2: break;
            case 3: break;
            case 4: break;
            case 5: break;
            case 6: break;
            case 7: break;
            case 8: break;
            case 9: break;
            case 10: break;
            case 11: break;
        }
    }//opcionesEventosTareas()
    
    private void opcionesImprimirDias() throws AWTException, InterruptedException
    {
        int op = -1;
        limpiar();
        System.out.println("\n\tIntroduce el Mes del que Quieres Imprimir los Dias del Calendario");
        op = comprobarScanner(1);
    }//opcionesImprimirDias()
    
    private void opcionesContactos() throws AWTException, InterruptedException
    {
        int op = -1;
        limpiar();
        System.out.println("\n\t1.- Leer Contactos");
        System.out.println("\t2.- Crear un Nuevo Contacto");
        System.out.println("\t3.- Listar Todos los Contactos");
        System.out.println("\t4.- Buscar un Contacto");
        System.out.println("\t5.- Guardar Contactos");
        System.out.println("\t6.- Salir");
        op = comprobarScanner(6);
        switch (op) 
        {
            case 1: break;
            case 2: break;
            case 3: break;
            case 4: break;
            case 5: break;
        }
    }//opcionesContactos()
}//class
