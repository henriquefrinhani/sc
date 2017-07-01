package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class IdadeUtil {

    public static final String FORMATO_DD_MM_YYYY = "dd/MM/yyyy";
    private static final DateFormat format_DD_MM_YYYY = new SimpleDateFormat(FORMATO_DD_MM_YYYY);

    public static String getIdade() {
        Date dataAtual = new Date();

        dataAtual.getTime();
        return format_DD_MM_YYYY.format(dataAtual);
    }

}
