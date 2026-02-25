import java.util.HashMap;
import java.util.Map;

public class SantaClaus {

    private Map<Gift, Elf> storedGifts;

    public SantaClaus() {
        this.storedGifts = new HashMap<>();
    }

    public void receiveGift(Gift gift, Elf elf) {
        storedGifts.put(gift, elf);
    }

    // approveGifts command
    public void approveGifts() {

        for (Map.Entry<Gift, Elf> entry : storedGifts.entrySet()) {
            Gift gift = entry.getKey();
            Elf elf = entry.getValue();

            gift.approve();
            elf.receiveApprovedGift(gift);
        }

        storedGifts.clear();
    }
}
