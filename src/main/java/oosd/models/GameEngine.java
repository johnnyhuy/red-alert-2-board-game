package oosd.models;

import oosd.models.board.Board;
import oosd.models.board.Piece;
import oosd.models.board.history.BoardHistory;
import oosd.models.player.Player;
import oosd.models.units.Unit;

import java.util.Iterator;
import java.util.List;

import static oosd.helpers.ListHelper.isNotEmpty;
import static oosd.helpers.ObjectHelper.exists;

public class GameEngine {
    private final BoardHistory history;
    private Board board;
    private Piece selectedPiece;
    private Player turn;
    private List<Player> players;
    private Iterator<Player> playersIterator;

    public GameEngine(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
        this.history = new BoardHistory(board);

        // Whoever we add to the players list, the first one takes the turn
        if (isNotEmpty(players)) {
            this.playersIterator = players.listIterator();
            this.turn = playersIterator.next();
        }
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
     * Get the selected piece user clicks.
     *
     * @return selected piece
     */
    public Piece getSelectedPiece() {
        return selectedPiece;
    }

    /**
     * Set the selected piece on in the game.
     *
     * @param selectedPiece selected piece
     */
    void setSelectedPiece(Piece selectedPiece) {
        this.selectedPiece = selectedPiece;
    }

    /**
     * Get the players in the game.
     *
     * @return list of players in the game
     */
    List<Player> getPlayers() {
        return this.players;
    }

    /**
     * Get the turn of the game.
     *
     * @return a player in the turn
     */
    public Player getTurn() {
        return this.turn;
    }

    /**
     * Get the next turn by going through the list sequentially.
     *
     * @return player in the turn
     */
    Player getNextTurn() {
        if (!playersIterator.hasNext()) {
            playersIterator = players.listIterator();
        }

        turn = playersIterator.next();

        return turn;
    }

    /**
     * Update defend statue pieces.
     */
    private void updateDefendPieces() {
        board.apply((column, row) -> {
            Unit unit = board.getPiece(column, row).getUnit();

            if (exists(unit) && unit.getDefendStatus()) {
                unit.decrementDefendTurns();
            }
        });
    }

    /**
     * Undo player turns.
     */
    public void undoTurn() {
        for (Player player : getPlayers()) {
            history.undo();
        }
    }

    public void moveUnit(Piece selectedPiece, Piece targetPiece) {
        history.backup();
        targetPiece.setUnit(selectedPiece.getUnit());
        selectedPiece.setUnit(null);
        setSelectedPiece(null);
        getNextTurn();
        updateDefendPieces();
    }

    public void defendUnit(Piece piece) {
        history.backup();
        piece.getUnit().startDefending();
        setSelectedPiece(null);
        getNextTurn();
    }

    public void attackUnit(Piece attackingPiece, Piece targetPiece) {
        history.backup();
        targetPiece.setUnit(attackingPiece.getUnit());
        attackingPiece.setUnit(null);
        setSelectedPiece(null);
        getNextTurn();
    }

    public void selectUnit(Piece piece) {
        setSelectedPiece(piece);
    }
}
