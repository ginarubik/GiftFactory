public class Toy extends Gift {

    public enum ToyType {
        PLUSH_ANIMAL,
        BOARD_GAME
    }

    private ToyType type;

    public Toy(String name, ToyType type, int recommendedAge) {
        super(name, recommendedAge);
        this.type = type;
    }

    public ToyType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Toy: " + getName() +
                " | Type: " + type +
                " | Age: " + getRecommendedAge() +
                " | Wrapped: " + isWrapped() +
                " | Approved: " + isApproved();
    }
}
