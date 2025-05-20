package github.duyan013m.create_shapez.processing.shape;

import github.duyan013m.create_shapez.config.ShapezConfigs;
import github.duyan013m.create_shapez.processing.common.ShapezAnalyzer;
import github.duyan013m.create_shapez.processing.common.ShapezInciser;

public class ShapezHalfDestroyer{
    public static String halfDestroy(String code){
        if(ShapezConfigs.getAllowEmpty()) {
            return ShapezInciser.incise2l(code);
        }else{
            return ShapezAnalyzer.formatEmpty(ShapezInciser.incise2l(code));
        }
    }

}
