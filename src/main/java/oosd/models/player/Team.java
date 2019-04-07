package oosd.models.player;

import com.google.java.contract.Ensures;

public class Team {
    private String name;

    public Team(String name) {
        this.name = name;
    }

    @Ensures("name.size() > 0")
    public String getName() {
        return name;
    }
}
