package oosd.models.units.zombies;

import oosd.models.player.Player;
import oosd.models.units.Unit;

public abstract class Zombie extends Unit {
    protected Zombie(Player player) {
        super(player);
    }
}
