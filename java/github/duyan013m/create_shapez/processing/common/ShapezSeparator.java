package github.duyan013m.create_shapez.processing.common;


import github.duyan013m.create_shapez.util.ShapezCodeHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShapezSeparator {

    public static Stream<String> separate8c1(String code) {
        return separate1(code, sc -> sc.indexOf(ShapezCodeHelper.CRYSTAL) != 0);
    }

    public static Stream<String> separate8s(String code) {
        return ShapezCodeHelper.split8s2s(code).flatMap(ShapezSeparator::separate4s1);
    }

    private static Stream<String> separate4s1(String code) {
        List<String> list = separate1(code, ShapezCodeHelper.NON_STICKY::contains).collect(Collectors.toList());
        String codep = ShapezAnalyzer.map2obj4s(code, sc -> ShapezCodeHelper.PIN.equals(sc) ? sc : ShapezCodeHelper.EMPTY);
        String empties = ShapezCodeHelper.empties(code);
        if (codep.contains(ShapezCodeHelper.PIN)) {
            for (int i = 0; i < codep.length(); i += 2) {
                if (ShapezCodeHelper.PIN.equals(codep.substring(i, i + 2))) {
                    list.add(new StringBuilder(empties).replace(i, i + 2, ShapezCodeHelper.PIN).toString());
                }
            }

        }
        return list.stream();
    }

//    public static Stream<String> separate(String code, Function<String, Boolean> test) {
//        return ShapezCodeHelper.split8s2s(code).flatMap(cs -> separate1(cs, test));
//    }

    private static Stream<String> separate1(String code, Function<String, Boolean> function) {
        if (code.isEmpty() || code.chars().map(ShapezCodeHelper.EMPTY1::indexOf).sum() % code.length() == 0) {
            return Stream.of(code);
        }
        List<String> scs = new ArrayList<>();
        StringBuilder buff = new StringBuilder();
        boolean notnull = false;
        String sc = code.substring(code.length() - 2);
        int first = -1;
        for (int i = 0; i < code.length(); i += 2) {
            if (!notnull) {
                notnull = function.apply(sc);
            }
            sc = code.substring(i, i + 2);
            if (!notnull) {
                continue;
            }
            if (first == -1) {
                first = i - 2;
            }
            if (!function.apply(sc)) {
                buff.append(sc);
                continue;
            }
            notnull = false;
            if (!buff.isEmpty()) {
                scs.add(String.join("", ShapezCodeHelper.empties(i - buff.length()), buff,
                        ShapezCodeHelper.empties(code.length() - i)));
                buff.setLength(0);
            }
        }
        if (first >= 0) {
            scs.add(String.join("", code.substring(0, first),
                    ShapezCodeHelper.empties(code.length() - first - buff.length()), buff));
        }
        return scs.stream();
    }
}
