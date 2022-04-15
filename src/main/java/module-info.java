module com.roadjava.student1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires beautyeye.lnf;

    opens com.java.roadstudent.roadjava.student1 to javafx.fxml;
    exports com.java.roadstudent.roadjava.student1;
}