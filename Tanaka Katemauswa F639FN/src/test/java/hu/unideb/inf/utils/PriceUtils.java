package hu.unideb.inf.utils;

import java.util.List;
import java.util.stream.Collectors;

public class PriceUtils {
    public static List<Double> parsePrices(List<String> priceTexts) {
        return priceTexts.stream()
                .map(text -> text.replace("$", ""))
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }

    public static boolean isSortedAscending(List<Double> prices) {
        List<Double> sortedPrices = prices.stream()
                .sorted()
                .collect(Collectors.toList());
        return prices.equals(sortedPrices);
    }

    public static boolean isSortedDescending(List<Double> prices) {
        List<Double> sortedPrices = prices.stream()
                .sorted((a,b) -> b.compareTo(a))
                .collect(Collectors.toList());
        return prices.equals(sortedPrices);
    }
}
