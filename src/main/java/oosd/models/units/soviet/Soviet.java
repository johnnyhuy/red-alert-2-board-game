package oosd.models.units.soviet;

import oosd.models.board.Piece;
import oosd.models.player.Player;
import oosd.models.units.Unit;

abstract class Soviet extends Unit {
    Soviet(Piece location) {
        super(location);
    }
}
