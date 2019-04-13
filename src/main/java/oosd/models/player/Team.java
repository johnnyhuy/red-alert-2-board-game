package oosd.models.player;

// TODO: Convert this to C4J
// @invariant name.size() > 0
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
