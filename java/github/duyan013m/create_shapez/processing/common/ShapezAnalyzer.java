package github.duyan013m.create_shapez.processing.common;

import github.duyan013m.create_shapez.util.ShapezCodeHelper;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ShapezAnalyzer {
    public static String[] formatEmpties(String... codes) {
        return Arrays.stream(codes).map(ShapezAnalyzer::formatEmpty).toArray(String[]::new);
    }

    public static String formatEmpty(String code) {
        return Arrays.stream(ShapezCodeHelper.code2ids(code)).sum() == 0 ? "" : code;
    }

    public static String[] analyze(String code) {
        return new String[]{analyze8s(code), analyze8c(code)};
    }

    public static String analyze8s(String code) {
        return ShapezCodeHelper.join8s4s(ShapezCodeHelper.split8s2s(code).map(ShapezAnalyzer::analyze8s1));
    }

    public static String analyze8c(String code) {
        return ShapezCodeHelper.join8s4s(ShapezCodeHelper.split8s2s(code).map(ShapezAnalyzer::analyze8c1));
    }

    public static String faltMap2obj(String code, Function<String, Stream<String>> function) {
        return map2obj2s(code).flatMap(function).collect(Collectors.joining());
    }

    public static String map2obj4s(String code, Function<String, String> function) {
        return map2obj2s(code).map(function).collect(Collectors.joining());
    }

    public static Stream<String> map2obj2s(String code) {
        return IntStream.range(0, code.length() / 2).mapToObj(i -> code.substring(i * 2, i * 2 + 2));
    }

    public static String map2obj4i(String code, IntFunction<String> function) {
        return IntStream.range(0, code.length() / 2).mapToObj(function).collect(Collectors.joining());
    }

    private static String analyze8s1(String code) {
        return map2obj4i(code, i -> code.substring(i * 2, i * 2 + 1));
    }

    private static String analyze8c1(String code) {
        return map2obj4i(code, i -> code.substring(i * 2 + 1, i * 2 + 2));
    }


    //mapMulti vd flatMap and groupBy vs separately
}
