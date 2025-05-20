package github.duyan013m.create_shapez.processing.common;

import github.duyan013m.create_shapez.config.ShapezConfigs;
import github.duyan013m.create_shapez.util.ShapezCodeHelper;

import java.util.stream.Stream;

public class ShapezGravitater {
    public static String gravitate4s(Stream<String> codes) {
        return discard(codes.flatMap(ShapezSeparator::separate8s).reduce(ShapezGravitater::fall1).orElseThrow());
    }

    public static String gravitate(String code) {
        return discard(ShapezSeparator.separate8s(code).reduce(ShapezGravitater::fall1).orElseThrow());
    }

    public static String fall(String code, String falling) {
        return discard(ShapezSeparator.separate8s(falling).reduce(code, ShapezGravitater::fall1));
    }

    public static String discard(String code) {
        return ShapezCodeHelper.join8s4s(ShapezCodeHelper.split8s2s(code).limit(ShapezConfigs.getMaxLayer()));
    }

    private static String fall1(String code, String falling) {
        if (falling.isEmpty() || ShapezAnalyzer.analyze8s(falling).contains(ShapezCodeHelper.CRYSTAL)) {
            return code;
        } else if (code.isEmpty()) {
            return falling;
        }
        String[] codes = ShapezCodeHelper.split8s2a(code);
        int i = codes.length;
        for (; i > 0; i--) {
            if (isOverlap1(codes[i - 1], falling)) {
                break;
            }
        }
        if (i == codes.length) {
            return String.format("%s:%s", code, falling);
        } else {
            codes[i] = overlap1(codes[i], falling);
            return ShapezCodeHelper.join8s4a(codes);
        }
    }

    private static String overlap1(String code, String falling) {
        StringBuilder code1 = new StringBuilder(code);
        for (int i = 0; i < falling.length(); i += 2) {
            String sc = falling.substring(i, i + 2);
            if (!ShapezCodeHelper.EMPTY.equals(sc)) {
                code1.replace(i, i + 2, sc);
            }
        }
        return code1.toString();
    }

    private static boolean isOverlap1(String code, String falling) {
        for (int i = 0; i < falling.length(); i += 2) {
            if (!ShapezCodeHelper.EMPTY.equals(code.substring(i, i + 2))
                    && !ShapezCodeHelper.EMPTY.equals(falling.substring(i, i + 2))) {
                return true;
            }
        }
        return false;
    }
}
