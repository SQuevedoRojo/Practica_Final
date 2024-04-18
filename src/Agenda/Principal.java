package Agenda;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
/**
 * 
 * @author Asier Sergio
 */
public class Principal 
{
    
    public static void main(String[] args) throws AWTException, InterruptedException 
    {
        Principal p = new Principal();
        p.prueba();
    }//main()
    
    public void prueba() throws AWTException, InterruptedException
    {
        Menu menu = new Menu();
        menu.opcionesPrincipales();
    }
}//class
