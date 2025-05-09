module com.aetxabao.invasoresfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires log4j;

    opens edu.masanz.da.pk2 to javafx.fxml;
    exports edu.masanz.da.pk2;
    exports edu.masanz.da.pk2.sprite;
    opens edu.masanz.da.pk2.sprite to javafx.fxml;
    exports edu.masanz.da.pk2.util;
    opens edu.masanz.da.pk2.util to javafx.fxml;
    exports edu.masanz.da.pk2.game;
    opens edu.masanz.da.pk2.game to javafx.fxml;
    exports edu.masanz.da.pk2.sprite.weaponry;
    opens edu.masanz.da.pk2.sprite.weaponry to javafx.fxml;
}