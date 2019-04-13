package oosd.models.player;

import de.vksi.c4j.ContractReference;
import oosd.contracts.models.TeamContract;

// TODO: Convert this to C4J
// @invariant name.size() > 0
@ContractReference(TeamContract.class)
public class Team {
    private String name;
    public int NAME_SIZE = 0;

    public Team(String name) {
        this.name = name;
    }

    // @post.condition name.size() > 0
    public String getName() {
        return name;
    }
}
