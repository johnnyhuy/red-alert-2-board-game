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
        primaryStage.setTitle("OOSD Board");

        AnchorPane tileMap = new AnchorPane();
        Scene content = new Scene(tileMap, 800, 800);
        primaryStage.setScene(content);
        primaryStage.setAlwaysOnTop(true);

        final double size = 50;
        final double tHeight = Math.sqrt(3);
        final int boardHeight = 4;
        final int boardWidth = 7;

        double distanceBetween = size * 1.5;
        double height = (boardHeight + 1) * (tHeight * size);
        double width = (boardWidth + 1) * (size + (size / 2));

        double xScale = size * (tHeight / 2.0), yScale = size * tHeight;
        double xOrigin, yOrigin, yOffset;

        for (yOrigin = 100; yOrigin < height; yOrigin += yScale)
        {
            for (xOrigin = 100, yOffset = yOrigin; xOrigin < width; xOrigin += distanceBetween)
            {
                Polygon tile = new Polygon();
                tile.getPoints().addAll(
                    xOrigin, yOffset,
                    xOrigin + size, yOffset,
                    xOrigin + distanceBetween, yOffset + xScale
//                    xOrigin + size, yOffset + yScale,
//                    xOrigin, yOffset + yScale,
//                    xOrigin - (size / 2.0), yOffset + xScale
                );

                Text text = new Text();
                text.setX(xOrigin);
                text.setY(yOrigin);
                text.setFont(new Font(10));
                text.setText(Math.floor(xOrigin) + ", " + Math.floor(yOrigin));
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
