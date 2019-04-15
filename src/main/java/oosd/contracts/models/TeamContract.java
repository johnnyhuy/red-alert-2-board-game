package oosd.contracts.models;

import de.vksi.c4j.Target;
import de.vksi.c4j.ClassInvariant;
import oosd.models.player.Team;

import static de.vksi.c4j.Condition.preCondition;

public class TeamContract extends Team {
    @Target
    private Team target;
    
    @ClassInvariant
    public void classInvariant() {
    	assert !target.getName().isEmpty();
    	assert target.getName().length() != 0;
    }

    public TeamContract(String name) {
        super(name);

        if (preCondition()) {
            assert !name.isEmpty();
        	assert name.length() != 0;
        }
    }
}
