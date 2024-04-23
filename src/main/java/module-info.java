module source.finalschoolfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires hibernate.entitymanager;
    requires java.sql;
    requires mysql.connector.j;
    requires jakarta.xml.bind;
    requires org.controlsfx.controls;
    requires static lombok;
    requires jakarta.persistence;
    requires org.glassfish.jaxb.runtime;
    requires jasperreports;
    requires jasperreports.fonts;

    opens  source.finalschoolfx.controllers.base to javafx.fxml, javafx.base;
    exports source.finalschoolfx;
    opens source.finalschoolfx to
            javafx.fxml,
            jakarta.xml.bind,
            java.sql,
            jasperreports,
            jasperreports.fonts;


    exports source.finalschoolfx.transformed;
    opens source.finalschoolfx.transformed to
            javafx.base,
            jakarta.xml.bind;

    exports source.finalschoolfx.controllers;
    opens source.finalschoolfx.controllers to javafx.fxml;
    exports source.finalschoolfx.models;
    opens source.finalschoolfx.models;

    opens source.finalschoolfx.controllers.student to javafx.fxml, jasperreports;
    exports source.finalschoolfx.controllers.student;
    exports source.finalschoolfx.controllers.teacher;
    opens source.finalschoolfx.controllers.teacher to javafx.fxml;
    exports source.finalschoolfx.controllers.discipline;
    opens source.finalschoolfx.controllers.discipline to javafx.fxml;
    exports source.finalschoolfx.controllers.studclass;
    opens source.finalschoolfx.controllers.studclass to javafx.fxml;
}