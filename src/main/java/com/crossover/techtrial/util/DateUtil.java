
package com.crossover.techtrial.util;

import com.crossover.techtrial.exceptions.GlobalExceptionHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utilitario para a formatacao de datas
 */
public final class DateUtil {

    private static String DATE_PATTERN ="yyyy-MM-dd'T'HH:mm:ss";

    /**
     * Impede instanciacao e heranca
     */
    private DateUtil() {
        // default
    }

    /**
     * Convert string to date
     * @param strDate - string to be converted
     * @return {@link Date} converted object
     */
    public static Date strToDate( String strDate ) throws GlobalExceptionHandler {

        if ( strDate != null && !strDate.isEmpty() ) {
            try {
                return new SimpleDateFormat( DATE_PATTERN ).parse( strDate );
            } catch (ParseException e) {
                throw new GlobalExceptionHandler();
            }
        }

        return null;
    }

    /**
     * Convert date to string, view preseatation
     * @param date - date to be coverted
     * @return string with date format
     */
    public static String dateToStr( Date date ) {
        if ( date != null ) {
            return new SimpleDateFormat(DATE_PATTERN ).format( date );
        }
        return null;
    }
}
