package github.duyan013m.create_shapez.processing.common;

import github.duyan013m.create_shapez.util.ShapezCodeHelper;

import java.util.List;
import java.util.stream.IntStream;

public class ShapezInciser {
    public static String incise2l(String code) {
        return ShapezGravitater.gravitate4s(ShapezCodeHelper.split8s2s(shatter(code)).map(ShapezInciser::incise2l1));
    }

    public static String[] incise(String code) {
        List<String[]> array = ShapezCodeHelper.split8s2s(shatter(code)).map(ShapezInciser::incise1).toList();
        return new String[]{ShapezGravitater.gravitate4s(array.stream().map(strings -> strings[0])),
                ShapezGravitater.gravitate4s(array.stream().map(strings -> strings[1]))};
    }

    private static String incise2l1(String code) {
        return split1l(code) + ShapezCodeHelper.empties(code.length() / 2);
    }

    private static String[] incise1(String code) {
//        String repeat = ShapezCodeHelper.EMPTY1.repeat(ShapezConfigs.getQuadrants());
        String repeat = ShapezCodeHelper.empties(code.length() / 2);
        return new String[]{split1l(code) + repeat, repeat + split1r(code)};
    }

    public static String shatter(String code) {
        return String.join(":", ShapezCodeHelper.split8s2s(code).map(
                ShapezInciser::shatter1).toList());
    }

    private static String shatter1(String code) {
        if(!ShapezAnalyzer.analyze8s(code).contains(ShapezCodeHelper.CRYSTAL)){
            return code;
        }
//        int quadrants = ShapezConfigs.getQuadrants();
        int quadrants = code.length() / 2;
        return ShapezSeparator.separate8c1(code).filter(s ->
                        ShapezCodeHelper.CRYSTAL.indexOf(s.charAt(0)) == 0
                                && ShapezCodeHelper.CRYSTAL.indexOf(s.charAt(s.length() - 2)) == 0
                                || ShapezCodeHelper.CRYSTAL.indexOf(s.charAt(quadrants - 2)) == 0
                                && ShapezCodeHelper.CRYSTAL.indexOf(s.charAt(quadrants)) == 0)
                .reduce(code, ShapezInciser::overlap1);
    }


    private static String overlap1(String code, String crystal) {
        StringBuilder code1 = new StringBuilder(code);
        for (int i = 0; i < crystal.length(); i += 2) {
            String sc = crystal.substring(i, i + 2);
            if (sc.charAt(0) == ShapezCodeHelper.CRYSTAL.charAt(0)) {
                code1.replace(i, i + 2, ShapezCodeHelper.EMPTY);
            }
        }
        return code1.toString();
    }

    public static String combine(String codel, String coder) {
        String[] codels = ShapezCodeHelper.split8s2a(codel);
        String[] coders = ShapezCodeHelper.split8s2a(coder);
        int max = Math.max(codels.length, coders.length);
        return ShapezCodeHelper.join8s4s(IntStream.range(0, max).mapToObj(i ->
                combine1(i < codels.length ? codels[i] : ShapezCodeHelper.empties(codels),
                        i < coders.length ? coders[i] : ShapezCodeHelper.empties(coders))));
    }

    private static String combine1(String codel, String coder) {
        return split1l(codel) + split1r(coder);
    }

    private static String split1r(String code) {
        return code.substring(code.length() / 2);
    }

    private static String split1l(String code) {
        return code.substring(0, code.length() / 2);
    }
}
