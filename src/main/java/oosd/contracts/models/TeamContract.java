package oosd.contracts.models;

import de.vksi.c4j.Target;
import oosd.models.player.Team;

import static de.vksi.c4j.Condition.preCondition;

public class TeamContract extends Team {
    @Target
    private Team target;

    public TeamContract(String name) {
        super(name);

        if (preCondition()) {
            assert !name.isEmpty();
        }
    }
}
