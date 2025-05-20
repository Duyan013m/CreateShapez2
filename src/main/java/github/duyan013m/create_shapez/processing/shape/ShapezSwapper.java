package github.duyan013m.create_shapez.processing.shape;

import github.duyan013m.create_shapez.config.ShapezConfigs;
import github.duyan013m.create_shapez.processing.common.ShapezAnalyzer;
import github.duyan013m.create_shapez.processing.common.ShapezInciser;

public class ShapezSwapper {
    public static String[] swap(String codel, String coder) {
        String[] codels = ShapezInciser.incise(codel);
        String[] coders = ShapezInciser.incise(coder);
        String[] codes = {ShapezInciser.combine(codels[0], coders[1]), ShapezInciser.combine(coders[0], codels[1])};
        if (ShapezConfigs.getAllowEmpty()) {
            return codes;
        } else {
            return ShapezAnalyzer.formatEmpties(codes);
        }
    }
}
