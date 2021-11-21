package beginnerlifetutorial.beginnerlifetutorial.commands;

import beginnerlifetutorial.beginnerlifetutorial.utils.Chat;
import beginnerlifetutorial.beginnerlifetutorial.utils.TutorialConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class TutorialCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.teleport(TutorialConfig.getTspLocation());
            player.sendMessage(Chat.f("&6あなたをチュートリアルにテレポートさせました", false));
        } else {
            sender.sendMessage(Chat.f("&cプレイヤーのみがこのコマンドを実行できます！", false));
        }

        return true;
    }
}
