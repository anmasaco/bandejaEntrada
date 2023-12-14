package com.bandeja.entrada.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Formato {

    public static final String PESOS              = "$ ###,###,###,###,###,###.00";
    public static final String PESOS_ENTERO       = "$ ###,###,###,###,###,###";
    public static final String US$                = "$ ###,###,###,###,###,###.00";
    public static final String US$_ENTERO         = "$ ###,###,###,###,###,###";
    public static final String NUMERO             = "##################.00";
    public static final String NUMERO_ENTERO      = "##################";

    public static void main(String[] args) {
        System.out.println("EJEMPLO:\n");
        //System.out.println("Dolares            " + Formato.formatear(Formato.PESOS_COLOMBIANOS, 125453));
        System.out.println("Pesos              " + Formato.formatear(Formato.PESOS_ENTERO, 125453));
        //System.out.println("Pesos colombianos  " + Formato.formatear(Formato.PESOS_COLOMBIANOS, 125453));
        System.out.println("NUMERO             " + Formato.formatear(Formato.NUMERO, 125453));
    }

    public static String formatear(String formato, int valor) {
        DecimalFormat forma = new DecimalFormat(formato);
        return forma.format(valor);
    }

    public static String formatear(String formato, float valor) {
        DecimalFormat forma = new DecimalFormat(formato);
        return forma.format(valor);
    }

    public static String formatear(String formato, double valor) {
        DecimalFormat forma = new DecimalFormat(formato);
        return forma.format(valor);
    }

    public static String formatear(String formato, Double valor) {
        if (valor != null) {
            DecimalFormat forma = new DecimalFormat(formato);
            return forma.format(valor);
        } else {
            return "";
        }
    }

    public static String formatear(String formato, long valor) {
        DecimalFormat forma = new DecimalFormat(formato);
        return forma.format(valor);
    }

    public static String formatear(String formato, BigDecimal valor) {
        if (valor != null) {
            DecimalFormat forma = new DecimalFormat(formato);
            return forma.format(valor);
        } else {
            return "";
        }
    }

    public static String formatoHtml(String valor) {
        if(valor!=null && !valor.isEmpty()){
            char c13 = (char)13;
            char c10 = (char)10;
            valor = valor.replaceAll(""+c13+c10, "<br/>").replaceAll(""+c10+c13, "<br/>").replaceAll(""+c13, "<br/>").replaceAll(""+c10, "<br/>");
        }
        return valor;
    }
}
