package oosd.contracts.models;

import de.vksi.c4j.Target;
import oosd.models.player.Player;
import oosd.models.units.Unit;
import oosd.models.units.behaviour.UnitBehaviour;

import java.util.List;

import static de.vksi.c4j.Condition.ignored;
import static de.vksi.c4j.Condition.preCondition;

public class UnitContract extends Unit {
    @Target
    private Unit target;

    public UnitContract(Player player) {
        super(player);
    }

    @Override
    public List<Class<? extends Unit>> getWinnables() {
        if (preCondition()) {
            assert target.getWinnables().size() > 0;
        }

        return ignored();
    }

    @Override
    public String getName() {
        if (preCondition()) {
            assert !target.getName().isEmpty();
        }

        return ignored();
    }

    @Override
    public String getImage() {
        return ignored();
    }

    @Override
    public UnitBehaviour getUnitBehaviour() {
        return ignored();
    }
}
