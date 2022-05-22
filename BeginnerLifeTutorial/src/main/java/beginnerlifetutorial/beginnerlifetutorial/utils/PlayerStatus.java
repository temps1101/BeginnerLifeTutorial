package beginnerlifetutorial.beginnerlifetutorial.utils;

import beginnerlifetutorial.beginnerlifetutorial.enums.TutorialType;
import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.container.ArchivedJobs;
import com.gmail.nossr50.api.ExperienceAPI;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import static beginnerlifetutorial.beginnerlifetutorial.enums.TutorialType.RESOURCE;

public class PlayerStatus {
    @Setter @Getter
    private TutorialType tutorialType;
    @Setter @Getter
    private int tutorialPhase;
    @Setter @Getter
    private int tutorialCounter;

    @Getter
    private Inventory originalInventory;
    @Getter
    private int originalHerbalismXP;

    @Getter
    private ArchivedJobs archivedJobs;

    public PlayerStatus(Player player, TutorialType tutorialType) {
        this.tutorialType = tutorialType;
        tutorialPhase = 0;
        tutorialCounter = 0;
        originalInventory = player.getInventory();
        if (tutorialType == RESOURCE) {
            originalHerbalismXP = ExperienceAPI.getXP(player, "Herbalism");
            archivedJobs = Jobs.getPlayerManager().getJobsPlayer(player).getArchivedJobs();

        }
    }
}
