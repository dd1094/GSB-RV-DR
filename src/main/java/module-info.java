module fr.gsb.rv.dr.gsb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens fr.gsb.rv.dr.gsb to javafx.fxml;
    exports fr.gsb.rv.dr.gsb;
    exports fr.gsb.rv.dr.technique;

    opens fr.gsb.rv.dr.entites to javafx.graphics, javafx.fxml, javafx.base;
    opens fr.gsb.rv.dr.technique to javafx.base, javafx.fxml, javafx.graphics;

}