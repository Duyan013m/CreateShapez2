package github.duyan013m.create_shapez.util;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;
import github.duyan013m.create_shapez.CreateShapez;
import github.duyan013m.create_shapez.config.ShapezConfigs;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ShapezCodeHelper {
    public static final String EMPTY1 = "-";
    public static final String EMPTY = empties(2);
    public static final String PIN = "P-";
    public static final String PINS = "pin";
    public static final String COLOR_CODE = "color-";
    public static final String COLOR = "urgbcmyw";
    public static final String SHAPE = "CRSW";
    public static final String CRYSTAL = "c";
    public static final String CRYSTAL_SHAPE = CRYSTAL + SHAPE;
    public static final String SEPARATOR = ":";
    public static final String VALID = CRYSTAL_SHAPE + COLOR + PIN + SEPARATOR;
    public static final Set<Integer> VALIDS = VALID.chars().boxed().collect(Collectors.toSet());
    public static final List<String> NON_STICKY = List.of(ShapezCodeHelper.EMPTY, ShapezCodeHelper.PIN);
    public static final List<String> COLORS = List.of("gray", "red", "green", "blue", "cyan", "magenta", "yellow", "white");
    public static final List<String> CRYSTAL_SHAPES = List.of("crystal", "circle", "rectangle", "star", "windmill");
    public static final ImmutableBiMap<Long, String> LS;
    public static final ImmutableBiMap<String, Long> SL;

    static {
        BiMap<Long, String> sc = HashBiMap.create();
        IntStream.range(0, NON_STICKY.size()).forEach(i -> sc.put((long) i, NON_STICKY.get(i)));
        ShapezCodeHelper.CRYSTAL_SHAPE.chars().forEach(s -> ShapezCodeHelper.COLOR.chars().forEach(c -> {
            if (CRYSTAL.charAt(0) == s && COLOR.charAt(0) == c) {
                return;
            }
            sc.put((long) sc.size(), String.valueOf((char) s) + (char) c);
        }));
        LS = ImmutableBiMap.copyOf(sc);
        SL = LS.inverse();
    }

    public static String empties(String... codes){
        return EMPTY1.repeat(codes[0].length());
    }

    public static String empties(int l){
        return EMPTY1.repeat(l);
    }

    public static String[] split8s2a(String code) {
        return code.split(SEPARATOR);
    }

    public static Stream<String> split8s2s(String code) {
        return Arrays.stream(code.split(SEPARATOR));
    }

    public static String replace8s(String code) {
        return code.replace(SEPARATOR, "");
    }

    public static String join8s4a(String... code) {
        return String.join(SEPARATOR, code);
    }

    public static String join8s4s(Stream<String> code) {
        return String.join(SEPARATOR, code.toList());
    }

    public static int lastIndex8s(String code) {
        return code.lastIndexOf(SEPARATOR);
    }
    public static int lastIndex8s5d(String code) {
        return code.contains(SEPARATOR)?code.lastIndexOf(SEPARATOR):0;
    }

//    public static int lastIndex8s5d8l(String code) {
//        return code.contains(SEPARATOR) ? code.lastIndexOf(SEPARATOR) : code.length();
//    }

    public static int index8s(String code) {
        return code.indexOf(SEPARATOR);
    }

//    public static int index8s5d(String code,int d) {
//        return code.contains(SEPARATOR)?code.indexOf(SEPARATOR):d;
//    }

    public static int index8s5d8l(String code) {
        return code.contains(SEPARATOR) ? code.indexOf(SEPARATOR) : code.length();
    }

    public static boolean isColor(String code) {
        return code.startsWith(COLOR_CODE);
    }

    public static boolean isValid(String code) {
        if (isColor(code)) {
            return isValid(code.substring(COLOR_CODE.length()), true);
        } else {
            return isValid(code, false);
        }
    }

    private static boolean isValid(String code, boolean isColor) {
        if (isColor) {
            return COLOR.contains(code);
        } else {
            return valid(code);
        }
    }


    public static long getMaxCount() {
//        return Collections.nCopies(ShapezConfigs.getQuadrants(), (long) LS.size()).stream().reduce(1L, (left, right) -> left * right);
        return Collections.nCopies(4, (long) LS.size()).stream().reduce(1L, (left, right) -> left * right);
    }

    public static int getMaxLength() {
//        return ShapezConfigs.getQuadrants() * 2;
        return 8;
    }

    public static String s2name(String sc) {
        return CRYSTAL_SHAPES.get(ShapezCodeHelper.CRYSTAL_SHAPE.indexOf(sc.charAt(0)));
    }

    public static String c2name(String sc) {
        return COLORS.get(ShapezCodeHelper.COLOR.indexOf(sc.charAt(1)));
    }

    public static String sc2name(String sc) {
        if (sc.length() != 2) {
            throw new IndexOutOfBoundsException();
        } else if (sc.equals(ShapezCodeHelper.EMPTY)) {
            return String.format("%s:item/transparent", CreateShapez.ID);
        } else if (sc.equals(ShapezCodeHelper.PIN)) {
            return String.format("%s:item/%s", CreateShapez.ID, PINS);
        } else if (ShapezCodeHelper.CRYSTAL.charAt(0) == sc.charAt(0)) {
            return String.format("%s:item/%s_%s", CreateShapez.ID, c2name(sc), s2name(sc));
        } else {
            return String.format("%s:block/%s_%s", CreateShapez.ID, c2name(sc), s2name(sc));
        }
    }

    private static long code2id1(String code) {
        long s = SL.size();
        long id = 0;
        for (int i = code.length() - 2; i >= 0; i -= 2) {
            id *= s;
            String s1 = code.substring(i, i + 2);
            if (!SL.containsKey(s1)) {
                return -1;
            } else {
                id += Objects.requireNonNull(SL.get(code.substring(i, i + 2)));
            }
        }
        return id;
    }

    public static long[] code2ids(String code) {
        String[] codes = ShapezCodeHelper.split8s2a(code);
        if (codes.length > ShapezConfigs.getMaxLayer()) {
            long[] ids = new long[codes.length];
            Arrays.fill(ids, ShapezConfigs.getMaxLayer(), ids.length, -1);
            return ids;
        }
        int[] array = Arrays.stream(codes).mapToInt(String::length).toArray();
        if (Arrays.stream(array).anyMatch(l -> l != getMaxLength())) {
            return Arrays.stream(array).mapToLong(l -> l != getMaxLength() ? -1 : 0).toArray();
        } else {
            return Arrays.stream(codes).mapToLong(ShapezCodeHelper::code2id1).toArray();
        }
    }

    private static String id2code1(long id) {
        long s = LS.size();
        StringBuilder code = new StringBuilder();
        while (id > 0) {
            code.append(LS.get(id % s));
            id /= s;
        }
        return code.append("-".repeat(getMaxLength() - code.length())).toString();
    }

    public static String ids2code(long... ids) {
        if (ids.length > ShapezConfigs.getMaxLayer()
                || Arrays.stream(ids).anyMatch(id -> id < 0 || id > getMaxCount())) {
            return "";
        } else {
            return ShapezCodeHelper.join8s4s(Arrays.stream(ids).mapToObj(ShapezCodeHelper::id2code1));
        }
    }

    public static boolean valid(String code) {
        if (code.isEmpty() || code.chars().anyMatch(c -> !ShapezCodeHelper.VALIDS.contains(c))) {
            return false;
        }
        long[] ids = code2ids(code);
        if (Arrays.stream(ids).sum() == 0) {
            return false;
        } else {
            return code.equals(ids2code(ids));
        }
    }
}
