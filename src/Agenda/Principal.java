package Agenda;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
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
        int anno = comprobarScanner();
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
    
    private int comprobarScanner()
    {
        int opcion = -1;
        do{
            try 
            {
                System.out.print("\tIntroduce el año que desee para crear la agenda -> ");
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
    
    public void crearRecordatorio()
    {
        
    }//crearRecordatorio()
    
    public void crearTarea()
    {
        
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
