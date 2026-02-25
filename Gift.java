public abstract class Gift {

    private String name;
    private int recommendedAge;
    private boolean wrapped;
    private boolean approved;

    public Gift(String name, int recommendedAge) {
        if (recommendedAge < 0 || recommendedAge > 18) {
            throw new IllegalArgumentException("Recommended age must be between 0 and 18.");
        }
        this.name = name;
        this.recommendedAge = recommendedAge;
        this.wrapped = false;
        this.approved = false;
    }

    public String getName() {
        return name;
    }

    public int getRecommendedAge() {
        return recommendedAge;
    }

    public boolean isWrapped() {
        return wrapped;
    }

    public boolean isApproved() {
        return approved;
    }

    public void wrap() {
        this.wrapped = true;
    }

    public void approve() {
        this.approved = true;
    }

    @Override
    public String toString() {
        return name +
                " (age " + recommendedAge + ")" +
                " | Wrapped: " + wrapped +
                " | Approved: " + approved;
    }
}

