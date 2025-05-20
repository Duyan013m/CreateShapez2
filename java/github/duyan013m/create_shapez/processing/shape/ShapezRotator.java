package github.duyan013m.create_shapez.processing.shape;

import github.duyan013m.create_shapez.util.ShapezCodeHelper;

public class ShapezRotator {
    public static final String CLOCKWISE = "clockwise";
    public static final String CENTRAL_SYMMETRY = "centralSymmetry";
    public static final String COUNTERCLOCKWISE = "counterclockwise";
//    public static String rotate(String... codes) {
//        return switch (codes[1]){
//            case CLOCKWISE -> clockwise(codes[0]);
//            case CENTRAL_SYMMETRY -> centralSymmetry(codes[0]);
//            case COUNTERCLOCKWISE -> counterclockwise(codes[0]);
//            default -> throw new IllegalArgumentException();
//        };
//    }
//    public static String rotate(String code, String d) {
//        return switch (d){
//            case CLOCKWISE -> clockwise(code);
//            case CENTRAL_SYMMETRY -> centralSymmetry(code);
//            case COUNTERCLOCKWISE -> counterclockwise(code);
//            default -> throw new IllegalArgumentException();
//        };
//    }

    public static String clockwise(String code) {
        return ShapezCodeHelper.join8s4s(ShapezCodeHelper.split8s2s(code).map(ShapezRotator::clockwise1));
    }

    private static String clockwise1(String code) {
        return code.isEmpty() ? code : code.substring(code.length() - 2) + code.substring(0, code.length() - 2);
    }

    public static String centralSymmetry(String code) {
        return ShapezCodeHelper.join8s4s(ShapezCodeHelper.split8s2s(code).map(ShapezRotator::centralSymmetry1));
    }

    private static String centralSymmetry1(String code) {
        return code.substring(code.length() / 2) + code.substring(0, code.length() / 2);
    }

    public static String counterclockwise(String code) {
        return ShapezCodeHelper.join8s4s(ShapezCodeHelper.split8s2s(code).map(ShapezRotator::counterClockwise1));
    }

    private static String counterClockwise1(String code) {
        return code.isEmpty() ? code : code.substring(2) + code.substring(0, 2);
    }
}
