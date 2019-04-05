package oosd.models;

import oosd.models.board.Board;
import oosd.models.board.Hexagon;
import oosd.models.player.Player;
import oosd.models.player.Team;
import oosd.models.units.humans.Plane;
import oosd.models.units.humans.Soldier;
import oosd.models.units.humans.Tank;
import oosd.models.units.zombies.JuggernautZombie;
import oosd.models.units.zombies.ScoutZombie;
import oosd.models.units.zombies.Zombat;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private Board board;
    private Hexagon selectedHexagon;

    public GameEngine(int boardColumns, int boardRows) {
        this.board = new Board(boardColumns, boardRows);
    }

    /**
     * GRASP: The creator
     * Responsible to initialize game pieces and players in the board.
     */
    public void initialize() {
        Player playerOne = new Player("Johnny Dave", Team.RED);
        Player playerTwo = new Player("Jane Doe", Team.BLUE);

        board.getHexagon(0, 0).setUnit(new Soldier(playerOne));
        board.getHexagon(1, 0).setUnit(new Soldier(playerOne));
        board.getHexagon(2, 0).setUnit(new Tank(playerOne));
        board.getHexagon(3, 0).setUnit(new Tank(playerOne));
        board.getHexagon(4, 0).setUnit(new Plane(playerOne));
        board.getHexagon(5, 0).setUnit(new Plane(playerOne));
        board.getHexagon(6, 0).setUnit(new Tank(playerOne));
        board.getHexagon(7, 0).setUnit(new Tank(playerOne));
        board.getHexagon(8, 0).setUnit(new Soldier(playerOne));
        board.getHexagon(9, 0).setUnit(new Soldier(playerOne));
        board.getHexagon(0, 9).setUnit(new ScoutZombie(playerTwo));
        board.getHexagon(1, 9).setUnit(new ScoutZombie(playerTwo));
        board.getHexagon(2, 9).setUnit(new Zombat(playerTwo));
        board.getHexagon(3, 9).setUnit(new Zombat(playerTwo));
        board.getHexagon(4, 9).setUnit(new JuggernautZombie(playerTwo));
        board.getHexagon(5, 9).setUnit(new JuggernautZombie(playerTwo));
        board.getHexagon(6, 9).setUnit(new Zombat(playerTwo));
        board.getHexagon(7, 9).setUnit(new Zombat(playerTwo));
        board.getHexagon(8, 9).setUnit(new ScoutZombie(playerTwo));
        board.getHexagon(9, 9).setUnit(new ScoutZombie(playerTwo));
    }

    /**
     * Get the game board.
     *
     * @return board that contains the game
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * Get the selected hexagon user clicks.
     *
     * @return selected hexagon
     */
    public Hexagon getSelectedHexagon() {
        return selectedHexagon;
    }

    /**
     * Set the selected hexagon on in the game.
     *
     * @param selectedHexagon selected hexagon
     */
    public void setSelectedHexagon(Hexagon selectedHexagon) {
        this.selectedHexagon = selectedHexagon;
    }

    /**
     * Get valid moves from a given hexagon.
     *
     * @param hexagon used to validate against
     * @return list of valid hexagons
     */
    public List<Hexagon> getValidMoves(Hexagon hexagon) {
        if (hexagon.getUnit() == null) {
            throw new NullPointerException("Selected hexagon must have a unit to check move validation.");
        }

        List<Hexagon> validMoves = new ArrayList<>();
        int northEastOffset = 0;
        int northWestOffset = 0;
        int southEastOffset = 0;
        int southWestOffset = 0;

        // TODO: Please refactor me :(

        for (int move = 1; move <= hexagon.getUnit().getMove(); move++) {
            int north = hexagon.getRow() - move;
            int south = hexagon.getRow() + move;

            if (north < board.getRows() && north > 0 && board.getHexagon(hexagon.getColumn(), north).getUnit() == null) {
                validMoves.add(new Hexagon(hexagon.getColumn(), north));
            }

            if (south < board.getRows() && board.getHexagon(hexagon.getColumn(), south).getUnit() == null) {
                validMoves.add(new Hexagon(hexagon.getColumn(), south));
            }
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

    /**
     * Check whether the given hexagon is valid based on the selected hexagon.
     *
     * @param checkHexagon used to check against the selected hexagon valid moves
     * @return whether the hexagon location is a valid move
     */
    public boolean isValidMove(Hexagon checkHexagon) {
        for (Hexagon hexagon : getValidMoves(selectedHexagon)) {
            if (hexagon.equals(checkHexagon)) {
                return true;
            }
        }

        return false;
    }
}
