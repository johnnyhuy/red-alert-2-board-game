package oosd.models.game;

import oosd.models.board.Board;
import oosd.models.board.Piece;
import oosd.models.board.history.History;
import oosd.models.player.Player;
import oosd.models.units.Unit;

import java.util.Iterator;
import java.util.List;

import static oosd.helpers.ListHelper.isNotEmpty;
import static oosd.helpers.ObjectHelper.exists;

public class GameEngine implements Engine {
    private final History history;
    private Board board;
    private Piece selectedPiece;
    private Player turn;
    private int turnLimit;
    private List<Player> players;
    private Iterator<Player> playersIterator;

    public GameEngine(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
        this.history = new History(board, players);
        this.turnLimit = 10;

        // Whoever we add to the players list, the first one takes the turn
        if (isNotEmpty(players)) {
            this.playersIterator = players.listIterator();
            this.turn = playersIterator.next();
        }
    }

    public GameEngine(Board board, List<Player> players, int turns) {
        this(board, players);
        this.turnLimit = turns;
    }

    @Override
    public Board getBoard() {
        return this.board;
    }

    @Override
    public Piece getSelectedPiece() {
        return selectedPiece;
    }

    /**
     * Set the selected piece on in the game.
     *
     * @param selectedPiece selected piece
     */
    private void setSelectedPiece(Piece selectedPiece) {
        this.selectedPiece = selectedPiece;
    }

    @Override
    public Player getTurn() {
        return this.turn;
    }

    @Override
    public boolean undoTurn() {
        Player player = getTurn();

        if (!player.getUndoStatus()) {
            return false;
        }

        player.incrementUndoMoves();

        for (Player ignored : getPlayers()) {
            history.undo();
        }

        return true;
    }

    @Override
    public void moveUnit(Piece selectedPiece, Piece targetPiece) {
        history.backup();
        getTurn().updateUndoStatus();
        targetPiece.setUnit(selectedPiece.getUnit());
        selectedPiece.setUnit(null);
        setSelectedPiece(null);
        getNextTurn();
        updateDefendPieces();
    }

    @Override
    public void defendUnit(Piece piece) {
        history.backup();
        getTurn().updateUndoStatus();
        piece.getUnit().startDefending();
        setSelectedPiece(null);
        getNextTurn();
    }

    @Override
    public void attackUnit(Piece attackingPiece, Piece targetPiece) {
        history.backup();
        getTurn().updateUndoStatus();
        targetPiece.getUnit().setCaptured(true);
        targetPiece.setUnit(attackingPiece.getUnit());
        attackingPiece.setUnit(null);
        setSelectedPiece(null);
        getNextTurn();
    }

    @Override
    public void selectUnit(Piece piece) {
        setSelectedPiece(piece);
    }

    @Override
    public int getTurns() {
        int turnCount = 0;

        for (Player player : getPlayers()) {
            turnCount += player.getTurns();
        }

        return turnCount;
    }

    @Override
    public int getRemainingTurns() {
        int turns = turnLimit - getTurns();

        // A bit of defensive programming here
        return turns < 0 ? 0 : turns;
    }

    @Override
    public int getTurnLimit() {
        return turnLimit;
    }

    @Override
    public void resetGame() {
        history.reset();
    }

    @Override
    public void forfeitGame() {
        Player playerTurn = getTurn();
        playerTurn.incrementLoss();

        for (Player player : getPlayers()) {
            if (playerTurn.equals(player)) {
                continue;
            }

            player.incrementWin();
        }

        resetGame();
    }

    @Override
    public void endGame() {
        Player winningPlayer = null;
        int winningUnits = 0;

        for (Player player : getPlayers()) {
            int units = player.getAllUnits().size();

            if (units > winningUnits) {
                winningPlayer = player;
                winningUnits = units;
            }
        }

        winningPlayer.incrementWin();

        for (Player player : getPlayers()) {
            if (player.equals(winningPlayer)) {
                continue;
            }

            player.incrementLoss();
        }
    }

    /**
     * Get the next turn by going through the list sequentially.
     */
    private void getNextTurn() {
        if (!playersIterator.hasNext()) {
            playersIterator = players.listIterator();
        }

        turn.incrementTurn();
        turn = playersIterator.next();
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
     * Get the players in the game.
     *
     * @return list of players in the game
     */
    private List<Player> getPlayers() {
        return this.players;
    }
}
