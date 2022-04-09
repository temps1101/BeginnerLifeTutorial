package beginnerlifetutorial.beginnerlifetutorial.utils;

import beginnerlifetutorial.beginnerlifetutorial.enums.TutorialPhase;
import beginnerlifetutorial.beginnerlifetutorial.enums.TutorialType;
import com.gamingmesh.jobs.container.JobProgression;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class PlayerStatus {
    private TutorialType tutorialType;
    private TutorialPhase tutorialPhase;

    // 資源チュートリアル用
    private ItemStack[] beforeTutorialInventoryContents;
    private int beforeTutorialHerbalismXP;
    private List<JobProgression> beforeTutorialJobsProgressions;
    private int dummyOntimePoint;

    public void setTutorialType(TutorialType tutorialType) {
        this.tutorialType = tutorialType;
    }

    public void setTutorialPhase(TutorialPhase tutorialPhase) {
        this.tutorialPhase = tutorialPhase;
    }

    public void setBeforeTutorialInventory(ItemStack[] beforeTutorialInventoryContents) {
        this.beforeTutorialInventoryContents = beforeTutorialInventoryContents;
    }

    public void setBeforeTutorialHerbalismXP(int beforeTutorialHerbalismXP) {
        this.beforeTutorialHerbalismXP = beforeTutorialHerbalismXP;
    }

    public void setBeforeTutorialJobsProgressions(List<JobProgression> beforeTutorialJobsProgressions) {
        this.beforeTutorialJobsProgressions = beforeTutorialJobsProgressions;
    }

    public void setDummyOntimePoint(int dummyOntimePoint) {
        this.dummyOntimePoint = dummyOntimePoint;
    }

    public TutorialType getTutorialType() {
        return tutorialType;
    }

    public TutorialPhase getTutorialPhase() {
        return tutorialPhase;
    }

    public ItemStack[] getBeforeTutorialInventory() {
        return beforeTutorialInventoryContents;
    }

    public int getBeforeTutorialHerbalismXP() {
        return beforeTutorialHerbalismXP;
    }

    public List<JobProgression> getBeforeTutorialJobsProgressions() {
        return beforeTutorialJobsProgressions;
    }

    public int getDummyOntimePoint() {
        return dummyOntimePoint;
    }
}
