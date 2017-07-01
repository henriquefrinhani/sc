package util;

public final class CpfUtil {

    private CpfUtil() {
    }

    public static boolean validar(String cpf) {
        if (cpf == null) {
            return false;
        }

        if (cpf.length() != 11) {
            return false;
        }

        if (caracterIguais(cpf)) {
            return false;
        }

        if (caracterInvalido(cpf)) {
            return false;
        }

        int soma1 = 0;
        int soma2 = 0;

        for (int i = 0; i < 9; i++) {
            int digito = Integer.parseInt(""+ cpf.charAt(i));

            soma1 += (10 - i) * digito;
            soma2 += (11 - i) * digito;
        }

        int dig1 = 11 - soma1 % 11;

        if (dig1 > 9) {
            dig1 = 0;
        }

        int dig2 = 11 - (soma2 + dig1 * 2) % 11;

        if (dig2 > 9) {
            dig2 = 0;
        }

        String digitos = cpf.substring(9, 11);
        return digitos.equals("" + dig1 + dig2);
    }

    private static boolean caracterIguais(String s) {
        char primeiro = s.charAt(0);

        for (int i = 1; i < s.length(); i++) {
            if (primeiro != s.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private static boolean caracterInvalido(String s) {
        for (char c : s.toCharArray()) {
            if (!(c >= '0' && c <= '9')) {
                return true;
            }
        }
        return false;
    }
}
