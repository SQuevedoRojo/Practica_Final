package Agenda;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Principal 
{
    public static void main(String[] args) 
    {
        System.out.println("Hola");
        System.out.println("");
        System.out.println("...");
        System.out.println("quiero que me funcione el pull y no se meta en rebasing");
        System.out.println("Pues no me jodas el repositorio");
        System.out.println("");
        System.out.println("ada");
        System.out.println("me tenia qie cambiar de rama");
    }//main()
    
    private void limpiar() throws AWTException, InterruptedException
    {
        Robot limpiar = new Robot();
        limpiar.keyPress(KeyEvent.VK_CONTROL);
        limpiar.keyPress(KeyEvent.VK_L);
        limpiar.keyRelease(KeyEvent.VK_CONTROL);
        limpiar.keyRelease(KeyEvent.VK_L);
        Thread.sleep(250);
    }
}//class
