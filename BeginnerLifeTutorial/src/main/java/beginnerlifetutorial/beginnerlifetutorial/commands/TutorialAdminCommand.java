package beginnerlifetutorial.beginnerlifetutorial.commands;

import beginnerlifetutorial.beginnerlifetutorial.enums.TutorialItems;
import beginnerlifetutorial.beginnerlifetutorial.utils.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TutorialAdminCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("tutorial.permission.admin")) {
            sender.sendMessage(Chat.f("権限不足です！", true));
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(Chat.f("githubのREADME.mdにコマンドの使い方が載っています！", true));
            return true;
        }

        switch (args[0]) {
            case "get":
                if (args.length != 2) {
                    sender.sendMessage(Chat.f("&c構文が違います。githubのREADME.mdにコマンドの使い方が載っています！", true));
                    return true;
                }

                if (!(sender instanceof Player)) {
                    sender.sendMessage(Chat.f("&cこのコマンドはプレイヤーとして実行する必要があります！", true));
                    return true;
                }

                Player player = (Player) sender;

                try {
                    TutorialItems tutorialItem = TutorialItems.valueOf(args[1]);
                    player.getInventory().addItem(tutorialItem.getItem());
                } catch (IllegalArgumentException exception) {
                    player.sendMessage(Chat.f("&c指定したアイテムは存在しません！", true));
                }

                return true;
            default:
                sender.sendMessage(Chat.f("&cそのようなオプションはありません。githubのREADME.mdにコマンドの使い方が載っています！", true));
                return true;
        }
    }
}
