package github.duyan013m.create_shapez.processing.shape;

import github.duyan013m.create_shapez.config.ShapezConfigs;
import github.duyan013m.create_shapez.processing.common.ShapezAnalyzer;
import github.duyan013m.create_shapez.processing.common.ShapezInciser;

public class ShapezCutter{
    public static String[] cut(String code){
        if(ShapezConfigs.getAllowEmpty()) {
            return ShapezInciser.incise(code);
        }else{
            return ShapezAnalyzer.formatEmpties(ShapezInciser.incise(code));
        }
    }
}
