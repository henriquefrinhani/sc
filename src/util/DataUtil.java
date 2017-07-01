package util;

import excecoes.NegocioException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DataUtil {

    public static final String FORMATO_DD_MM_YYYY = "dd/MM/yyyy";
    public static final String FORMATO_yyyy_MM_DD = "yyyy-MM-dd";
    private static final DateFormat format_DD_MM_YYYY = new SimpleDateFormat(FORMATO_DD_MM_YYYY);
    private static final DateFormat format_YYYY_MM_DD = new SimpleDateFormat(FORMATO_yyyy_MM_DD);

    private DataUtil() {
    }

    public static String formatoData(Calendar data) {
        if (ObjetoUtil.isNulo(data)) {
            return null;
        }
        return format_YYYY_MM_DD.format(data);
    }

    public static String formatoData(Date data) {
        if (ObjetoUtil.isNulo(data)) {
            return null;
        }
        return format_YYYY_MM_DD.format(data);
    }

    public static String parseDate(String data, String formato) throws NegocioException {

        if (ObjetoUtil.isVazio(formato)) {
            throw new NegocioException("Formato Inválido");
        }

        DateFormat format = getFormat(formato);

        try {
            Date df = format.parse(data);
            return format_YYYY_MM_DD.format(df);
        } catch (Exception e) {
            throw new NegocioException("Formato de data '" + data + "' inválido.");
        }
    }

    private static DateFormat getFormat(String formato) {
        DateFormat format = null;

        if (format_DD_MM_YYYY.equals(formato)) {
            format = format_DD_MM_YYYY;

        } else {
            format = new SimpleDateFormat(formato);
        }

        return format;
    }

    public static String parseDate(String data) throws NegocioException {
        return parseDate(data, FORMATO_DD_MM_YYYY);
    }
    
    public static String parseDateString(String data)throws NegocioException{
        
        DateFormat format = getFormat(FORMATO_yyyy_MM_DD);

        try {
            Date df = format.parse(data);
            return format_DD_MM_YYYY.format(df);
        } catch (Exception e) {
            throw new NegocioException("Formato de data '" + data + "' inválido.");
        }
    }
            

    
}
