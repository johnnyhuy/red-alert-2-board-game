package oosd.views;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import oosd.models.board.Board;

public class BoardView extends View {
    private Board board;
    private AnchorPane tilePane;

    public BoardView(Board board, AnchorPane tilePane) {
        this.board = board;
        this.tilePane = tilePane;
    }

    @Override
    public void render() {
        final double size = 50;
        final double tHeight = Math.sqrt(3);

        double distanceBetween = size * 1.5;
        double height = (this.board.getRows() + 1) * (tHeight * size);
        double width = (this.board.getColumns() + 1) * (size + (size / 2));

        double xIncrement = size * (tHeight / 2.0);
        double yIncrement = size * tHeight;
        double xOrigin, yOrigin;
        int count = 0;

        for (yOrigin = 100; yOrigin < height; yOrigin += yIncrement)
        {
            for (xOrigin = 100; xOrigin < width; xOrigin += distanceBetween)
            {
                Polygon tile = new Polygon();
                tile.getPoints().addAll(
                    xOrigin, yOrigin,
                    xOrigin + size, yOrigin,
                    xOrigin + distanceBetween, yOrigin + xIncrement,
                    xOrigin + size, yOrigin + yIncrement,
                    xOrigin, yOrigin + yIncrement,
                    xOrigin - (size / 2.0), yOrigin + xIncrement
                );

                // TODO: DEBUG
                Text text = new Text();
                text.setX(xOrigin);
                text.setY(yOrigin);
                text.setFont(new Font(10));
                text.setText(Math.floor(xOrigin) + ", " + Math.floor(yOrigin));
                // DEBUG

                tile.setFill(Paint.valueOf("#ffffff"));
                tile.setStrokeWidth(2);
                tile.setStroke(Paint.valueOf("#000000") );
                this.tilePane.getChildren().add(tile);
                this.tilePane.getChildren().add(text);

                // Every even element set the y value down
                if (count % 2 == 0) {
                    yOrigin += xIncrement;
                } else {
                    yOrigin -= xIncrement;
                }

                count++;
            }
        }
    }
}
