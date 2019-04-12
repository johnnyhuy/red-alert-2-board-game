package oosd.models.player;

import static org.valid4j.Assertive.*;

// Invariant name.length > 0
public class Team {
    private String name;

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
    	// Post-condition
    	require(name.length() > 0);
    	
        return name;
    }
}
