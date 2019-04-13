package oosd.models.player;

import de.vksi.c4j.ContractReference;
import oosd.contracts.models.TeamContract;

@ContractReference(TeamContract.class)
public class Team {
    private String name;

    public Team(String name) {
        this.name = name;
    }

    // @post.condition name.size() > 0
    public String getName() {
        return name;
    }
}
