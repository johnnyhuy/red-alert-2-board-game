//package oosd.models;
//
//import oosd.models.board.Board;
//import oosd.models.board.Piece;
//import oosd.models.mocks.MockUnitEmptyName;
//import oosd.models.mocks.MockUnitNotWinnables;
//import oosd.models.player.Player;
//import oosd.models.player.Team;
//import oosd.models.units.Unit;
//import oosd.models.units.allied.GISoldier;
//import oosd.models.units.allied.GrizzlyTank;
//import oosd.models.units.allied.Harrier;
//import oosd.models.units.soviet.Conscript;
//import oosd.models.units.soviet.KirovAirship;
//import oosd.models.units.soviet.RhinoTank;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.function.Executable;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class UnitTest {
//    @Test
//    void testCreateKirovAirship() {
//        // Act
//        Unit unit = new KirovAirship(new Piece(1, 1));
//
//        // Assert
//        assertNotNull(unit);
//    }
//
//    @Test
//    void testCreateGrizzlyTank() {
//        // Act
//        Unit unit = new GrizzlyTank(new Piece(1, 1));
//
//        // Assert
//        assertNotNull(unit);
//    }
//
//    @Test
//    void testCreateRhinoTank() {
//        // Act
//        Unit unit = new RhinoTank(new Piece(1, 1));
//
//        // Assert
//        assertNotNull(unit);
//    }
//
//    @Test
//    void testCreateHarrier() {
//        // Act
//        Unit unit = new Harrier(new Piece(1, 1));
//
//        // Assert
//        assertNotNull(unit);
//    }
//
//    @Test
//    void testCreateConscript() {
//        // Act
//        Unit unit = new Conscript(new Piece(1, 1));
//
//        // Assert
//        assertNotNull(unit);
//    }
//
//    @Test
//    void testCreateGISoldier() {
//        // Act
//        Unit unit = new GISoldier(new Piece(1, 1));
//
//        // Assert
//        assertNotNull(unit);
//    }
//
//    @Test
//    void testWinnableUnits() {
//        // Arrange
//        Unit unit = new GISoldier(new Piece(1, 1));
//
//        // Act
//        List<Class<? extends Unit>> winnables = unit.getWinnables();
//
//        // Assert
//        assertTrue(winnables.contains(KirovAirship.class));
//        assertTrue(winnables.contains(RhinoTank.class));
//        assertFalse(winnables.contains(GISoldier.class));
//    }
//
//    @Test
//    void testUnitCaptured() {
//        // Arrange
//        Unit unit = new GISoldier(new Piece(1, 1));
//        Unit otherUnit = new GISoldier(new Piece(1, 1));
//
//        // Act
//        unit.setCaptured(true);
//
//        // Assert
//        assertTrue(unit.getCaptured());
//        assertFalse(otherUnit.getCaptured());
//    }
//
//    @Test
//    void testGetWinnablesShouldNotBeZero() {
//        // Arrange
//        Unit unit = new MockUnitNotWinnables(new Piece(1, 1));
//
//        // Act
//        Executable run = unit::getWinnables;
//
//        // Assert
//        assertThrows(AssertionError.class, run);
//    }
//
//    @Test
//    void testGetUnitNameShouldNotBeEmpty() {
//        // Arrange
//        Unit unit = new MockUnitEmptyName(new Piece(1, 1));
//
//        // Act
//        Executable run = unit::getName;
//
//        // Assert
//        assertThrows(AssertionError.class, run);
//    }
//
//    @Test
//    void testGetUnitLocation() {
//        // Arrange
//        Piece piece = new Piece(0, 0);
//        Unit unit = new KirovAirship(piece);
//
//        // Act
//        Piece location = unit.getLocation();
//
//        // Assert
//        assertEquals(piece, location);
//        assertEquals(piece.getColumn(), location.getColumn());
//        assertEquals(piece.getRow(), location.getRow());
//    }
//}
