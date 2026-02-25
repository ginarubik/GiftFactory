public class Book extends Gift {

    private int pages;

    public Book(String name, int pages, int recommendedAge) {
        super(name, recommendedAge);

        if (pages < 4 || pages > 10000) {
            throw new IllegalArgumentException("Pages must be between 4 and 10000.");
        }

        this.pages = pages;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public String toString() {
        return "Book: " + getName() +
                " | Pages: " + pages +
                " | Age: " + getRecommendedAge() +
                " | Wrapped: " + isWrapped() +
                " | Approved: " + isApproved();
    }
}
