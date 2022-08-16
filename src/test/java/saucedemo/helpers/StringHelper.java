package saucedemo.helpers;

import java.math.BigDecimal;

final public class StringHelper {

    public static BigDecimal stringSemSinalDeDinheiroParaDecimal(String text) {
        text = text.replaceAll("\\$", "");
        text = text.replaceAll("\\D+\\.\\D+", "");
        text = text.replaceAll("[^\\d.]", "");
        return new BigDecimal(text);
    }
}
