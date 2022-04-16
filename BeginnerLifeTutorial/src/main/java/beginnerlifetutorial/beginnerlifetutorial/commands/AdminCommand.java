package beginnerlifetutorial.beginnerlifetutorial.commands;

import beginnerlifetutorial.beginnerlifetutorial.BeginnerLifeTutorial;
import beginnerlifetutorial.beginnerlifetutorial.enums.TutorialItems;
import beginnerlifetutorial.beginnerlifetutorial.utils.Chat;
import beginnerlifetutorial.beginnerlifetutorial.utils.ItemCreator;
import beginnerlifetutorial.beginnerlifetutorial.utils.PlayerStatus;
import beginnerlifetutorial.beginnerlifetutorial.utils.TutorialConfig;
import com.gamingmesh.jobs.Jobs;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;


public class AdminCommand implements CommandExecutor {
    private BeginnerLifeTutorial plugin;
    public AdminCommand(BeginnerLifeTutorial plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(Chat.f("&6ロビーチュートリアルプラグインv{0}", true, plugin.getDescription().getVersion()));
        } else if (args.length == 1) {
            switch (args[0]) {
                case "reload":
                    plugin.reloadConfig();
                    TutorialConfig.load(plugin.getConfig());
                    sender.sendMessage(Chat.f("&6リロード完了！", true));
                    return true;


                case "status":
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        PlayerStatus playerStatus = BeginnerLifeTutorial.getPlayerCache().get(player);

                        if (playerStatus != null) {
                            player.sendMessage(Chat.f("&6あんたのチュートリアルタイプ：{0}", true, playerStatus.getTutorialType().name()));
                            player.sendMessage(Chat.f("&6あんたのチュートリアルフェーズ：{0}", true, playerStatus.getTutorialPhase().name()));
                        } else {
                            player.sendMessage(Chat.f("&6未登録＝チュートリアル始めよう", true));
                        }
                    } else {
                        sender.sendMessage(Chat.f("&6プレイヤーになろう。", true));
                    }

                    return true;

                case "rollback":
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        PlayerStatus playerStatus = BeginnerLifeTutorial.getPlayerCache().get(player);

                        if (playerStatus != null) {
                            player.getInventory().setContents(playerStatus.getBeforeTutorialInventory());
                            player.sendMessage(Chat.f("&6キャッシュアイテムをロルバしたよん", true));

                        } else {
                            player.sendMessage(Chat.f("&6未登録＝チュートリアル始めよう", true));
                        }
                    } else {
                        sender.sendMessage(Chat.f("&6プレイヤーになろう。", true));
                    }

                    return true;

                case "jobs":
                    Jobs.getJobs().forEach(jobs -> sender.sendMessage(jobs.getName()));

                case "ott":
                    if (sender instanceof Player) {
                        Player player = (Player) sender;

                        player.getInventory().addItem(ItemCreator.start()
                                .material(Material.PAPER)
                                .count(1)
                                .name("&aオンタイムチケット")
                                .lore("ログインしていると一定時間ごとにもらえる。")
                                .lore("換金、特殊アイテムとの交換等に使える。")
                                .unbreakable(true)
                                .enchantment(Enchantment.MENDING, 1)
                                .flags(ItemFlag.HIDE_ENCHANTS)
                                .flags(ItemFlag.HIDE_DESTROYS)
                                .create());
                    } else {
                        sender.sendMessage(Chat.f("&6プレイヤーになろう。", true));
                    }

                    return true;

                case "tutorialpickaxe":
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        player.getInventory().addItem(TutorialItems.getTutorialPickaxe());
                    } else {
                        sender.sendMessage(Chat.f("&6プレイヤーになろう。", true));
                    }

                    return true;

                default:
                    sender.sendMessage(Chat.f("&6/ltutorialAdmin reload", true));
                    sender.sendMessage(Chat.f("&6/ltutorialAdmin status", true));
                    sender.sendMessage(Chat.f("&6/ltutorialAdmin rollback", true));
                    return true;
            }
        }
        return true;
    }
}