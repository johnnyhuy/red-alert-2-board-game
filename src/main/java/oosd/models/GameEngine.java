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
        Hexagon[][] hexagons = this.board.getHexagons();
        Player playerOne = new Player("Johnny Dave", Team.RED);
        Player playerTwo = new Player("Jane Doe", Team.BLUE);

        hexagons[0][0].setUnit(new Tank(playerOne));
        hexagons[1][0].setUnit(new Plane(playerOne));
        hexagons[2][0].setUnit(new Soldier(playerOne));
        hexagons[3][0].setUnit(new Soldier(playerOne));
        hexagons[4][0].setUnit(new Soldier(playerOne));
        hexagons[4][4].setUnit(new Zombat(playerOne));
        hexagons[0][9].setUnit(new Zombat(playerTwo));
        hexagons[1][9].setUnit(new ScoutZombie(playerTwo));
        hexagons[2][9].setUnit(new Zombat(playerTwo));
        hexagons[3][9].setUnit(new JuggernautZombie(playerTwo));
        hexagons[4][9].setUnit(new ScoutZombie(playerTwo));
        hexagons[5][9].setUnit(new ScoutZombie(playerTwo));
        hexagons[6][9].setUnit(new ScoutZombie(playerTwo));
        hexagons[7][9].setUnit(new ScoutZombie(playerTwo));
        hexagons[8][9].setUnit(new ScoutZombie(playerTwo));
        hexagons[9][9].setUnit(new ScoutZombie(playerTwo));
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

    public List<Hexagon> getValidMoves() {
        List<Hexagon> hexagons = new ArrayList<>();
        Hexagon selectedHexagon = getSelectedHexagon();
        int northEastOffset = 0;
        int northWestOffset = 0;
        int southEastOffset = 0;
        int southWestOffset = 0;

        if (selectedHexagon == null) {
            return hexagons;
        }

        for (int move = 1; move <= selectedHexagon.getUnit().getMove(); move++) {
            int north = selectedHexagon.getRow() - move;
            if (north >= board.getRows() || north < 0) {
                continue;
            }

            hexagons.add(new Hexagon(selectedHexagon.getColumn(), north));
        }

        for (int move = 1; move <= selectedHexagon.getUnit().getMove(); move++) {
            int south = selectedHexagon.getRow() + move;
            if (south >= board.getRows()) {
                continue;
            }

            hexagons.add(new Hexagon(selectedHexagon.getColumn(), south));
        }

        for (int move = 1; move <= selectedHexagon.getUnit().getMove(); move++) {
            int west = selectedHexagon.getColumn() - move;
            if (west >= board.getColumns() || west < 0) {
                continue;
            }

            if (west % 2 != 0) {
                northWestOffset++;
            }

            int northWest = selectedHexagon.getRow() - northWestOffset;
            if (northWest >= board.getRows() || northWest < 0) {
                continue;
            }

            hexagons.add(new Hexagon(west, northWest));
        }

        for (int move = 1; move <= selectedHexagon.getUnit().getMove(); move++) {
            int west = selectedHexagon.getColumn() - move;
            if (west >= board.getColumns() || west < 0) {
                continue;
            }

            if (west % 2 == 0) {
                southWestOffset++;
            }

            int southWest = selectedHexagon.getRow() + southWestOffset;
            if (southWest >= board.getRows()) {
                continue;
            }

            hexagons.add(new Hexagon(west, southWest));
        }

        for (int move = 1; move <= selectedHexagon.getUnit().getMove(); move++) {
            int east = selectedHexagon.getColumn() + move;
            if (east >= board.getColumns()) {
                continue;
            }

            if (east % 2 != 0) {
                northEastOffset++;
            }

            int northEast = selectedHexagon.getRow() - northEastOffset;
            if (northEast >= board.getRows() || northEast < 0) {
                continue;
            }

            hexagons.add(new Hexagon(east, northEast));
        }

        for (int move = 1; move <= selectedHexagon.getUnit().getMove(); move++) {
            int east = selectedHexagon.getColumn() + move;
            if (east >= board.getColumns()) {
                continue;
            }

            if (east % 2 == 0) {
                southEastOffset++;
            }

            int southEast = selectedHexagon.getRow() + southEastOffset;
            if (southEast >= board.getRows()) {
                continue;
            }

            hexagons.add(new Hexagon(east, southEast));
        }

        return hexagons;
    }
}
