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
        // Size of each side
        final double size = 50;

        // Height of the equilateral triangle
        final double tHeight = Math.sqrt(3);

        // Small gap between each shape (the left/right triangle bit)
        double gap = size * 1.5;

        double height = (this.board.getRows() + 1) * (tHeight * size);
        double width = (this.board.getColumns() + 1) * (size + (size / 2));

        // Increments used to place the next polygon
        double halfIncrement = size * (tHeight / 2.0);
        double fullIncrement = size * tHeight;

        int count = 0;

        for (double y = 100; y < height; y += fullIncrement)
        {
            for (double x = 100; x < width; x += gap)
            {
                Polygon tile = new Polygon();
                tile.getPoints().addAll(
                    x, y,
                    x + size, y,
                    x + gap, y + halfIncrement,
                    x + size, y + fullIncrement,
                    x, y + fullIncrement,
                    x - (size / 2.0), y + halfIncrement
                );

                // TODO: DEBUG
                Text text = new Text();
                text.setX(x);
                text.setY(y);
                text.setFont(new Font(10));
                text.setText(Math.floor(x) + ", " + Math.floor(y));
                // DEBUG

                tile.setFill(Paint.valueOf("#ffffff"));
                tile.setStrokeWidth(2);
                tile.setStroke(Paint.valueOf("#000000") );
                this.tilePane.getChildren().add(tile);
                this.tilePane.getChildren().add(text);

                // Every even element set the y value down
                if (count % 2 == 0) {
                    y = y + halfIncrement;
                } else {
                    y = y - halfIncrement;
                }

                count++;
            }
        }
    }
}
