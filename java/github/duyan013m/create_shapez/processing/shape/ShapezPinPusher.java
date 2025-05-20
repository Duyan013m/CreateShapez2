package github.duyan013m.create_shapez.processing.shape;

import github.duyan013m.create_shapez.processing.common.ShapezAnalyzer;
import github.duyan013m.create_shapez.processing.common.ShapezGravitater;
import github.duyan013m.create_shapez.processing.common.ShapezUnstacker;
import github.duyan013m.create_shapez.util.ShapezCodeHelper;

import java.util.stream.Collectors;

public class ShapezPinPusher {
    public static String pinPush(String code) {
        return ShapezGravitater.discard(ShapezCodeHelper.join8s4a(ShapezAnalyzer.map2obj4s(ShapezUnstacker.unstack0(code), sc ->
                ShapezCodeHelper.EMPTY.equals(sc) ? ShapezCodeHelper.EMPTY : ShapezCodeHelper.PIN), code));
    }
}
