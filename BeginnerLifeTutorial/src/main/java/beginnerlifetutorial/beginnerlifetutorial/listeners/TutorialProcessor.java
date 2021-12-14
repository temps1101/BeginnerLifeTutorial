package beginnerlifetutorial.beginnerlifetutorial.listeners;

import beginnerlifetutorial.beginnerlifetutorial.BeginnerLifeTutorial;
import beginnerlifetutorial.beginnerlifetutorial.enums.TutorialType;
import beginnerlifetutorial.beginnerlifetutorial.events.TutorialStepEvent;
import beginnerlifetutorial.beginnerlifetutorial.utils.Chat;
import beginnerlifetutorial.beginnerlifetutorial.utils.ItemUtil;
import beginnerlifetutorial.beginnerlifetutorial.utils.PlayerStatus;
import beginnerlifetutorial.beginnerlifetutorial.utils.TutorialConfig;
import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.container.JobsPlayer;
import com.gmail.nossr50.api.ExperienceAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static beginnerlifetutorial.beginnerlifetutorial.enums.TutorialPhase.*;
import static beginnerlifetutorial.beginnerlifetutorial.enums.TutorialType.RESOURCE;
import static org.bukkit.Sound.ENTITY_PLAYER_LEVELUP;

public class TutorialProcessor implements Listener {
    @EventHandler
    public void onTutorialStep(TutorialStepEvent event) {
        Player player = event.getPlayer();
        PlayerStatus playerStatus = BeginnerLifeTutorial.getPlayerCache().get(player);

        switch (playerStatus.getTutorialType()) {
            case RESOURCE:
                switch (playerStatus.getTutorialPhase()) {
                    case START:
                        // 資源チュートリアル開始
                        startProcess(player, RESOURCE);

                        playerStatus.setBeforeTutorialHerbalismXP(ExperienceAPI.getXP(player, "Herbalism"));
                        ExperienceAPI.setXP(player, "Herbalism", 0);

                        JobsPlayer jobsPlayer = Jobs.getPlayerManager().getJobsPlayer(player);
                        playerStatus.setBeforeTutorialJobsProgressions(jobsPlayer.getJobProgression());

                        jobsPlayer.leaveAllJobs();
                        jobsPlayer.joinJob(Jobs.getJob("Farmer"));

                        playerStatus.setBeforeTutorialInventory(player.getInventory().getContents());
                        player.getInventory().clear();

                        BeginnerLifeTutorial.getPlayerCache().replace(player, playerStatus);

                        Chat.fancySend(player, true, 4, Chat.f("&6ここは資源ワールドです。", false));
                        Chat.fancySend(player, true, 8, Chat.f("&6まずは今入手した桑で目の前の小麦を収穫してみましょう。", false));

                        Bukkit.getScheduler().runTaskLater(BeginnerLifeTutorial.getPlugin(), () -> {
                            player.getInventory().setItemInMainHand(ItemUtil.createItem(Material.DIAMOND_HOE, "&6チュートリアル用クワ", 1, "&fチュートリアル用に作られた桑", "&f持っていたアイテムはチュートリアル終了後に返却されます。"));
                        }, 20*8);

                        Chat.fancySend(player, true, 10, Chat.f("&6桑で小麦を右クリックすると小麦を回収できます。", false));

                        Bukkit.getScheduler().runTaskLater(BeginnerLifeTutorial.getPlugin(), () -> {
                            playerStatus.setTutorialPhase(WAITING_WHEAT_HARVESTED);
                            BeginnerLifeTutorial.getPlayerCache().replace(player, playerStatus);
                        }, 20*10);

                        break;

                    case WHEAT_HARVESTED:
                        Chat.fancySend(player, true, 0, Chat.f("&6画面上部に緑色のバーで出たのがMCMMOのレベル、青色のバーで出たのがjobsのレベルです。", false));
                        Chat.fancySend(player, true, 4, Chat.f("&6MCMMOのレベルを増やすと使えるスキルが増え、jobsのレベルを増やすともらえるお金が増えます。", false));
                        Chat.fancySend(player, true, 8, Chat.f("&6次は桑のスキルを使って収穫してみましょう。", false));
                        Chat.fancySend(player, true, 12, Chat.f("&6桑をしゃがみながら一度右クリックした後、『&3You &6ready &3your Hoe.&6』と出たのを確認した後、小麦を左クリックで収穫すると、『&2**GREEN TERRA ACTIVATED**&6』と表示され、スキルが発動されます。", false));
                        Chat.fancySend(player, true, 15, Chat.f("&6このスキルを使って収穫することで、一定期間の間、収穫量を増やしたり、自動でタネを植え直したりできます。", false));
                        Bukkit.getScheduler().runTaskLater(BeginnerLifeTutorial.getPlugin(), () -> {
                            playerStatus.setTutorialPhase(WAITING_GREEN_TERRA_FINISHED);
                            BeginnerLifeTutorial.getPlayerCache().replace(player, playerStatus);
                        }, 20*15);

                        break;

                    case GREEN_TERRA_FINISHED:
                        Chat.fancySend(player, true, 3, Chat.f("&6スキルが使用できましたね！", false));
                        Chat.fancySend(player, true, 4, Chat.f("&6桑以外にもこのサーバーにはたくさんのスキルがあります。https://wikiここで確認してみてください。", false));
                }

                break;

            case SHOPMONEY:
                break;

            case DUNGEON:
                break;

            case RAID:
                break;

            default:
                return;
        }
    }

    private void startProcess(Player player, TutorialType tutorialType) {
        String japaneseTitle;
        String englishTitle;

        // テレポート
        switch (tutorialType) {
            case RESOURCE:
                player.teleport(TutorialConfig.getResourceLocation());
                japaneseTitle = "&6資源チュートリアル";
                englishTitle = "&6RESOURCE TUTORIAL";
                break;

            case SHOPMONEY:
                player.teleport(TutorialConfig.getShopMoneyLocation());
                japaneseTitle = "&6Shop、Moneyチュートリアル";
                englishTitle = "&6Shop＆Money TUTORIAL";
                break;

            case DUNGEON:
                player.teleport(TutorialConfig.getDungeonLocation());
                japaneseTitle = "&6ダンジョンチュートリアル";
                englishTitle = "&6DUNGEON TUTORIAL";
                break;

            case RAID:
                player.teleport(TutorialConfig.getRaidLocation());
                japaneseTitle = "&6レイドチュートリアル";
                englishTitle = "&6RAID TUTORIAL";
                break;

            default:
                japaneseTitle = "";
                englishTitle = "";
        }

        player.playSound(player.getLocation(), ENTITY_PLAYER_LEVELUP, 1, 1);
        player.sendTitle(Chat.f(japaneseTitle, false), Chat.f(englishTitle, false), 10, 70, 20);
    }
}
