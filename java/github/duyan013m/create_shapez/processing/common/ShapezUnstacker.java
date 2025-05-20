package github.duyan013m.create_shapez.processing.common;

import github.duyan013m.create_shapez.config.ShapezConfigs;
import github.duyan013m.create_shapez.util.ShapezCodeHelper;

import java.util.Objects;

public class ShapezUnstacker {
    public static String[] unstack(String code) {
        String left=unstack1(code);
        if(ShapezConfigs.getAllowEmpty()){
            return new String[]{unstack1_(code), left.isEmpty()?
                    ShapezCodeHelper.empties(code):left};
        }else{
            return new String[]{unstack1_(code), left};
        }
    }

    public static String unstack0(String code) {
        return code.substring(0, ShapezCodeHelper.index8s5d8l(code));
    }
    public static String unstack1(String code) {
        return code.substring(0, ShapezCodeHelper.lastIndex8s5d(code));
    }

    public static String unstack1_(String code) {
        return code.substring(ShapezCodeHelper.lastIndex8s(code) + 1);
    }
}
