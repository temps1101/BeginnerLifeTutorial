package beginnerlifetutorial.beginnerlifetutorial.utils;

import beginnerlifetutorial.beginnerlifetutorial.TutorialType;
import org.bukkit.inventory.PlayerInventory;

public class PlayerStatus {
    private TutorialType tutorialType;
    private PlayerInventory beforeTutorialInventory;

    public void setTutorialType(TutorialType tutorialType) {
        this.tutorialType = tutorialType;
    }

    public void setBeforeTutorialInventory(PlayerInventory beforeTutorialInventory) {
        this.beforeTutorialInventory = beforeTutorialInventory;
    }

    public TutorialType getTutorialType() {
        return tutorialType;
    }

    public PlayerInventory getBeforeTutorialInventory() {
        return beforeTutorialInventory;
    }
}
