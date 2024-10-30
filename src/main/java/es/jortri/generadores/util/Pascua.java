package es.jortri.generadores.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Clase para calcular las fecha necesarias de la pascua (Semana Santa) religiosa
 * NOTA: Codigo obtenido de https://es.wikipedia.org/wiki/Anexo:Implementaciones_del_algoritmo_de_c%C3%A1lculo_de_la_fecha_de_Pascua#Algoritmo_en_Java
 */
public class Pascua {
    private static int M = 24;
    private static int N = 5;
    
    //ejercicio a comprobar
    private int anno;


    public Pascua(int anno) {
        if (anno >= 1583 && anno <= 1699) {
            M = 22;
            N = 2;
        } else if (anno >= 1700 && anno <= 1799) {
            M = 23;
            N = 3;
        } else if (anno >= 1800 && anno <= 1899) {
            M = 23;
            N = 4;
        } else if (anno >= 1900 && anno <= 2099) {
            M = 24;
            N = 5;
        } else if (anno >= 2100 && anno <= 2199) {
            M = 24;
            N = 6;
        } else if (anno >= 2200 && anno <= 2299) {
            M = 25;
            N = 0;
        }
        this.anno = anno;
    }

    /**
     * Obtiene la fecha correspondiente al domingo de resureccion de la Semana Santa
     * del ejercicio con que se inicializo el objeto.
     * NOTA: internamente calcula las fechas de inicio/fin del periodo vacacional 
     * de Semana SAnta
     * @return Domingo de resurección
     */
    public Date getFechaPascua() {
        int a, b, c, d, e, dia, mes;

        //Cálculo de residuos
        a = anno % 19;
        b = anno % 4;
        c = anno % 7;
        d = (19 * a + M) % 30;
        e = (2 * b + 4 * c + 6 * d + N) % 7;

        //Dado que el Domingo de Resurrección puede caer en marzo o abril, Gauss separó en 
        //dos operaciones distintas la posible fecha, siendo la fecha correcta la que única 
        //que existe tras realizar los cálculos
        if (d + e < 10) {
            dia = d + e + 22;
            mes = Calendar.MARCH;
        } else {
            dia = d + e - 9;
            mes = Calendar.APRIL;
        }

        //Excepciones especiales. Existen dos excepciones a tener en cuenta:
        //  1)Si la fecha obtenida es el 26 de abril...la Pascua caerá en el 19 de abril. 
        //  2)Si la fecha obtenida es el 25 de abril, con d = 28, e = 6 y a > 10.…la Pascua caerá en el 18 de abril.        
        if (dia == 26 && mes == Calendar.APRIL)
            dia = 19;
        if (dia == 25 && mes == Calendar.APRIL && d == 28 && e == 6 && a > 10)
            dia = 18;

        Calendar fechaPascua = Calendar.getInstance();
        fechaPascua.set(Calendar.DAY_OF_MONTH, dia);
        fechaPascua.set(Calendar.MONTH, mes);
        fechaPascua.set(Calendar.YEAR, this.anno);
        //dejamos el time a las 12:00:00
        fechaPascua.set(Calendar.HOUR_OF_DAY, 12);
        fechaPascua.set(Calendar.MINUTE, 0);
        fechaPascua.set(Calendar.SECOND, 0);
        fechaPascua.set(Calendar.MILLISECOND, 0);        
        
        //se devuelve el domingo de resureccion
        return fechaPascua.getTime();
    }
}
