package oosd.models;

import oosd.models.board.Piece;
import oosd.models.player.Player;
import oosd.models.player.Team;
import oosd.models.units.Unit;
import oosd.models.units.allied.GISoldier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {
    @Test
    void testCompareLocationsPiece() {
        // Arrange
        Piece pieceOne = new Piece(1, 1);
        Piece pieceTwo = new Piece(1, 1);
        Piece pieceThree = new Piece(2, 5);

        // Act
        boolean shouldEqual = pieceOne.equals(pieceTwo);
        boolean shouldNotEqual = pieceOne.equals(pieceThree);

        // Assert
        assertTrue(shouldEqual);
        assertFalse(shouldNotEqual);
    }

    @Test
    void testGetPieceLocation() {
        // Arrange
        Piece piece = new Piece(1, 1);

        // Act
        int row = piece.getRow();
        int column = piece.getColumn();

        // Assert
        assertEquals(1, row);
        assertEquals(1, column);
    }

    @Test
    void testGetPiecePlayer() {
        // Arrange
        Piece piece = new Piece(1, 1);
        Player player = new Player("John Tester", new Team("Red"));
        Unit unit = new GISoldier(player);

        // Act
        piece.setUnit(unit);

        // Assert
        assertEquals(unit, piece.getUnit());
    }

    @Test
    void testShouldNotBeNegativeCreatePieceRowsAndColumns() {
        // Act
        Executable run = () -> new Piece(-100, -100);

        // Assert
        assertThrows(AssertionError.class, run);
    }
}
