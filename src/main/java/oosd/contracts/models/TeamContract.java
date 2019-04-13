package oosd.contracts.models;

import de.vksi.c4j.ClassInvariant;
import de.vksi.c4j.Target;
import oosd.models.player.Team;

import static de.vksi.c4j.Condition.ignored;
import static de.vksi.c4j.Condition.preCondition;

public class TeamContract extends Team {
    @Target
    private Team target;

    @ClassInvariant
    public void classInvariant() {
        assert target.getName().length() > NAME_SIZE : "name > NAME_SIZE";
    }

    public TeamContract(String name) {
        super(name);

        if (preCondition()) {
            assert !name.isEmpty();
        }
    }

    @Override
    public String getName() {
        if (preCondition()) {
            assert target.getName().length() > NAME_SIZE : "name > NAME_SIZE";
        }

        return ignored();
    }
}
