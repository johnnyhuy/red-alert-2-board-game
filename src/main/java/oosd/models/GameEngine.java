package oosd.models;

import oosd.models.board.Board;
import oosd.models.board.Hexagon;
import oosd.models.player.Player;
import oosd.models.player.Team;
import oosd.models.units.*;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private Board board;
    private Hexagon selectedHexagon;

    public GameEngine() {
        final int boardRow = 10;
        final int boardColumn = 10;

        this.board = new Board(boardColumn, boardRow);
    }

    /**
     * GRASP: The creator
     * Responsible to initialize game pieces and players in the board.
     */
    public void initialize() {
        Player playerOne = new Player("Johnny Dave", Team.RED);
        Player playerTwo = new Player("Jane Doe", Team.BLUE);

        board.getHexagon(0, 0).setUnit(new Tank(playerOne));
        board.getHexagon(1, 0).setUnit(new Plane(playerOne));
        board.getHexagon(2, 0).setUnit(new Soldier(playerOne));
        board.getHexagon(3, 0).setUnit(new Soldier(playerOne));
        board.getHexagon(4, 0).setUnit(new Soldier(playerOne));
        board.getHexagon(4, 4).setUnit(new Zombat(playerOne));
        board.getHexagon(0, 9).setUnit(new Zombat(playerTwo));
        board.getHexagon(1, 9).setUnit(new ScoutZombie(playerTwo));
        board.getHexagon(2, 9).setUnit(new Zombat(playerTwo));
        board.getHexagon(3, 9).setUnit(new JuggernautZombie(playerTwo));
        board.getHexagon(4, 9).setUnit(new ScoutZombie(playerTwo));
        board.getHexagon(5, 9).setUnit(new ScoutZombie(playerTwo));
        board.getHexagon(6, 9).setUnit(new ScoutZombie(playerTwo));
        board.getHexagon(7, 9).setUnit(new ScoutZombie(playerTwo));
        board.getHexagon(8, 9).setUnit(new ScoutZombie(playerTwo));
        board.getHexagon(9, 9).setUnit(new ScoutZombie(playerTwo));
    }

    public Board getBoard() {
        return this.board;
    }

    public Hexagon getSelectedHexagon() {
        return selectedHexagon;
    }

    public void setSelectedHexagon(Hexagon selectedHexagon) {
        this.selectedHexagon = selectedHexagon;
    }

    public List<Hexagon> getValidMoves(Hexagon hexagon) {
        List<Hexagon> validMoves = new ArrayList<>();
        int northEastOffset = 0;
        int northWestOffset = 0;
        int southEastOffset = 0;
        int southWestOffset = 0;

        // TODO: Please refactor me :(

        for (int move = 1; move <= hexagon.getUnit().getMove(); move++) {
            int north = hexagon.getRow() - move;
            if (north >= board.getRows() || north < 0) {
                continue;
            }

            if (board.getHexagon(hexagon.getColumn(), north).getUnit() != null) {
                break;
            }

            validMoves.add(new Hexagon(hexagon.getColumn(), north));
        }

        for (int move = 1; move <= hexagon.getUnit().getMove(); move++) {
            int south = hexagon.getRow() + move;
            if (south >= board.getRows()) {
                continue;
            }

            if (board.getHexagon(hexagon.getColumn(), south).getUnit() != null) {
                break;
            }

            validMoves.add(new Hexagon(hexagon.getColumn(), south));
        }

        for (int move = 1; move <= hexagon.getUnit().getMove(); move++) {
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

        for (int move = 1; move <= hexagon.getUnit().getMove(); move++) {
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

        for (int move = 1; move <= hexagon.getUnit().getMove(); move++) {
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

        for (int move = 1; move <= hexagon.getUnit().getMove(); move++) {
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

    public boolean isValidMove(Hexagon selectedHexagon) {
        for (Hexagon hexagon : getValidMoves(selectedHexagon)) {
            if (hexagon.equals(selectedHexagon)) {
                return true;
            }
        }

        return false;
    }
}
