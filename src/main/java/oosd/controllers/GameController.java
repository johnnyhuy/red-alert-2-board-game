package oosd.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameController implements Controller {
    @FXML
    private AnchorPane tilePane;

    public void initialize() {
        final double size = 50;
        final double tHeight = Math.sqrt(3);
        final int boardHeight = 4;
        final int boardWidth = 2;

        double distanceBetween = size * 1.5;
        double height = (boardHeight + 1) * (tHeight * size);
        double width = (boardWidth + 1) * (size + (size / 2));

        double xIncrement = size * (tHeight / 2.0);
        double yIncrement = size * tHeight;
        double xOrigin, yOrigin, yOffset;

        for (yOrigin = 100; yOrigin < height; yOrigin += yIncrement)
        {
            for (xOrigin = 100, yOffset = yOrigin; xOrigin < width; xOrigin += distanceBetween)
            {
                Polygon tile = new Polygon();
                tile.getPoints().addAll(
                        xOrigin, yOffset,
                        xOrigin + size, yOffset,
                        xOrigin + distanceBetween, yOffset + xIncrement,
                        xOrigin + size, yOffset + yIncrement,
                        xOrigin, yOffset + yIncrement,
                        xOrigin - (size / 2.0), yOffset + xIncrement
                );

                Text text = new Text();
                text.setX(xOrigin);
                text.setY(yOrigin);
                text.setFont(new Font(10));
                text.setText(Math.floor(xOrigin) + ", " + Math.floor(yOrigin));
                tile.setFill(Paint.valueOf("#ffffff"));
                tile.setStrokeWidth(2);
                tile.setStroke(Paint.valueOf("#000000") );
                this.tilePane.getChildren().add(tile);
                this.tilePane.getChildren().add(text);

                yOffset = yOffset == yOrigin ? yOffset + xIncrement : yOrigin;
            }
        }
    }
}
