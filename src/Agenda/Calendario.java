package Agenda;

import java.util.GregorianCalendar;


public class Calendario
{
    private static final int DOMINGO=1;
    private static final int LUNES=2;
    private static final int MARTES=3;
    private static final int MIERCOLES=4;
    private static final int JUEVES=5;
    private static final int VIERNES=6;
    private static final int SABADO=7;

    public static void calendarioPorMes(int mes,int anno)
    {
        int contador1 = 0,contador2 = 0;
        int primerdia=1;

        GregorianCalendar fecha=new GregorianCalendar(anno,mes-1,primerdia);
        GregorianCalendar fecha2=new GregorianCalendar(anno,mes,primerdia);
        primerdia=fecha.get(fecha.DAY_OF_WEEK);

        System.out.println("L"+"  "+"   M"+"  "+"  X"+"  "+"  J"+"  "+"  V"+"  "+"  S"+"  "+"  D"+"  ");
        switch (primerdia){
            
            case DOMINGO:System.out.print(" ");break;
            case LUNES:break;
            case MARTES:System.out.print(" ");break;
            case MIERCOLES:System.out.print(" ");break;
            case JUEVES:System.out.print(" ");break;
            case VIERNES:System.out.print(" ");break;
            case SABADO:System.out.print(" ");
        }
        while(fecha.equals(fecha2)==false){
            if(fecha.DATE < 10)
                if(contador2 == 0 && contador1 != 0)
                    System.out.print(fecha.get(fecha.DATE)+"    ");
                else
                     System.out.print(fecha.get(fecha.DATE)+"   ");
            else
                System.out.print(fecha.get(fecha.DATE)+" ");
            
            if(fecha.get(GregorianCalendar.DAY_OF_WEEK)==GregorianCalendar.SUNDAY)
            {
                System.out.println("");
                contador2++;
            }
            fecha.add(GregorianCalendar.DATE,1);
            if(fecha.get(GregorianCalendar.DAY_OF_WEEK)== 3)
            {
                if (contador1 == 0)
                    System.out.print("  ");
                if(contador1  == 1)
                    System.out.print("  ");
                contador1++;
            }
        }
        System.out.println("");
    }
}
