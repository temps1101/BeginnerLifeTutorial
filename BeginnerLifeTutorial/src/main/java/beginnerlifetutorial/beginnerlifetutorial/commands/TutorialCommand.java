package beginnerlifetutorial.beginnerlifetutorial.commands;

import beginnerlifetutorial.beginnerlifetutorial.utils.Chat;
import beginnerlifetutorial.beginnerlifetutorial.utils.TutorialConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;


public class TutorialCommand implements CommandExecutor {
    private static Map<String, Long> commandCoolDownCache = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (commandCoolDownCache.containsKey(player.getName())) {
                int timeRemaining = (int) (System.currentTimeMillis() - commandCoolDownCache.get(player.getName())) / 1000;
                if (timeRemaining < 10) {
                    player.teleport(TutorialConfig.getTspLocation());
                    player.sendMessage(Chat.f("&6あなたをチュートリアルにテレポートさせました", false));
                } else {
                    player.sendMessage(Chat.f("&cこのコマンドは現在クールダウン中です！残り：{0}秒", false, timeRemaining));
                }
            } else {
                player.teleport(TutorialConfig.getTspLocation());
                player.sendMessage(Chat.f("&6あなたをチュートリアルにテレポートさせました", false));
                commandCoolDownCache.put(player.getName(), System.currentTimeMillis());
            }
        } else {
            sender.sendMessage(Chat.f("&cプレイヤーのみがこのコマンドを実行できます！", false));
        }

        return true;
    }
}
