package arcadeGame.gameHelpers;

public enum DeathType {
    SPIKE("Those Spikes can Really Hurt!"),
    ENEMY("They Really Come Out of Nowhere don't they... Jump on their Heads next time!"),
    HUNTER_SEEKER("They'll find you! Hit em' in the Head!");

    private final String encouragementString;

    DeathType(String message) {
        this.encouragementString = message;
    }

    public String getEncouragementString() {
        return this.encouragementString;
    }
}
