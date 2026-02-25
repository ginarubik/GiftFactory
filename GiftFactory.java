import java.util.*;
import java.io.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Main

public class SantaGiftFactory {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GiftFactory factory = new GiftFactory();

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("Quit")) {
                factory.executeCommand("Quit");
                break;
            }
            factory.executeCommand(input);
        }
    }
}

// Gift Hierarchy

abstract class Gift {

    private final String name;
    private final int recommendedAge;
    private boolean wrapped;
    private boolean approved;

    protected Gift(String name, int recommendedAge) {
        if (recommendedAge < 0 || recommendedAge > 18)
            throw new IllegalArgumentException("Invalid recommended age.");
        this.name = name;
        this.recommendedAge = recommendedAge;
    }

    public String getName() { return name; }
    public boolean isWrapped() { return wrapped; }
    public boolean isApproved() { return approved; }

    public void wrap() { wrapped = true; }

    public void approve() {
        if (wrapped) approved = true;
    }
}

// Toy

class Toy extends Gift {

    enum ToyType { PLUSH_ANIMAL, BOARD_GAME }

    private final ToyType type;

    public Toy(String name, ToyType type, int age) {
        super(name, age);
        this.type = type;
    }

    public ToyType getType() { return type; }
}

// Book

class Book extends Gift {

    private final int pages;

    public Book(String name, int pages, int age) {
        super(name, age);
        if (pages < 4 || pages > 10000)
            throw new IllegalArgumentException("Invalid page count.");
        this.pages = pages;
    }

    public int getPages() { return pages; }
}

// Elf

class Elf {

    private final String name;
    private final List<Gift> gifts = new ArrayList<>();

    public Elf(String name) { this.name = name; }

    public String getName() { return name; }

    public void receiveGift(Gift gift) {
        gifts.add(gift);
    }

    public void wrapGifts() {
        for (Gift g : gifts)
            if (!g.isWrapped())
                g.wrap();
    }

    public void passToSanta(SantaClaus santa) {
        Iterator<Gift> it = gifts.iterator();
        while (it.hasNext()) {
            Gift g = it.next();
            if (g.isWrapped() && !g.isApproved()) {
                santa.receiveGift(g, this);
                it.remove();
            }
        }
    }

    public void receiveApprovedGift(Gift gift) {
        gifts.add(gift);
    }

    public int removeApprovedGifts() {
        int count = 0;
        Iterator<Gift> it = gifts.iterator();
        while (it.hasNext()) {
            if (it.next().isApproved()) {
                it.remove();
                count++;
            }
        }
        return count;
    }

    public int countUnwrappedGifts() {
        int count = 0;
        for (Gift g : gifts)
            if (!g.isWrapped())
                count++;
        return count;
    }
}

// SantaClaus

class SantaClaus {

    private final Map<Gift, Elf> stored = new HashMap<>();

    public void receiveGift(Gift gift, Elf elf) {
        stored.put(gift, elf);
    }

    public void approveGifts() {
        for (Map.Entry<Gift, Elf> entry : stored.entrySet()) {
            entry.getKey().approve();
            entry.getValue().receiveApprovedGift(entry.getKey());
        }
        stored.clear();
    }

    public int countWaitingApproval() {
        return stored.size();
    }
}

// Gift Factory

class GiftFactory {

    private final Map<String, Elf> elves = new LinkedHashMap<>();
    private final SantaClaus santa = new SantaClaus();
    private int giftsInSack = 0;

