package oosd.contracts.models;

import de.vksi.c4j.Contract;
import de.vksi.c4j.Target;
import oosd.models.player.Player;
import oosd.models.units.Unit;

import static de.vksi.c4j.Condition.*;

@Contract
public class PlayerContract extends Player {
    @Target
    private Player target;

    public PlayerContract(String playerName) {
        super(playerName);

        assert !preCondition() || !playerName.isEmpty();
    }

    @Override
    public void addUnit(Unit newUnit) {
        assert !preCondition() || target.getAllUnits().size() < 20;

        assert !postCondition() || target.getAllUnits().size() == old(target.getAllUnits().size() + 1);
    }
}
