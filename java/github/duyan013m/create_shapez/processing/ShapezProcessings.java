package github.duyan013m.create_shapez.processing;

import github.duyan013m.create_shapez.processing.shape.*;

import java.util.function.Function;

public enum ShapezProcessings {
    colorMixer("colorMix"),
    crystalGenerator("crystalGenerate"),
    painter("paint"),
    cutter("cut"),
    halfDestroyer("halfDestroy"),
    pinPusher("pinPush"),
    rotator("rotate"),
    stacker("stack"),
    swapper("swap"),
    unstacker("unstack");
    private final String cmd;

    ShapezProcessings(String cmd) {
        this.cmd = cmd;
    }

    public String getCmd() {
        return this.cmd;
    }
}