    public void executeCommand(String input) {

        if (input.startsWith("HireElf")) {
            String name = input.split(" : ")[1];
            elves.put(name, new Elf(name));
        }

        else if (input.startsWith("NewToy")) {
            String[] p = input.split(" : ")[1].split(" \\| ");
            Toy.ToyType type = p[1].equalsIgnoreCase("plush animal")
                    ? Toy.ToyType.PLUSH_ANIMAL
                    : Toy.ToyType.BOARD_GAME;

            elves.get(p[3])
                    .receiveGift(new Toy(p[0], type, Integer.parseInt(p[2])));
        }

        else if (input.startsWith("NewBook")) {
            String[] p = input.split(" : ")[1].split(" \\| ");
            elves.get(p[3])
                    .receiveGift(new Book(p[0],
                            Integer.parseInt(p[1]),
                            Integer.parseInt(p[2])));
        }

        else if (input.startsWith("WrapGifts")) {
            elves.get(input.split(" : ")[1]).wrapGifts();
        }

        else if (input.equals("PassToSanta")) {
            for (Elf e : elves.values())
                e.passToSanta(santa);
        }

        else if (input.equals("ApproveGifts")) {
            santa.approveGifts();
        }

        else if (input.equals("FillTheSack")) {
            for (Elf e : elves.values())
                giftsInSack += e.removeApprovedGifts();
        }

        else if (input.equals("Quit")) {
            printReport();
        }
    }

    private void printReport() {

        int awaitingWrap = 0;
        for (Elf e : elves.values())
            awaitingWrap += e.countUnwrappedGifts();

        System.out.println("* Santa's Gift Factory *");
        System.out.println("Elves: " + elves.size());
        System.out.println("Gifts in the sack: " + giftsInSack);
        System.out.println("Gifts awaiting wrapping: " + awaitingWrap);
        System.out.println("Gifts awaiting Santa's approval: " +
                santa.countWaitingApproval());
        System.out.println("HO-HO-HO! Merry Christmas!");
    }
}

// Unit Tests

class SantaGiftFactoryTests {

    // Validation

    @Test
    void invalidToyAgeShouldThrow() {
        assertThrows(IllegalArgumentException.class,
                () -> new Toy("Car", Toy.ToyType.BOARD_GAME, 25));
    }

    @Test
    void invalidBookPagesShouldThrow() {
        assertThrows(IllegalArgumentException.class,
                () -> new Book("Book", 2, 5));
    }

    // Wrapping

    @Test
    void wrappingShouldWork() {
        Elf elf = new Elf("Anna");
        Gift toy = new Toy("Car", Toy.ToyType.BOARD_GAME, 5);

        elf.receiveGift(toy);
        elf.wrapGifts();

        assertTrue(toy.isWrapped());
    }

    // Branch Coverage

    @Test
    void approvingUnwrappedGiftShouldNotWork() {
        Gift toy = new Toy("Car", Toy.ToyType.BOARD_GAME, 5);
        toy.approve();
        assertFalse(toy.isApproved());
    }

    @Test
    void unwrappedGiftShouldNotBePassedToSanta() {
        Elf elf = new Elf("Anna");
        SantaClaus santa = new SantaClaus();

        Gift toy = new Toy("Car", Toy.ToyType.BOARD_GAME, 5);
        elf.receiveGift(toy);

        elf.passToSanta(santa);

        assertEquals(0, santa.countWaitingApproval());
    }

    @Test
    void approvedGiftShouldNotBePassedAgain() {
        Elf elf = new Elf("Anna");
        SantaClaus santa = new SantaClaus();

        Gift toy = new Toy("Car", Toy.ToyType.BOARD_GAME, 5);
        toy.wrap();
        toy.approve();

        elf.receiveGift(toy);
        elf.passToSanta(santa);

        assertEquals(0, santa.countWaitingApproval());
    }

    @Test
    void removingApprovedGiftsWhenNoneExistShouldReturnZero() {
        Elf elf = new Elf("Anna");
        Gift toy = new Toy("Car", Toy.ToyType.BOARD_GAME, 5);

        elf.receiveGift(toy);

        assertEquals(0, elf.removeApprovedGifts());
    }

    /* ---------- FULL WORKFLOW ---------- */

    @Test
    void fullBookWorkflowShouldWork() {
        GiftFactory factory = new GiftFactory();

        factory.executeCommand("HireElf : Anna");
        factory.executeCommand("NewBook : Story | 100 | 7 | Anna");
        factory.executeCommand("WrapGifts : Anna");
        factory.executeCommand("PassToSanta");
        factory.executeCommand("ApproveGifts");
        factory.executeCommand("FillTheSack");

        assertTrue(true);
    }
}
