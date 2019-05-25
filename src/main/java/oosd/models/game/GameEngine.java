package oosd.models.game;

import oosd.models.board.Board;
import oosd.models.board.GameBoard;
import oosd.models.board.Piece;
import oosd.models.board.history.GameSnapshot;
import oosd.models.board.history.History;
import oosd.models.board.history.Snapshot;
import oosd.models.player.Player;
import oosd.models.units.Unit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static oosd.helpers.ListHelper.isNotEmpty;
import static oosd.helpers.ObjectHelper.exists;
import static oosd.helpers.ObjectHelper.isNull;

public class GameEngine implements Engine {
    private final History history;
    private Board board;
    private Piece selectedPiece;
    private Player turn;
    private int turnLimit;
    private int undoCount = 0;
    private List<Player> players;
    private Iterator<Player> playerIterator;

    public GameEngine(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
        this.history = new History(this);
        this.turnLimit = 10;

        // Whoever we add to the players list, the first one takes the turn
        if (isNotEmpty(players)) {
            this.playerIterator = players.listIterator();
            this.turn = playerIterator.next();
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

    @Override
    public Player getTurn() {
        return this.turn;
    }

    @Override
    public boolean undoTurn() {
        if (!getTurn().getUndoStatus()) {
            return false;
        }

        for (Player ignored : getPlayers()) {
            history.undo();
        }

        undoCount++;
        getTurn().setUndoMoves(undoCount);

        return true;
    }

    @Override
    public void moveUnit(Piece selectedPiece, Piece targetPiece) {
        // TODO: move undo count
        undoCount = 0;
        history.backup();
        getTurn().updateUndoStatus();
        targetPiece.setUnit(selectedPiece.getUnit());
        selectedPiece.setUnit(null);
        setSelectedPiece(null);
        getNextTurn();
        updateDefendPieces();
    }

    @Override
    public boolean canMoveUnit(Piece piece) {
        boolean unitExists = exists(piece.getUnit());
        boolean isValidMove = this.getSelectedPiece().isValidMove(this, piece);

        return !unitExists && isValidMove;
    }

    @Override
    public void defendUnit(Piece piece) {
        undoCount = 0;
        history.backup();
        getTurn().updateUndoStatus();
        piece.getUnit().startDefending();
        setSelectedPiece(null);
        getNextTurn();
    }

    @Override
    public boolean canDefendUnit(Piece piece) {
        Piece selectedPiece = getSelectedPiece();
        return exists(selectedPiece) && selectedPiece.equals(piece);
    }

    @Override
    public void attackUnit(Piece attackingPiece, Piece targetPiece) {
        targetPiece.getUnit().setCaptured(true);
        moveUnit(attackingPiece, targetPiece);
    }

    @Override
    public boolean canAttackUnit(Piece targetPiece) {
        if (isNull(targetPiece.getUnit())) {
            return false;
        }

        boolean isValidMove = this.getSelectedPiece().isValidMove(this, targetPiece);
        boolean isEnemyUnit = !targetPiece.getUnit().getPlayer().equals(this.getTurn());
        boolean isDefensive = targetPiece.getUnit().getDefendStatus();

        return isEnemyUnit && !isDefensive && isValidMove;
    }

    @Override
    public void selectUnit(Piece piece) {
        setSelectedPiece(piece);
    }

    @Override
    public boolean canSelectUnit(Piece piece) {
        boolean isEnemyUnit = !piece.getUnit().getPlayer().equals(getTurn());
        boolean isDefensive = piece.getUnit().getDefendStatus();

        return !isDefensive && !isEnemyUnit;
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
            int units = player.getAliveUnits().size();

            if (units > winningUnits) {
                winningPlayer = player;
                winningUnits = units;
            }
        }

        if (isNull(winningPlayer)) {
            return;
        }

        winningPlayer.incrementWin();

        for (Player player : getPlayers()) {
            if (player.equals(winningPlayer)) {
                continue;
            }

            player.incrementLoss();
        }
    }

    @Override
    public Snapshot save() {
        List<Player> oldPlayers = this.players;
        List<Player> savedPlayers = new ArrayList<>();
        Board oldBoard = this.board;
        Board savedBoard = new GameBoard(oldBoard.getColumns(), oldBoard.getRows());

        for (Player player : oldPlayers) {
            savedPlayers.add(player.save());
        }

        for (int row = 0; row < savedBoard.getRows(); row++) {
            for (int column = 0; column < savedBoard.getColumns(); column++) {
                Piece piece = oldBoard.getPiece(column, row);
                Unit unit = piece.getUnit();

                for (Player player : savedPlayers) {
                    if (isNull(unit) || isNull(player)) {
                        continue;
                    }

                    if (!unit.getPlayer().equals(player)) {
                        continue;
                    }

                    Unit savedUnit = unit.save();
                    savedUnit.setPlayer(player);
                    savedUnit.setCaptured(unit.isCaptured());
                    player.addUnit(savedUnit);
                    savedBoard.getPiece(column, row).setUnit(savedUnit);
                }
            }
        }

        return new GameSnapshot(savedBoard, savedPlayers);
    }

    @Override
    public void restore(Snapshot snapshot) {
        this.board = snapshot.getBoard();
        this.players = new ArrayList<>(snapshot.getPlayers());
        this.playerIterator = players.listIterator();

        while (playerIterator.hasNext()) {
            Player nextPlayer = playerIterator.next();
            if (nextPlayer.equals(turn)) {
                this.turn = nextPlayer;
                break;
            }
        }
    }

    /**
     * Set the selected piece on in the game.
     *
     * @param selectedPiece selected piece
     */
    private void setSelectedPiece(Piece selectedPiece) {
        this.selectedPiece = selectedPiece;
    }

    /**
     * Get the next turn by going through the list sequentially.
     */
    private void getNextTurn() {
        if (!playerIterator.hasNext()) {
            playerIterator = players.listIterator();
        }

        turn.incrementTurn();
        turn = playerIterator.next();
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
