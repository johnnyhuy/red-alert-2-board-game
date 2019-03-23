package oosd.controllers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("board.fxml"));
//        primaryStage.setTitle("OOSD Board");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();

        AnchorPane tileMap = new AnchorPane();
        Scene content = new Scene(tileMap, 700, 700);
        primaryStage.setScene(content);

        final double scale = 100;
        double height = 6 * scale;
        double width = 3 * scale;

        final double tHeight = Math.sqrt(3);
        final double size = scale / 2;

        double xScale = size * (tHeight / 2.0), yScale = size * tHeight;
        double xOrigin, yOrigin, yOffset;
        double distanceBetween = size * (3.0 / 2.0);

        for (yOrigin = scale; yOrigin < height; yOrigin += yScale)
        {
            for (xOrigin = scale, yOffset = yOrigin; xOrigin < width; xOrigin += distanceBetween)
            {
                Polygon tile = new Polygon();
                tile.getPoints().addAll(
                    xOrigin, yOffset,
                    xOrigin + size, yOffset,
                    xOrigin + distanceBetween, yOffset + xScale,
                    xOrigin + size, yOffset + yScale,
                    xOrigin, yOffset + yScale,
                    xOrigin - (size / 2.0), yOffset + xScale
                );

                Text text = new Text();
                text.setFont(new Font(20));
                text.setText("test");

                tile.setFill(Paint.valueOf("#ffffff"));
                tile.setStrokeWidth(2);
                tile.setStroke(Paint.valueOf("#000000") );
                tileMap.getChildren().add(tile);
                tileMap.getChildren().add(text);

                yOffset = yOffset == yOrigin ? yOffset + xScale : yOrigin;
            }
        }
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
