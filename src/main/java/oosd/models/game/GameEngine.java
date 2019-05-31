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
import java.util.List;

import static oosd.helpers.ObjectHelper.exists;
import static oosd.helpers.ObjectHelper.isNull;

public class GameEngine implements Engine {
    private final History history;
    private TurnService turnService;
    private PlayerService playerService;
    private Board board;
    private Piece selectedPiece;
    private int undoCount = 0;

    public GameEngine(Board board, List<Player> players) {
        this.board = board;
        this.history = new History(this);
        this.playerService = new GamePlayerService(players);
        this.turnService = new GameTurnService(playerService, 10);
    }

    public GameEngine(Board board, List<Player> players, int turnLimit) {
        this(board, players);
        this.turnService = new GameTurnService(playerService, turnLimit);
    }

    @Override
    public Board getBoard() {
        return this.board;
    }

    @Override
    public Piece getSelected() {
        return selectedPiece;
    }

    @Override
    public TurnService getTurnService() {
        return this.turnService;
    }

    @Override
    public PlayerService playerService() {
        return this.playerService;
    }

    @Override
    public boolean undoTurn() {
        if (!getTurn().canUndo()) {
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
    public void move(Piece selectedPiece, Piece targetPiece) {
        undoCount = 0;
        history.backup();
        getTurn().updateUndoStatus();
        updateDefendPieces();
        targetPiece.setUnit(selectedPiece.getUnit());
        selectedPiece.setUnit(null);
        setSelectedPiece(null);
        getNextTurn();
    }

    @Override
    public boolean canMove(Piece piece) {
        boolean unitExists = exists(piece.getUnit());
        boolean isValidMove = this.getSelected().isValidMove(this, piece);

        return !unitExists && isValidMove;
    }

    @Override
    public void defend(Piece piece) {
        undoCount = 0;
        history.backup();
        getTurn().updateUndoStatus();
        updateDefendPieces();
        piece.getUnit().startDefending(piece);
        setSelectedPiece(null);
        getNextTurn();
    }

    @Override
    public boolean canDefend(Piece piece) {
        Piece selectedPiece = getSelected();
        return exists(selectedPiece) && selectedPiece.equals(piece);
    }

    @Override
    public void attack(Piece attackingPiece, Piece targetPiece) {
        undoCount = 0;
        history.backup();
        updateDefendPieces();
        getTurn().updateUndoStatus();
        targetPiece.getUnit().setCaptured(true);
        targetPiece.setUnit(attackingPiece.getUnit());
        attackingPiece.setUnit(null);
        setSelectedPiece(null);
        getNextTurn();
    }

    @Override
    public boolean canAttack(Piece targetPiece) {
        if (isNull(targetPiece.getUnit())) {
            return false;
        }

        boolean isValidMove = this.getSelected().isValidMove(this, targetPiece);
        boolean isEnemyUnit = !targetPiece.getUnit().getPlayer().equals(getTurn());
        boolean isDefensive = targetPiece.getUnit().canDefend(targetPiece);

        return isEnemyUnit && !isDefensive && isValidMove;
    }

    @Override
    public void select(Piece piece) {
        this.setSelectedPiece(piece);
    }

    @Override
    public boolean canSelect(Piece piece) {
        boolean isFriendlyUnit = piece.getUnit().getPlayer().equals(getTurn());
        boolean isDefensive = piece.getUnit().canDefend(piece);

        return !isDefensive && isFriendlyUnit;
    }

    @Override
    public void resetGame() {
        this.history.reset();
        this.turnService.resetTurn();

        for (Player player : getPlayers()) {
            player.setCanUndo(true);
            player.setUndoMoves(0);
            player.setTurns(0);
        }
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
        Player winningPlayer = getWinningPlayer();
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
        List<Player> oldPlayers = getPlayers();
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
                    savedUnit.setDefendTurns(unit.getDefendTurns());
                    savedUnit.setDefendingPiece(unit.getDefendingPiece());
                    player.addUnit(savedUnit);
                    savedBoard.getPiece(column, row).setUnit(savedUnit);
                }
            }
        }

        return new GameSnapshot(savedBoard, savedPlayers, turnService.getTurn());
    }

    @Override
    public void restore(Snapshot snapshot) {
        for (Player oldPlayer : getPlayers()) {
            for (Player newPlayer : snapshot.getPlayers()) {
                if (newPlayer.equals(oldPlayer)) {
                    newPlayer.setWins(oldPlayer.getWins());
                    newPlayer.setLosses(oldPlayer.getLosses());
                }
            }
        }

        this.board = snapshot.getBoard();
        this.playerService.setPlayers(snapshot.getPlayers());
        this.turnService.setTurn(snapshot.getTurn());
    }

    @Override
    public void saveGame() {
        history.save();
    }

    @Override
    public boolean saveGameExists() {
        return history.saveExists();
    }

    @Override
    public void restoreGame() {
        history.restore();
    }

    @Override
    public Player getWinningPlayer() {
        Player winningPlayer = null;
        int winningUnits = 0;

        for (Player player : getPlayers()) {
            int units = player.getAliveUnits().size();

            if (units > winningUnits) {
                winningPlayer = player;
                winningUnits = units;
            }
        }

        return winningPlayer;
    }

    /**
     * Get players convenience method.
     *
     * @return list of players
     */
    private List<Player> getPlayers() {
        return this.playerService.getPlayers();
    }

    /**
     * Get turn convenience method.
     *
     * @return player turn
     */
    private Player getTurn() {
        return this.turnService.getTurn();
    }

    /**
     * Get the next turn
     */
    private void getNextTurn() {
        this.turnService.getNextTurn();
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
     * Update defend statue pieces.
     */
    private void updateDefendPieces() {
        board.apply((column, row) -> {
            Piece piece = board.getPiece(column, row);
            Unit unit = piece.getUnit();

            if (exists(unit) && unit.canDefend(piece)) {
                unit.decrementDefendTurns();
            }
        });
    }
}
