package beginnerlifetutorial.beginnerlifetutorial.listeners;

import beginnerlifetutorial.beginnerlifetutorial.BeginnerLifeTutorial;
import beginnerlifetutorial.beginnerlifetutorial.enums.TutorialItems;
import beginnerlifetutorial.beginnerlifetutorial.events.StepTutorial;
import beginnerlifetutorial.beginnerlifetutorial.utils.Chat;
import beginnerlifetutorial.beginnerlifetutorial.utils.PlayerStatus;
import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.container.JobsPlayer;
import com.gmail.nossr50.api.ExperienceAPI;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static org.bukkit.Sound.*;

public class TutorialProcessor implements Listener {
    private FileConfiguration configuration;

    public TutorialProcessor(FileConfiguration fileConfiguration) {
        configuration = fileConfiguration;
    }

    @EventHandler
    public void onStepTutorial(StepTutorial event) {
        Player player = event.getPlayer();
        PlayerStatus playerStatus = BeginnerLifeTutorial.getPlayerStatus(player);

        switch (playerStatus.getTutorialType()) {
            case RESOURCE:
                switch (playerStatus.getTutorialPhase()) {
                    case 0:
                        player.teleport(configuration.getLocation("resourceLocation"));

                        Bukkit.getScheduler().runTaskLater(BeginnerLifeTutorial.getPlugin(), () -> {
                            player.playSound(player.getLocation(), ENTITY_PLAYER_LEVELUP, 1, 1);
                            player.sendTitle(Chat.f("&6資源チュートリアル", false), Chat.f("&6RESOURCE TUTORIAL", false), 10, 70, 20);

                            player.getInventory().clear();
                            ExperienceAPI.setXP(player, "Herbalism", 0);

                            JobsPlayer jobsPlayer = Jobs.getPlayerManager().getJobsPlayer(player);
                            jobsPlayer.leaveAllJobs();
                            jobsPlayer.joinJob(Jobs.getJob("Farmer"));
                        }, 10); // 0.5s遅延

                        Bukkit.getScheduler().runTaskLater(BeginnerLifeTutorial.getPlugin(), () -> {
                            stepTutorialWithButton(player, Chat.f("&6資源チュートリアルへようこそ！。", false), "次へ", 0);
                        }, 20*4); // 4s遅延

                        break;

                    case 1:
                        stepTutorialWithButton(player, Chat.f("&6このチュートリアルでは&aJobs&6、&aMCMMO&6、&a資源採取&6、&a釣り&6について説明します！", false), "次へ", 1);
                        break;
                    case 2:
                        stepTutorialWithButton(player, Chat.f("&6最初にクワをゲットして、小麦を収穫し、&aJobs&6と&aMCMMO&6について理解しましょう！", false), "クワをゲットする", 2);
                        break;

                    case 3:
                        player.playSound(player.getLocation(), ITEM_ARMOR_EQUIP_DIAMOND, 1, 1);
                        player.getInventory().setItemInMainHand(TutorialItems.RESOURCE_HOE.getItem());

                        Bukkit.getScheduler().runTaskLater(BeginnerLifeTutorial.getPlugin(), () -> {
                            player.sendMessage(Chat.f("&8==============================", false));
                            player.sendMessage(Chat.f("&6今、クワを一つ渡したのでそのクワを使って目の前にある小麦をいくつか収穫してみましょう！。&a収穫時に画面上部に注目しておいてください！", false));
                            player.sendMessage(Chat.f("&8==============================", false));
                            player.playSound(player.getLocation(), BLOCK_SCAFFOLDING_BREAK, 1, 1);
                        }, 20*2); // 2s遅延
                        break;

                    case 4:
                        Bukkit.getScheduler().runTaskLater(BeginnerLifeTutorial.getPlugin(), () -> {
                            stepTutorialWithButton(player, Chat.f("&6画面上部に緑色のバーで出たのが&aMCMMO&6のレベル、青色のバーで出たのが&aJobs&6のレベルです。", false), "次へ", 4);
                        }, 20*2); // 2s遅延

                        break;

                    case 5:
                        stepTutorialWithButton(player, Chat.f("&aMCMMO&6のレベルを増やすと使える&aスキル&6が増え、&ajobs&6のレベルを増やすともらえる&aお金&6が増えます。", false), "次へ", 5);
                        break;

                    case 6:
                        stepTutorialWithButton(player, Chat.f("&6&aスキル&6はプレイ中に使える技、&ajobs&6でもらえるお金はは土地やアイテムを買うときの通貨だと思ってください。", false), "次へ", 6);
                        break;

                    case 7:
                        stepTutorialWithButton(player, Chat.f("&6&aスキル&6についてもう少し説明します。", false), "次へ", 7);
                        break;

                    case 8:
                        stepTutorialWithButton(player, Chat.f("&6&aスキル&6は、&aそのスキルと紐づけられているアイテム&6（攻撃力増加だったら剣、鉱物ドロップ増加だったらツルハシ、etc.）をしゃがみながらクリックすることで発動し、一定期間使えるようになります。", false), "次へ", 8);
                        break;

                    case 9:
                        stepTutorialWithButton(player, Chat.f("&6今回は、農作物の収穫量を増やし、収穫したら自動でタネを植えてくれる「&2&lGREEN TERRA&r&6」というスキルを使ってみましょう。", false), "次へ", 9);
                        break;

                    case 10:
                        stepTutorialWithButton(player, Chat.f("&6クワを持ち、しゃがみながら右クリックをすると、『&3You &6ready &3your Hoe.&6』と表示されます。これでスキルが使えるモードに入ります。ちなみに5秒ほどでこのモードはオフになるので、オフになった場合は再度しゃがみながら右クリックをしてください。", false), "次へ", 10);
                        break;

                    case 11:
                        player.sendMessage(Chat.f("&8==============================", false));
                        player.sendMessage(Chat.f("&6スキルが使えるモードになっている間に作物を収穫し始めると、『&2**GREEN TERRA ACTIVATED**&6』と表示され、一定期間スキルが使えるようになります。では実際にスキルを発動して、小麦を収穫してみてください。", false));
                        player.sendMessage(Chat.f("&8==============================", false));
                        player.playSound(player.getLocation(), BLOCK_SCAFFOLDING_BREAK, 1, 1);
                        break;

                    case 12:
                        stepTutorialWithButton(player, Chat.f("&6スキルが使用できましたね！どんどんレベルを上げて、より強いスキルを使えるようになりましょう！", false), "次へ", 12);
                        break;

                    case 13:
                        stepTutorialWithButton(player, Chat.f("&6クワ以外にもこのサーバーにはたくさんのスキルがあります。https://azisabaofficial.playing.wiki/d/MCMMOここで確認してみてください。", false), "次へ", 13);
                        break;

                    case 14:
                        stepTutorialWithButton(player, Chat.f("&6次は地下で&a資源採取&6の体験をしてみましょう！", false), "洞窟へ移動する", 14);
                        break;

                    case 15:
                        player.getInventory().clear();
                        player.teleport(configuration.getLocation("resourceCaveLocation"));
                        Bukkit.getScheduler().runTaskLater(BeginnerLifeTutorial.getPlugin(), () -> {
                            player.sendMessage(Chat.f("&a洞窟前にテレポートさせました。", false));
                            player.playSound(player.getLocation(), ENTITY_PLAYER_LEVELUP, 1, 1);
                        }, 10); // 0.5s遅延

                        Bukkit.getScheduler().runTaskLater(BeginnerLifeTutorial.getPlugin(), () -> {
                            stepTutorialWithButton(player, Chat.f("&6目の前の村人から&aオンタイムチケット&6を使ってツルハシを購入し、資源採掘をしてみましょう！。", false), "次へ", 15);
                        }, 20*4); // 4s遅延

                    case 16:
                        stepTutorialWithButton(player, Chat.f("&6まず、&aオンタイムチケット&6についての説明をします。", false), "次へ", 16);
                        break;

                    case 17:
                        stepTutorialWithButton(player, Chat.f("&aオンタイムチケット&6とはアジ鯖内のほとんどのサーバーで使用できるポイントのことです。ポイントはアジ鯖にいる際に定期的に付与されます。", false), "次へ", 17);
                        break;

                    case 18:
                        stepTutorialWithButton(player, Chat.f("&6オンタイムポイントは、&a/ott&6コマンドでオンタイムチケットに、インベントリー内のオンタイムチケットは&a/tto&6コマンドでオンタイムポイントに変換できます。", false), "次へ", 18);
                        break;

                    case 19:
                        stepTutorialWithButton(player, Chat.f("&6オンタイムチケットはLife鯖はもちろん、他のサーバーでもアイテム購入や取引に使えます。", false), "次へ", 19);
                        break;

                    case 20:
                        player.playSound(player.getLocation(), BLOCK_SCAFFOLDING_BREAK, 1, 1);
                        player.sendMessage(Chat.f("&8==============================", false));
                        player.sendMessage(Chat.f("&6では実際にオンタイムチケットを&a/ott&6コマンドで入手してみてください！", false));
                        player.sendMessage(Chat.f("&8==============================", false));
                        break;

                    case 21:
                        Bukkit.getScheduler().runTaskLater(BeginnerLifeTutorial.getPlugin(), () -> {
                            player.playSound(player.getLocation(), BLOCK_SCAFFOLDING_BREAK, 1, 1);
                            player.sendMessage(Chat.f("&8==============================", false));
                            player.sendMessage(Chat.f("&6では、今入手したオンタイムチケットで目の前にいる村人とツルハシを交換してみましょう！", false));
                            player.sendMessage(Chat.f("&8==============================", false));
                        }, 20*2); // 2s遅延
                        break;

                    case 22:
                        Bukkit.getScheduler().runTaskLater(BeginnerLifeTutorial.getPlugin(), () -> {
                            player.playSound(player.getLocation(), BLOCK_SCAFFOLDING_BREAK, 1, 1);
                            player.sendMessage(Chat.f("&8==============================", false));
                            player.sendMessage(Chat.f("&6購入しましたね！では、目の前の資源を採掘しましょう！", false));
                            player.sendMessage(Chat.f("&8==============================", false));
                        }, 10); // 0.5s遅延
                        break;

                    case 23:
                        player.sendMessage(Chat.f("&6[MythicMobs] &aItem &bsigen_ticket &awas put in your inventory!", false));
                        player.sendMessage(Chat.f("チケットを掘り当てた", false));
                        player.getInventory().addItem(TutorialItems.SIGEN_TICKET.getItem());

                        Bukkit.getScheduler().runTaskLater(BeginnerLifeTutorial.getPlugin(), () -> {
                            stepTutorialWithButton(player, Chat.f("&6このように、資源ワールドで資源をを集めていると一定確率で資源チケットを入手できます。", false), "次へ", 23);
                        }, 10); // 0.5s遅延
                        break;

                    case 24:
                        stepTutorialWithButton(player, Chat.f("&6次は地下を出て海に行きましょう", false), "海に行く", 24);
                        break;

                    case 25:
                        player.getInventory().clear();
                        player.teleport(configuration.getLocation("resourceSeaLocation"));
                        Bukkit.getScheduler().runTaskLater(BeginnerLifeTutorial.getPlugin(), () -> {
                            player.sendMessage(Chat.f("&a海にテレポートさせました。", false));
                            player.playSound(player.getLocation(), ENTITY_PLAYER_LEVELUP, 1, 1);
                        }, 10); // 0.5s遅延

                        Bukkit.getScheduler().runTaskLater(BeginnerLifeTutorial.getPlugin(), () -> {
                            stepTutorialWithButton(player, Chat.f("&6ではここで&aダイヤロッド&6で釣りをしてみましょう。", false), "次へ", 25);
                        }, 20*4); // 4s遅延

                        break;

                    case 26:
                        stepTutorialWithButton(player, Chat.f("&6Life鯖ではでは特殊な釣竿を使うことで&aダイヤ&6などを入手することができます。", false), "次へ", 26);
                        break;

                    case 27:
                        player.playSound(player.getLocation(), BLOCK_SCAFFOLDING_BREAK, 1, 1);
                        player.sendMessage(Chat.f("&8==============================", false));
                        player.sendMessage(Chat.f("&6では実際にその釣竿で海で釣りをし、ダイヤを釣り当ててみましょう！", false));
                        player.sendMessage(Chat.f("&8==============================", false));
                        break;

                    case 28:
                        Bukkit.getScheduler().runTaskLater(BeginnerLifeTutorial.getPlugin(), () -> {
                            stepTutorialWithButton(player, Chat.f("&6ダイヤを釣り当てられましたね！", false), "次へ", 28);
                        }, 20*2); // 2s遅延
                        break;

                    case 29:
                        stepTutorialWithButton(player, Chat.f("&6他にもLife鯖にはたくさんの追加要素があります。わからないことがありましたら、wiki（https://azisabaofficial.playing.wiki）を読んでみたり、[Admin]や[Mod]マークが名前についている運営に気軽に質問してみてください！", false), "次へ", 29);
                        break;

                    case 30:
                        stepTutorialWithButton(player, Chat.f("&6以上で資源チュートリアルを終了します！お疲れ様でした！", false), "三重塔に戻る", 30);
                        break;

                    case 31:
                        player.teleport(configuration.getLocation("TutorialHomeLocation"));

                        ExperienceAPI.setXP(player, "Herbalism", playerStatus.getOriginalHerbalismXP());
                        JobsPlayer jobsPlayer = Jobs.getPlayerManager().getJobsPlayer(player);
                        jobsPlayer.setArchivedJobs(playerStatus.getArchivedJobs());
                        player.getInventory().setContents(playerStatus.getOriginalInventory().getContents());

                        Bukkit.getScheduler().runTaskLater(BeginnerLifeTutorial.getPlugin(), () -> {
                            player.playSound(player.getLocation(), ENTITY_PLAYER_LEVELUP, 1, 1);
                            player.sendTitle(Chat.f("&6チュートリアル", false), Chat.f("&6TUTORIAL", false), 10, 70, 20);
                            BeginnerLifeTutorial.removePlayerStatus(player);
                        }, 10); // 0.5s遅延

                        break;
                }
                break;
            case SHOPMONEY:
                switch (playerStatus.getTutorialPhase()) {
                    case 0:
                        player.teleport(configuration.getLocation("shopMoneyLocation"));

                        Bukkit.getScheduler().runTaskLater(BeginnerLifeTutorial.getPlugin(), () -> {
                            player.playSound(player.getLocation(), ENTITY_PLAYER_LEVELUP, 1, 1);
                            player.sendTitle(Chat.f("&6Shop＆Moneyチュートリアル", false), Chat.f("&6SHOP＆MONEY TUTORIAL", false), 10, 70, 20);
                        }, 10); // 0.5s遅延

                        Bukkit.getScheduler().runTaskLater(BeginnerLifeTutorial.getPlugin(), () -> {
                            stepTutorialWithButton(player, Chat.f("&6Shop＆＆Moneyチュートリアルへようこそ！。", false), "次へ", 0);
                        }, 20*4); // 4s遅延

                        break;

                    case 1:
                        stepTutorialWithButton(player, Chat.f("&6Life鯖では自分でお店を開いてお金を稼いだり、稼いだお金で新しいものを買ったりすることができます。", false), "次へ", 1);
                        break;

                    case 2:
                        stepTutorialWithButton(player, Chat.f("&6所持金は&a/money&6で確認できるので、コマンドを打って確認してみてください。", false), "次へ", 2);
                        break;

                    case 3:
                        stepTutorialWithButton(player, Chat.f("&6$10になっていたと思います。チュートリアル終了後、元の金額に戻るのでご安心ください。", false), "次へ", 3);
                        break;

                    case 4:
                        stepTutorialWithButton(player, Chat.f("&6次にshopの説明をします。", false), "次へ", 4);
                        break;

                    case 5:
                        stepTutorialWithButton(player, Chat.f("&6街にあるshopではアイテムの売買ができます。まず、", false), "次へ", 4);
                }
            case DUNGEON:
            case RAID:
        }
    }

    private void stepTutorialWithButton(Player player, String mainMessage, String buttonLabel, int currentPhase) {
        TextComponent button = new TextComponent(Chat.f("&a&l[{0}]", false, buttonLabel));
        button.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/stepTutorial caravan" + " " + currentPhase));

        player.sendMessage(Chat.f("&8==============================", false));
        player.sendMessage(mainMessage);
        player.spigot().sendMessage(button);
        player.sendMessage(Chat.f("&8==============================", false));
        player.playSound(player.getLocation(), BLOCK_SCAFFOLDING_BREAK, 1, 1);
    }
}
