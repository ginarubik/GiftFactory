import java.util.*;

public class GiftFactory {

    private Map<String, Elf> elves;
    private SantaClaus santa;

    public GiftFactory() {
        this.elves = new LinkedHashMap<>();
        this.santa = new SantaClaus();
    }

    public void executeCommand(String input) {

        if (input.startsWith("HireElf")) {
            String name = input.split(" : ")[1];
            elves.put(name, new Elf(name));
        }

        else if (input.startsWith("NewToy")) {
            String[] parts = input.split(" : ")[1].split(" \\| ");

            String name = parts[0];
            String typeStr = parts[1];
            int age = Integer.parseInt(parts[2]);
            String elfName = parts[3];

            Toy.ToyType type = typeStr.equalsIgnoreCase("plush animal")
                    ? Toy.ToyType.PLUSH_ANIMAL
                    : Toy.ToyType.BOARD_GAME;

            Gift toy = new Toy(name, type, age);
            elves.get(elfName).receiveGift(toy);
        }

        else if (input.startsWith("NewBook")) {
            String[] parts = input.split(" : ")[1].split(" \\| ");

            String name = parts[0];
            int pages = Integer.parseInt(parts[1]);
            int age = Integer.parseInt(parts[2]);
            String elfName = parts[3];

            Gift book = new Book(name, pages, age);
            elves.get(elfName).receiveGift(book);
        }

        else if (input.startsWith("WrapGifts")) {
            String elfName = input.split(" : ")[1];
            elves.get(elfName).wrapGifts();
        }

        else if (input.equals("PassToSanta")) {
            for (Elf elf : elves.values()) {
                elf.passToSanta(santa);
            }
        }

        else if (input.equals("ApproveGifts")) {
            santa.approveGifts();
        }

        else if (input.equals("FillTheSack")) {
            for (Elf elf : elves.values()) {
                elf.fillTheSack();
            }
        }

        else if (input.equals("Quit")) {
            printStatus();
        }
    }

    private void printStatus() {
        System.out.println("===== FACTORY STATUS =====");

        for (Elf elf : elves.values()) {
            System.out.println("Elf: " + elf.getName());
            elf.printStoredGifts();
            System.out.println();
        }
    }
}
