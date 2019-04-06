package oosd.models.units.behaviour;

import oosd.models.GameEngine;
import oosd.models.board.Board;
import oosd.models.board.Hexagon;

import java.util.ArrayList;
import java.util.List;

public class LinearUnitBehaviour extends UnitBehaviour {
    private final int moves;

    public LinearUnitBehaviour(int moves) {
        this.moves = moves;
    }

    @Override
    public List<Hexagon> getValidMoves(GameEngine gameEngine, Hexagon hexagon) {
        Board board = gameEngine.getBoard();

        List<Hexagon> validMoves = new ArrayList<>();
        int northEastOffset = 0;
        int northWestOffset = 0;
        int southEastOffset = 0;
        int southWestOffset = 0;
        boolean trackNorth = true;
        boolean trackSouth = true;

        for (int move = 1; move <= moves; move++) {
            int north = hexagon.getRow() - move;
            int south = hexagon.getRow() + move;

            if (north < board.getRows() && north >= 0 && trackNorth) {
                if (board.getHexagon(hexagon.getColumn(), north).getUnit() != null) {
                    trackNorth = false;
                }

                if (trackNorth) {
                    validMoves.add(new Hexagon(hexagon.getColumn(), north));
                }
            }

            if (south < board.getRows()) {
                if (board.getHexagon(hexagon.getColumn(), south).getUnit() != null) {
                    trackSouth = false;
                }

                if (trackSouth) {
                    validMoves.add(new Hexagon(hexagon.getColumn(), south));
                }
            }
        }

        for (int move = 1; move <= moves; move++) {
            int west = hexagon.getColumn() - move;
            if (west >= board.getColumns() || west < 0) {
                continue;
            }

            if (west % 2 != 0) {
                northWestOffset++;
            }

            int northWest = hexagon.getRow() - northWestOffset;
            if (northWest >= board.getRows() || northWest < 0) {
                continue;
            }

            if (board.getHexagon(west, northWest).getUnit() != null) {
                break;
            }

            validMoves.add(new Hexagon(west, northWest));
        }

        for (int move = 1; move <= moves; move++) {
            int west = hexagon.getColumn() - move;
            if (west >= board.getColumns() || west < 0) {
                continue;
            }

            if (west % 2 == 0) {
                southWestOffset++;
            }

            int southWest = hexagon.getRow() + southWestOffset;
            if (southWest >= board.getRows()) {
                continue;
            }

            if (board.getHexagon(west, southWest).getUnit() != null) {
                break;
            }

            validMoves.add(new Hexagon(west, southWest));
        }

        for (int move = 1; move <= moves; move++) {
            int east = hexagon.getColumn() + move;
            if (east >= board.getColumns()) {
                continue;
            }

            if (east % 2 != 0) {
                northEastOffset++;
            }

            int northEast = hexagon.getRow() - northEastOffset;
            if (northEast >= board.getRows() || northEast < 0) {
                continue;
            }

            if (board.getHexagon(east, northEast).getUnit() != null) {
                break;
            }

            validMoves.add(new Hexagon(east, northEast));
        }

        for (int move = 1; move <= moves; move++) {
            int east = hexagon.getColumn() + move;
            if (east >= board.getColumns()) {
                continue;
            }

            if (east % 2 == 0) {
                southEastOffset++;
            }

            int southEast = hexagon.getRow() + southEastOffset;
            if (southEast >= board.getRows()) {
                continue;
            }

            if (board.getHexagon(east, southEast).getUnit() != null) {
                break;
            }

            validMoves.add(new Hexagon(east, southEast));
        }

        return validMoves;
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
