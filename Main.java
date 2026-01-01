package application;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();
        root.getStyleClass().add("root");

        // ===== TOP =====
        Label cityLabel = new Label("Addis Ababa");
        cityLabel.getStyleClass().add("city-title");

        TextField cityField = new TextField();
        cityField.setPromptText("Enter city");

        VBox topBox = new VBox(cityLabel, cityField);
        topBox.setAlignment(Pos.CENTER);
        topBox.setSpacing(5);
        root.setTop(topBox);

        // ===== CENTER =====
        Label tempLabel = new Label("24°C");
        tempLabel.getStyleClass().add("temperature");

        Label conditionLabel = new Label("Partly Cloudy");
        conditionLabel.getStyleClass().add("condition");

        Label airQuality = new Label("Air Quality: Good");
        airQuality.getStyleClass().add("info-text");

        Label gardeningTip = new Label("Gardening Tip: Ideal day for watering plants.");
        gardeningTip.getStyleClass().add("tip-text");

        // Leaf shape
        Ellipse leaf = new Ellipse(30, 20);
        leaf.getStyleClass().add("leaf");

        RotateTransition leafAnimation = new RotateTransition(Duration.seconds(4), leaf);
        leafAnimation.setFromAngle(-5);
        leafAnimation.setToAngle(5);
        leafAnimation.setAutoReverse(true);
        leafAnimation.setCycleCount(RotateTransition.INDEFINITE);
        leafAnimation.play();

        VBox centerBox = new VBox(
                tempLabel,
                conditionLabel,
                leaf,
                airQuality,
                gardeningTip
        );
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setSpacing(10);
        root.setCenter(centerBox);

        // ===== BOTTOM =====
        HBox forecastBox = new HBox(
                createForecastCard("Mon", "22°C"),
                createForecastCard("Tue", "25°C"),
                createForecastCard("Wed", "23°C")
        );
        forecastBox.setAlignment(Pos.CENTER);
        forecastBox.setSpacing(15);
        forecastBox.setPadding(new Insets(10));
        root.setBottom(forecastBox);

        // ===== REFRESH BUTTON =====
        Button refreshBtn = new Button("Refresh");
        refreshBtn.disableProperty().bind(
                Bindings.isEmpty(cityField.textProperty())
        );

        root.setRight(refreshBtn);
        BorderPane.setMargin(refreshBtn, new Insets(10));

        Scene scene = new Scene(root, 500, 400);
        scene.getStylesheets().add(
                getClass().getResource("/style.css").toExternalForm()
        );

        stage.setTitle("EcoLife Weather Widget");
        stage.setScene(scene);
        stage.show();
    }

    private VBox createForecastCard(String day, String temp) {
        Label dayLabel = new Label(day);
        Label tempLabel = new Label(temp);

        VBox box = new VBox(dayLabel, tempLabel);
        box.getStyleClass().add("forecast-card");
        box.setAlignment(Pos.CENTER);
        box.setSpacing(5);

        return box;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
