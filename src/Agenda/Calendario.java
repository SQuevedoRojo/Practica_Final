package Agenda;

import java.util.GregorianCalendar;


public class Calendario implements Constantes_Colores
{
    public static void calendarioPorMes(int mes, int anno) 
    {
        int primerDia = 1;

        GregorianCalendar fecha = new GregorianCalendar(anno, mes - 1, primerDia);
        GregorianCalendar fecha2 = new GregorianCalendar(anno, mes, primerDia);
        primerDia = fecha.get(GregorianCalendar.DAY_OF_WEEK);

        System.out.println(Constantes_Colores.ANSI_BLUE_BACKGROUND+"L  M  X   J  V  S  D"+Constantes_Colores.ANSI_RESET);
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
    }
    
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
    }
}