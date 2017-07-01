package util;

import java.util.Collection;
import java.util.Map;

public class ObjetoUtil {

    private ObjetoUtil() {
    }

    public static boolean isNulo(Object objeto) {
        return objeto == null;
    }

    public static boolean isVazio(Object objeto) {
        if (isNulo(objeto)) {
            return true;
        }

        if (objeto instanceof Number) {
            return ((Number) objeto).intValue() == 0;
        }

        if (objeto instanceof String) {
            return ((String) objeto).trim().length() == 0;
        }

        if (objeto instanceof Collection<?>) {
            return ((Collection<?>) objeto).isEmpty();
        }

        if (objeto instanceof Map<?, ?>) {
            return ((Map<?, ?>) objeto).isEmpty();
        }

        if (objeto instanceof Object[]) {
            return ((Object[]) objeto).length == 0;
        }

        if (objeto instanceof CharSequence) {
            return ((CharSequence) objeto).length() == 0;
        }
        return objeto.toString().length() == 0;
    }
}
