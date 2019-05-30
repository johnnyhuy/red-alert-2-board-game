package oosd.views.components.polygons;

import oosd.models.board.Piece;
import oosd.views.components.images.DefendPieceImage;
import oosd.views.factories.ViewComponentFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.HashMap;

@Component
public class BoardPolygonMap {
    private HashMap<Piece, SelectionPiecePolygon> selectionPieces;
    private HashMap<Piece, UnitPiecePolygon> unitPieces;
    private HashMap<Piece, DefendPieceImage> defendPieces;
    private HashMap<Piece, BackgroundPiecePolygon> backgroundPieces;

    @Inject
    public BoardPolygonMap(ApplicationContext context) {
        ViewComponentFactory boardFactory = context.getBean(ViewComponentFactory.class);
        this.unitPieces = boardFactory.createUnitPiecePolygons();
        this.selectionPieces = boardFactory.createSelectionPiecePolygons();
        this.defendPieces = boardFactory.createDefendPieceImage();
        this.backgroundPieces = boardFactory.createBackgroundPiecePolygons();
    }

    public SelectionPiecePolygon getSelectionPiece(Piece piece) {
        return selectionPieces.get(piece);
    }

    public UnitPiecePolygon getUnitPiece(Piece piece) {
        return unitPieces.get(piece);
    }

    public DefendPieceImage getDefendPiece(Piece piece) {
        return defendPieces.get(piece);
    }

    public BackgroundPiecePolygon getBackgroundPiece(Piece piece) {
        return backgroundPieces.get(piece);
    }
}
