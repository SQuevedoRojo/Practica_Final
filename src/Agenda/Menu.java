package Agenda;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase que se encarga del menu
 * @author Asier Sergio
 * @version 1.0
 */
public class Menu 
{
    private Scanner entrada = new Scanner(System.in);
    public Menu() {}
    
    public void opcionesPrincipales(Principal p) throws AWTException, InterruptedException
    {
        int opcionPrincipal = -1;
        do {            
            limpiar();
            System.out.println("MENU");
            System.out.println("\t1.- Men� de Eventos y Tareas");
            System.out.println("\t2.- Men� de Lista de Contactos");
            System.out.println("\t3.- Men� para Imprimir los Dias de Calendario de un Mes");
            System.out.println("\t4.- Salir");
            opcionPrincipal = comprobarScanner(4);
            switch (opcionPrincipal) {
                case 1:opcionesEventosTareas(p);break;
                case 2:opcionesContactos(p);break;
                case 3:opcionesImprimirDias(p);break;
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
                System.out.println("\n\t\tNo has introducido un n�mero entero");
                entrada.nextLine();
            }
            if (!(opcion >= 1 && opcion <= opMax))
                System.out.println("\n\t\tIntroduce una opcion en el rango 1.."+opMax);
        }while(!(opcion >= 1 && opcion <= opMax));
        return opcion;
    }//comprobarScanner()
    
    public void limpiar() throws AWTException, InterruptedException
    {
        Robot limpiar = new Robot();
        limpiar.keyPress(KeyEvent.VK_CONTROL);
        limpiar.keyPress(KeyEvent.VK_L);
        limpiar.keyRelease(KeyEvent.VK_CONTROL);
        limpiar.keyRelease(KeyEvent.VK_L);
        Thread.sleep(250);
    }//limpiar()
    
    private void opcionesEventosTareas(Principal p) throws AWTException, InterruptedException
    {
        int op = -1;
        String enter;
        limpiar();
        System.out.println("\n\t1.- Crear un Evento Recordatorio");
        System.out.println("\t2.- Crear un Evento Tarea");
        System.out.println("\t3.- Borrar un Evento Recordatorio");
        System.out.println("\t4.- Borrar un Evento Tarea");
        System.out.println("\t5.- Imprimir todos los eventos de un d�a determinado");
        System.out.println("\t6.- Imprimir todos los eventos de un mes determinado");
        System.out.println("\t7.- Imprimir todos los eventos de la agenda");
        System.out.println("\t8.- Imprimir un evento por mes, d�a y hora");
        System.out.println("\t9.- Leer de un fichero todos los eventos de un a�o");
        System.out.println("\t10.- Guardar de un fichero de texto todos los eventos de un a�o");
        System.out.println("\t11.- Guardar en un fichero de texto todos los eventos de un mes");
        System.out.println("\t12.- Guardar en un fichero de texto todos los eventos de un d�a");
        System.out.println("\t13.- Salir");
        op = comprobarScanner(13);
        limpiar();
        switch (op) 
        {
            case 1: p.crearEventos(1); break;
            case 2: p.crearEventos(2);break;
            case 3: p.borrarRecordatorio(); break;
            case 4: p.borrarTarea(); break;
            case 5: p.imprimirEventosDia(); break;
            case 6: p.imprimirEventosMes(); break;
            case 7: p.imprimirTodosLosEventos(); break;
            case 8: p.imprimirEventoEspecifico(); break;
            case 9: p.leerEventosFichero(); break;
            case 10: p.guardarEventosAnno(); break;
            case 11: p.guardarEventosMes(); break;
            case 12: p.guardarEventosDia(); break;
        }
        entrada.nextLine();
        System.out.println("\nPulsa enter para continuar");
        enter = entrada.nextLine();
    }//opcionesEventosTareas()
    
    private void opcionesImprimirDias(Principal p) throws AWTException, InterruptedException
    {
        int op = -1;
        limpiar();
        System.out.println("\n\tIntroduce el Mes del que Quieres Imprimir los Dias del Calendario");
        op = comprobarScanner(1);
    }//opcionesImprimirDias()
    
    private void opcionesContactos(Principal p) throws AWTException, InterruptedException
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
