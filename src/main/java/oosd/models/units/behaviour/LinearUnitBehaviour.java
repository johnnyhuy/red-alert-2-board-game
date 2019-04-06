package oosd.models.units.behaviour;

import oosd.models.GameEngine;
import oosd.models.board.Board;
import oosd.models.board.Hexagon;

import java.util.ArrayList;
import java.util.List;

import static oosd.helpers.NumberHelper.isEven;
import static oosd.helpers.NumberHelper.isOdd;

public class LinearUnitBehaviour extends UnitBehaviour {
    private final int moves;
    private List<Hexagon> validMoves;
    private boolean trackNorth = true;
    private boolean trackSouth = true;
    private boolean trackSouthEast = true;
    private boolean trackSouthWest = true;
    private boolean trackNorthEast = true;
    private boolean trackNorthWest = true;
    private int northEastOffset = 0;
    private int northWestOffset = 0;
    private int southEastOffset = 0;
    private int southWestOffset = 0;

    public LinearUnitBehaviour(int moves) {
        this.moves = moves;
        this.validMoves = new ArrayList<>();
    }

    @Override
    public List<Hexagon> getValidMoves(GameEngine gameEngine, Hexagon hexagon) {
        Board board = gameEngine.getBoard();

        for (int move = 1; move <= moves; move++) {
            addNorthValidMoves(hexagon, board, move);
            addNorthWestValidMoves(hexagon, board, move);
            addNorthEastValidMoves(hexagon, board, move);
            addSouthValidMoves(hexagon, board, move);
            addSouthWestValidMoves(hexagon, board, move);
            addSouthEastValidMoves(hexagon, board, move);
        }

        return validMoves;
    }

    private void addNorthValidMoves(Hexagon hexagon, Board board, int move) {
        int north = hexagon.getRow() - move;
        if (north >= board.getRows() || north < 0) {
            return;
        }

        if (board.getHexagon(hexagon.getColumn(), north).getUnit() != null) {
            trackNorth = false;
        }

        if (!trackNorth) {
            return;
        }

        validMoves.add(new Hexagon(hexagon.getColumn(), north));
    }

    private void addNorthWestValidMoves(Hexagon hexagon, Board board, int move) {
        int west = hexagon.getColumn() - move;
        if (west >= board.getColumns() || west < 0) {
            return;
        }

        if (isOdd(west)) {
            northWestOffset++;
        }

        int northWest = hexagon.getRow() - northWestOffset;
        if (northWest >= board.getRows() || northWest < 0) {
            return;
        }

        if (board.getHexagon(west, northWest).getUnit() != null) {
            trackNorthWest = false;
        }

        if (!trackNorthWest) {
            return;
        }

        validMoves.add(new Hexagon(west, northWest));
    }

    private void addNorthEastValidMoves(Hexagon hexagon, Board board, int move) {
        int east = hexagon.getColumn() + move;
        if (east >= board.getColumns()) {
            return;
        }

        if (isOdd(east)) {
            northEastOffset++;
        }

        int northEast = hexagon.getRow() - northEastOffset;
        if (northEast >= board.getRows() || northEast < 0) {
            return;
        }

        if (board.getHexagon(east, northEast).getUnit() != null) {
            trackNorthEast = false;
        }

        if (!trackNorthEast) {
            return;
        }

        validMoves.add(new Hexagon(east, northEast));
    }

    private void addSouthValidMoves(Hexagon hexagon, Board board, int move) {
        int south = hexagon.getRow() + move;
        if (south >= board.getRows()) {
            return;
        }

        if (board.getHexagon(hexagon.getColumn(), south).getUnit() != null) {
            trackSouth = false;
        }

        if (!trackSouth) {
            return;
        }

        validMoves.add(new Hexagon(hexagon.getColumn(), south));
    }

    private void addSouthWestValidMoves(Hexagon hexagon, Board board, int move) {
        int west = hexagon.getColumn() - move;
        if (west >= board.getColumns() || west < 0) {
            return;
        }

        if (isEven(west)) {
            southWestOffset++;
        }

        int southWest = hexagon.getRow() + southWestOffset;
        if (southWest >= board.getRows()) {
            return;
        }

        if (board.getHexagon(west, southWest).getUnit() != null) {
            trackSouthWest = false;
        }

        if (!trackSouthWest) {
            return;
        }

        validMoves.add(new Hexagon(west, southWest));
    }

    private void addSouthEastValidMoves(Hexagon hexagon, Board board, int move) {
        int east = hexagon.getColumn() + move;
        if (east >= board.getColumns()) {
            return;
        }

        if (isEven(east)) {
            southEastOffset++;
        }

        int southEast = hexagon.getRow() + southEastOffset;
        if (southEast >= board.getRows()) {
            return;
        }

        if (board.getHexagon(east, southEast).getUnit() != null) {
            trackSouthEast = false;
        }

        if (!trackSouthEast) {
            return;
        }

        validMoves.add(new Hexagon(east, southEast));
    }

    @Override
    public boolean isValidMove(GameEngine gameEngine, Hexagon checkHexagon) {
        for (Hexagon hexagon : getValidMoves(gameEngine, gameEngine.getSelectedHexagon())) {
            if (hexagon.equals(checkHexagon)) {
                return true;
            }
        }

        return false;
    }
}
