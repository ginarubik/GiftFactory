import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Elf {

    private String name;
    private List<Gift> storedGifts;

    public Elf(String name) {
        this.name = name;
        this.storedGifts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void receiveGift(Gift gift) {
        storedGifts.add(gift);
    }

    // wrapGifts command
    public void wrapGifts() {
        for (Gift gift : storedGifts) {
            if (!gift.isWrapped()) {
                gift.wrap();
            }
        }
    }

    // passToSanta command
    public void passToSanta(SantaClaus santa) {
        List<Gift> toSend = new ArrayList<>();

        for (Gift gift : storedGifts) {
            if (gift.isWrapped() && !gift.isApproved()) {
                toSend.add(gift);
            }
        }

        for (Gift gift : toSend) {
            santa.receiveGift(gift, this);
            storedGifts.remove(gift);
        }
    }

    // Called by Santa to return approved gifts
    public void receiveApprovedGift(Gift gift) {
        storedGifts.add(gift);
    }

    // fillTheSack command
    public void fillTheSack() {
        Iterator<Gift> iterator = storedGifts.iterator();

        while (iterator.hasNext()) {
            Gift gift = iterator.next();
            if (gift.isApproved()) {
                System.out.println("🎁 Gift placed in sack: " + gift.getName());
                iterator.remove();
            }
        }
    }

    public void printStoredGifts() {
        System.out.println("Elf " + name + " has:");
        for (Gift gift : storedGifts) {
            System.out.println("   " + gift);
        }
    }
}
