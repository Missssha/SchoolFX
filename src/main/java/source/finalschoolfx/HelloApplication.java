package source.finalschoolfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;


public class HelloApplication extends Application {

    @Getter
    private static EntityManager entityManager;
    @Getter
    private static HelloApplication instance;

    @Override
    public void start(Stage stage) throws IOException {
        instance = this;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("FXML/startWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Программный комплекс для школы");
        stage.setScene(scene);
        stage.setMinHeight(600);
        stage.setMinWidth(800);
        stage.show();
    }

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("admin");
        entityManager = entityManagerFactory.createEntityManager();
        System.out.println("Starting of the testing");
        System.out.println("=======================================");
        launch(args);

    }

}