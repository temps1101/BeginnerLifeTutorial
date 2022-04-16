package beginnerlifetutorial.beginnerlifetutorial.listeners;

import beginnerlifetutorial.beginnerlifetutorial.BeginnerLifeTutorial;
import beginnerlifetutorial.beginnerlifetutorial.enums.TutorialPhase;
import beginnerlifetutorial.beginnerlifetutorial.enums.TutorialType;
import beginnerlifetutorial.beginnerlifetutorial.events.TutorialStepEvent;
import beginnerlifetutorial.beginnerlifetutorial.utils.Chat;
import beginnerlifetutorial.beginnerlifetutorial.utils.ItemCreator;
import beginnerlifetutorial.beginnerlifetutorial.utils.PlayerStatus;
import beginnerlifetutorial.beginnerlifetutorial.utils.TutorialConfig;
import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.container.JobsPlayer;
import com.gmail.nossr50.api.ExperienceAPI;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;

import static beginnerlifetutorial.beginnerlifetutorial.enums.TutorialPhase.*;
import static org.bukkit.Sound.*;

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
                        playerStatus.setBeforeTutorialHerbalismXP(ExperienceAPI.getXP(player, "Herbalism"));
                        ExperienceAPI.setXP(player, "Herbalism", 0);

                        JobsPlayer jobsPlayer = Jobs.getPlayerManager().getJobsPlayer(player);
                        playerStatus.setBeforeTutorialJobsProgressions(jobsPlayer.getJobProgression());

                        jobsPlayer.leaveAllJobs();
                        jobsPlayer.joinJob(Jobs.getJob("Farmer"));

                        playerStatus.setBeforeTutorialInventory(player.getInventory().getContents());
                        player.getInventory().clear();

                        playerStatus.setDummyOntimePoint(100);

                        BeginnerLifeTutorial.getPlayerCache().replace(player, playerStatus);


                        player.teleport(TutorialConfig.getResourceLocation());
                        player.playSound(player.getLocation(), ENTITY_PLAYER_LEVELUP, 1, 1);
                        player.sendTitle(Chat.f("&6資源チュートリアル", false), Chat.f("&6RESOURCE TUTORIAL", false), 10, 70, 20);

                        Bukkit.getScheduler().runTaskLater(BeginnerLifeTutorial.getPlugin(), () -> {
                            stepTutorialWithButton(player, Chat.f("&6ここは資源ワールドです。", false), "次へ", START, NEXT_MESSAGE_PRESSED_1);
                        }, 20*4); // 4s遅延

                        break;

                    case NEXT_MESSAGE_PRESSED_1:
                        stepTutorialWithButton(player, Chat.f("&6まずは桑を入手し、目の前の小麦を収穫してみましょう。", false), "桑を入手する", NEXT_MESSAGE_PRESSED_1, HOE_BUTTON_PRESSED);
                        break;

                    case HOE_BUTTON_PRESSED:
                        player.getInventory().setItemInMainHand(ItemCreator.start().material(Material.DIAMOND_HOE).name("&6チュートリアル用クワ").unbreakable(true).lore("&fチュートリアル用に作られた桑").count(1).flags(ItemFlag.HIDE_UNBREAKABLE).create());
                        player.playSound(player.getLocation(), ITEM_ARMOR_EQUIP_DIAMOND, 1, 1);

                        playerStatus.setTutorialPhase(WAITING_WHEAT_HARVESTED);
                        BeginnerLifeTutorial.getPlayerCache().replace(player, playerStatus);

                        player.sendMessage(Chat.f("&6収穫してみてください！", false));

                        break;

                    case WHEAT_HARVESTED:
                        Bukkit.getScheduler().runTaskLater(BeginnerLifeTutorial.getPlugin(), () -> {
                            stepTutorialWithButton(player, Chat.f("&6画面上部に緑色のバーで出たのがMCMMOのレベル、青色のバーで出たのがjobsのレベルです。", false), "次へ", WHEAT_HARVESTED, NEXT_MESSAGE_PRESSED_2);
                        }, 20*2); // 2s遅延

                        break;

                    case NEXT_MESSAGE_PRESSED_2:
                        stepTutorialWithButton(player, Chat.f("&6MCMMOのレベルを増やすと使えるスキルが増え、jobsのレベルを増やすともらえるお金が増えます。", false), "次へ", NEXT_MESSAGE_PRESSED_2, NEXT_MESSAGE_PRESSED_3);
                        break;

                    case NEXT_MESSAGE_PRESSED_3:
                        stepTutorialWithButton(player, Chat.f("&6次は桑のスキルを使って収穫してみましょう。", false), "次へ", NEXT_MESSAGE_PRESSED_3, NEXT_MESSAGE_PRESSED_4);
                        break;

                    case NEXT_MESSAGE_PRESSED_4:
                        stepTutorialWithButton(player, Chat.f("&6桑のスキルを発動させると、一定期間の間収穫量が増え、自動でタネを植え直してくれます。", false), "次へ", NEXT_MESSAGE_PRESSED_4, NEXT_MESSAGE_PRESSED_5);
                        break;

                    case NEXT_MESSAGE_PRESSED_5:
                        stepTutorialWithButton(player, Chat.f("&6桑を一度右クリックした後、『&3You &6ready &3your Hoe.&6』と出たのを確認した後5秒以内に小麦を左クリックで収穫すると、『&2**GREEN TERRA ACTIVATED**&6』と表示され、スキルが発動されます。", false), "次へ", NEXT_MESSAGE_PRESSED_5, NEXT_MESSAGE_PRESSED_6);
                        break;

                    case NEXT_MESSAGE_PRESSED_6:
                        stepTutorialWithButton(player, Chat.f("&6ではスキルを使って収穫しましょう！", false), "スキルを使って収穫", NEXT_MESSAGE_PRESSED_6, START_SKILL);
                        break;

                    case START_SKILL:
                        playerStatus.setTutorialPhase(WAITING_GREEN_TERRA_FINISHED);
                        BeginnerLifeTutorial.getPlayerCache().replace(player, playerStatus);
                        player.sendMessage(Chat.f("&6収穫してみてください！", false));
                        player.playSound(player.getLocation(), BLOCK_SCAFFOLDING_BREAK, 1, 1);
                        break;

                    case GREEN_TERRA_FINISHED:
                        stepTutorialWithButton(player, Chat.f("&6スキルが使用できましたね！", false), "次へ", GREEN_TERRA_FINISHED, NEXT_MESSAGE_PRESSED_7);
                        break;

                    case NEXT_MESSAGE_PRESSED_7:
                        stepTutorialWithButton(player, Chat.f("&6桑以外にもこのサーバーにはたくさんのスキルがあります。https://azisabaofficial.playing.wiki/d/MCMMOここで確認してみてください。", false), "次へ", NEXT_MESSAGE_PRESSED_7, NEXT_MESSAGE_PRESSED_8);
                        break;

                    case NEXT_MESSAGE_PRESSED_8:
                        stepTutorialWithButton(player, Chat.f("&6次は地下で採掘を体験してみましょう。", false), "洞窟へ移動する", NEXT_MESSAGE_PRESSED_8, TP_CAVE);
                        break;

                    case TP_CAVE:
                        player.getInventory().clear();
                        player.teleport(TutorialConfig.getResourceCaveLocation());
                        player.sendMessage(Chat.f("&6洞窟前にテレポートさせました。", false));
                        player.playSound(player.getLocation(), ENTITY_PLAYER_LEVELUP, 1, 1);

                        Bukkit.getScheduler().runTaskLater(BeginnerLifeTutorial.getPlugin(), () -> {
                            stepTutorialWithButton(player, Chat.f("&6では、目の前の村人から&aオンタイムチケット&6を使ってツルハシを購入しましょう。", false), "次へ", TP_CAVE, NEXT_MESSAGE_PRESSED_9);
                        }, 20*4); // 4s遅延

                    case NEXT_MESSAGE_PRESSED_9:
                        stepTutorialWithButton(player, Chat.f("&6まず、オンタイムチケットについての説明をします。", false), "次へ", NEXT_MESSAGE_PRESSED_9, NEXT_MESSAGE_PRESSED_10);
                        break;

                    case NEXT_MESSAGE_PRESSED_10:
                        stepTutorialWithButton(player, Chat.f("&6オンタイムチケットとはアジ鯖内のほとんどのサーバーで使用できるポイントのことです。ポイントはアジ鯖にいる際に定期的に付与されます。", false), "次へ", NEXT_MESSAGE_PRESSED_10, NEXT_MESSAGE_PRESSED_11);
                        break;

                    case NEXT_MESSAGE_PRESSED_11:
                        stepTutorialWithButton(player, Chat.f("&6オンタイムポイントは、&a/ott&6コマンドでオンタイムチケットに、インベントリー内のオンタイムチケットは&a/tto&6コマンドでオンタイムポイントに変換できます。", false), "次へ", NEXT_MESSAGE_PRESSED_11, NEXT_MESSAGE_PRESSED_12);
                        break;

                    case NEXT_MESSAGE_PRESSED_12:
                        stepTutorialWithButton(player, Chat.f("&6オンタイムチケットはLife鯖はもちろん、他のサーバーでもアイテム購入や取引に使えます。", false), "次へ", NEXT_MESSAGE_PRESSED_12, NEXT_MESSAGE_PRESSED_13);
                        break;

                    case NEXT_MESSAGE_PRESSED_13:
                        stepTutorialWithButton(player, Chat.f("&6では実際にオンタイムチケットを&a/ott&6コマンドで入手してみてください！", false), "OK", NEXT_MESSAGE_PRESSED_13, NEXT_MESSAGE_PRESSED_14);

                    case NEXT_MESSAGE_PRESSED_14:
                        player.playSound(player.getLocation(), BLOCK_SCAFFOLDING_BREAK, 1, 1);
                        player.sendMessage(Chat.f("&6どうぞ！", false));

                    case OTT_GAVE:
                        player.playSound(player.getLocation(), BLOCK_SCAFFOLDING_BREAK, 1, 1);
                        player.sendMessage(Chat.f("&8==============================", false));
                        player.sendMessage(Chat.f("&6では、今入手したオンタイムチケットで目の前にいる村人とツルハシを交換してみましょう！", false));
                        player.sendMessage(Chat.f("&8==============================", false));

                    case PICKAXE_BOUGHT:
                        player.sendMessage(Chat.f("&8==============================", false));
                        player.sendMessage(Chat.f("購入しましたね！では、目の前の資源を採掘しましょう！", false));
                        player.sendMessage(Chat.f("&8==============================", false));
                        if (playerStatus.getMineAmount() == 10) {
                            player.sendMessage(Chat.f("&8==============================", false));
                            player.sendMessage(Chat.f("資源ワールドで採掘をしていると、一定確率で資源チケットを入手できます。", false));
                            player.sendMessage(Chat.f("&8==============================", false));
                        }
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

    private void stepTutorialWithButton(Player player, String mainMessage, String buttonLabel, TutorialPhase currentPhase, TutorialPhase nextPhase) {
        TextComponent button = new TextComponent(Chat.f("&a&l[{0}]", false, buttonLabel));
        button.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ltutorialNavigator caravan" + " " + currentPhase.toString() +  " " + nextPhase.toString()));

        player.sendMessage(Chat.f("&8==============================", false));
        player.sendMessage(mainMessage);
        player.spigot().sendMessage(button);
        player.sendMessage(Chat.f("&8==============================", false));
        player.playSound(player.getLocation(), BLOCK_SCAFFOLDING_BREAK, 1, 1);
    }
}
