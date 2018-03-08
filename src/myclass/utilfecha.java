/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author carlos860920
 */
public class utilfecha {

    /**
     * Método para comvertir un texto a tipo <b>Date</b>
     *
     * @param stringFecha Fecha
     * @param formato Formato de fecha
     * @return fecha o nulo
     */
    public static Date convierteStringADate(String stringFecha, String formato) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            Date fecha = sdf.parse(stringFecha);
            return fecha;
        } catch (ParseException e) {
            System.err.println("convierteStringADate -- Ha ocurrido un error en la conversion de fechas" + e);
            return null;
        }
    }
    
     public static String convierteDateAString(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        //cal.add(Calendar.DATE, 1);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

        String formatted = format1.format(cal.getTime());
        return formatted;
    }

    /**
     * Método para sumarle dias a una fecha
     *
     * @param fecha
     * @param dias dias a sumar
     * @return fecha en tipo Date
     */
    public static Date sumaDias(Date fecha, int dias) {
        if (dias == 0) {
            return fecha;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.DAY_OF_YEAR, dias);
        return cal.getTime();
    }

    /**
     * Método para sumarle dias a una fecha
     *
     * @param fecha
     * @param meses meses a sumar
     * @return fecha en tipo Date
     */
    public static Date sumaMeses(Date fecha, int meses) {
        if (meses == 0) {
            return fecha;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.MONTH, meses);
        return cal.getTime();
    }

    /**
     * Método para sumarle dias a una fecha
     *
     * @param fecha
     * @param annos años a sumar
     * @return fecha en tipo Date
     */
    public static Date sumaAnnos(Date fecha, int annos) {
        if (annos == 0) {
            return fecha;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.YEAR, annos);
        return cal.getTime();
    }
}
