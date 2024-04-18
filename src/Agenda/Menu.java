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
    public Menu() {
    }
    
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
            if(opcionPrincipal != 4)
            {
                switch (opcionPrincipal) {
                    case 1:
                        
                        break;
                    case 2:
                        
                        break;
                        
                    case 3:
                        
                        break;
                }
            }
        }while (opcionPrincipal != 4);
        System.out.println("PROGRAMA TERMINADO");
    }//opciones()
    
    private int comprobarScanner(int opMax)
    {
        int opcion = -1;
        boolean correcto = true;
        do{
            try 
            {
                System.out.print("\n\tElige la opcion correspondiente -> ");
                opcion = entrada.nextInt();
            } 
            catch (InputMismatchException e)
            {
                System.out.println("\n\tNo has introducido un número entero");
                entrada.nextLine();
            }
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
        System.out.println("1.- Crear un Evento Recordatorio");
        System.out.println("2.- Crear un Evento Tarea");
        System.out.println("3.- Borrar un Evento Recordatorio");
        System.out.println("4.- Borrar un Evento Tarea");
        System.out.println("5.- Imprimir todos los eventos de un día determinado");
        System.out.println("6.- Imprimir todos los eventos de un mes determinado");
        System.out.println("7.- Imprimir un evento por mes, día y hora");
        System.out.println("8.- Leer de un fichero todos los eventos de un año");
        System.out.println("9.- Guardar de un fichero de texto todos los eventos de un año");
        System.out.println("10.- Guardar en un fichero de texto todos los eventos de un mes");
        System.out.println("11.- Guardar en un fichero de texto todos los eventos de un día");
        System.out.println("12.- Salir");
    }//opcionesEventosTareas()
    
    private void opcionesImprimirDias()
    {
        
    }//opcionesImprimirDias()
    
    private void opcionesContactos()
    {
        
    }//opcionesContactos()
}
