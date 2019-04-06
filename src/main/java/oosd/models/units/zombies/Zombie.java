package oosd.models.units.zombies;

import oosd.models.player.Player;
import oosd.models.units.Unit;

abstract class Zombie extends Unit {
    Zombie(Player player) {
        super(player);
    }
}
