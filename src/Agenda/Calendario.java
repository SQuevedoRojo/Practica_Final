package Agenda;

import java.util.GregorianCalendar;

/**
 * Clase Calendario la cual muestra por pantalla un calendario de un mes y año especificos
 * @author Asier Sergio
 * @version 1.0
 */
public class Calendario implements Constantes_Colores
{
    /**
     * Metodo el cual imprime un calendario de un mes y año especificos
     * 
     * @param mes variable introducida por el usuario la cual representa el mes a imprimir
     * @param anno variable introducida por el usuario la cual representa el año a imprimir
     */
    public static void calendarioPorMes(int mes, int anno) 
    {
        int primerDia = 1;

        GregorianCalendar fecha = new GregorianCalendar(anno, mes - 1, primerDia);
        GregorianCalendar fecha2 = new GregorianCalendar(anno, mes, primerDia);
        primerDia = fecha.get(GregorianCalendar.DAY_OF_WEEK);

        System.out.println(Constantes_Colores.ANSI_BLUE_BACKGROUND+ANSI_BLACK+"L  M  X   J  V  S  D"+Constantes_Colores.ANSI_RESET);
        switch (primerDia) {
            case 1:
                System.out.print("                  ");
                break;
            case 2:
                break;
            case 3:
                System.out.print("   ");
                break;
            case 4:
                System.out.print("      ");
                break;
            case 5:
                System.out.print("         ");
                break;
            case 6:
                System.out.print("            ");
                break;
            case 7:
                System.out.print("               ");
                break;
        }

        while (!fecha.equals(fecha2)) {
            int dia = fecha.get(GregorianCalendar.DAY_OF_MONTH);
            saberDia(dia, fecha);
            if (fecha.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SUNDAY) 
                System.out.println("");
            fecha.add(GregorianCalendar.DATE, 1);
            if (fecha.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.TUESDAY)
                System.out.print(" ");
        }
        System.out.println("");
    }//calendarioPorMes()
    
    /**
     * Metodo empleado para imprimirlo de un color diferente 
     * 
     * @param dia variable la calual guarada el dia para saber su posicion en la semana
     * @param fecha variable la cual sir
     */
    private static void saberDia(int dia,GregorianCalendar fecha)
    {
        if(dia < 10)
        {
            if(fecha.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SUNDAY || 
                    fecha.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SATURDAY)
                System.out.print(" " + Constantes_Colores.ANSI_RED + dia + Constantes_Colores.ANSI_RESET + " ");
            else
                System.out.print(" " + Constantes_Colores.ANSI_GREEN + dia + Constantes_Colores.ANSI_RESET + " ");
        }
        else
        {
            if(fecha.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SUNDAY || 
                    fecha.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SATURDAY)
                System.out.print(Constantes_Colores.ANSI_RED + dia + Constantes_Colores.ANSI_RESET + " ");
            else
                System.out.print(Constantes_Colores.ANSI_GREEN + dia + Constantes_Colores.ANSI_RESET + " ");
        }
    }//saberDia()
}//class