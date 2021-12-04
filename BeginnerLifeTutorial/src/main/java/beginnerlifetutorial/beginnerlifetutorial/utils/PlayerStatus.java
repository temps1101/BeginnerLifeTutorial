package beginnerlifetutorial.beginnerlifetutorial.utils;

import beginnerlifetutorial.beginnerlifetutorial.enums.TutorialPhase;
import beginnerlifetutorial.beginnerlifetutorial.enums.TutorialType;
import org.bukkit.inventory.PlayerInventory;

public class PlayerStatus {
    private TutorialType tutorialType;
    private TutorialPhase tutorialPhase;
    private PlayerInventory beforeTutorialInventory;

    public void setTutorialType(TutorialType tutorialType) {
        this.tutorialType = tutorialType;
    }

    public void setTutorialPhase(TutorialPhase tutorialPhase) {
        this.tutorialPhase = tutorialPhase;
    }

    public void setBeforeTutorialInventory(PlayerInventory beforeTutorialInventory) {
        this.beforeTutorialInventory = beforeTutorialInventory;
    }

    public TutorialType getTutorialType() {
        return tutorialType;
    }

    public TutorialPhase getTutorialPhase() {
        return tutorialPhase;
    }

    public PlayerInventory getBeforeTutorialInventory() {
        return beforeTutorialInventory;
    }
}
