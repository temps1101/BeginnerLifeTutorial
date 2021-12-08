package beginnerlifetutorial.beginnerlifetutorial.commands;

import beginnerlifetutorial.beginnerlifetutorial.BeginnerLifeTutorial;
import beginnerlifetutorial.beginnerlifetutorial.utils.Chat;
import beginnerlifetutorial.beginnerlifetutorial.utils.PlayerStatus;
import beginnerlifetutorial.beginnerlifetutorial.utils.TutorialConfig;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


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
                            player.sendMessage(Chat.f("&6あんたが持ってた所持金：{0}", true, String.valueOf(playerStatus.getBeforeTutorialBalance())));
                            player.sendMessage(Chat.f("&6あんたのチュートリアルタイプ：{0}", true, playerStatus.getTutorialType().name()));
                            player.sendMessage(Chat.f("&6あんたのチュートリアルフェーズ：{0}", true, playerStatus.getTutorialPhase().name()));
                        } else {
                            player.sendMessage(Chat.f("&6未登録＝チュートリアル始めよう", true));
                        }
                    } else {
                        sender.sendMessage(Chat.f("&6プレイヤーになろう。", true));
                    }

                    return true;

                case "drawback":
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        PlayerStatus playerStatus = BeginnerLifeTutorial.getPlayerCache().get(player);

                        if (playerStatus != null) {
                            player.getInventory().setContents(playerStatus.getBeforeTutorialInventory());
                            player.sendMessage(Chat.f("&6キャッシュアイテムをロルバしたよん", true));

                            setBalance(player, playerStatus.getBeforeTutorialBalance());
                        } else {
                            player.sendMessage(Chat.f("&6未登録＝チュートリアル始めよう", true));
                        }
                    } else {
                        sender.sendMessage(Chat.f("&6プレイヤーになろう。", true));
                    }

                default:
                    sender.sendMessage(Chat.f("&6/ltutorialAdmin reload", true));
                    sender.sendMessage(Chat.f("&6/ltutorialAdmin status", true));
                    return true;
            }
        }
        return true;
    }

    private void setBalance(Player player, double balance) {
        Economy economy = BeginnerLifeTutorial.getEconomy();

        double currentBalance = economy.getBalance(player);
        economy.withdrawPlayer(player, currentBalance);
        economy.depositPlayer(player, balance);
    }
}