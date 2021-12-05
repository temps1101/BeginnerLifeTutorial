package beginnerlifetutorial.beginnerlifetutorial.utils;

import beginnerlifetutorial.beginnerlifetutorial.enums.TutorialPhase;
import beginnerlifetutorial.beginnerlifetutorial.enums.TutorialType;
import org.bukkit.inventory.PlayerInventory;

public class PlayerStatus {
    private TutorialType tutorialType;
    private TutorialPhase tutorialPhase;

    // 資源チュートリアル用
    private PlayerInventory beforeTutorialInventory;
    private double beforeTutorialBalance;

    public void setTutorialType(TutorialType tutorialType) {
        this.tutorialType = tutorialType;
    }

    public void setTutorialPhase(TutorialPhase tutorialPhase) {
        this.tutorialPhase = tutorialPhase;
    }

    public void setBeforeTutorialInventory(PlayerInventory beforeTutorialInventory) {
        this.beforeTutorialInventory = beforeTutorialInventory;
    }

    public void setBeforeTutorialBalance(double beforeTutorialBalance) {
        this.beforeTutorialBalance = beforeTutorialBalance;
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

    public double getBeforeTutorialBalance() {
        return beforeTutorialBalance;
    }
}
