package oosd.models.units.allied;

import oosd.models.player.Player;
import oosd.models.units.Unit;

abstract class Allied extends Unit {
    Allied(Player player) {
        super(player);
    }
}
