package util;

import javafx.stage.Stage;

public final class StageHolder {

    private static Stage stage;

    private StageHolder() {
        throw new UnsupportedOperationException("Class is not instantiatable");
    }

    public static Stage getStage() {
        if (stage == null) {
            throw new NullPointerException("A stage has not been set.");
        }
        return stage;
    }

    public static void setStage(Stage stage) {
        StageHolder.stage = stage;
    }
}
