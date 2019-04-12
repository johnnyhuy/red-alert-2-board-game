package oosd.models.player;

import static org.valid4j.Assertive.*;

public class Team {
    private String name;

    public Team(String name) {
        this.name = name;
        
        // Invariant
        assert name.length() > 0 : "Name cannot be empty.";
    }

    public String getName() {
    	// Post-condition
    	require(name.length() > 0);
    	
        return name;
    }
}
