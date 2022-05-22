package beginnerlifetutorial.beginnerlifetutorial.commands;

import beginnerlifetutorial.beginnerlifetutorial.BeginnerLifeTutorial;
import beginnerlifetutorial.beginnerlifetutorial.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import static org.bukkit.Sound.ENTITY_PLAYER_LEVELUP;

public class TutorialCommand implements CommandExecutor {
    private FileConfiguration configuration;

    public TutorialCommand(FileConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.teleport(configuration.getLocation("TutorialHomeLocation"));
            Bukkit.getScheduler().runTaskLater(BeginnerLifeTutorial.getPlugin(), () -> {
                player.playSound(player.getLocation(), ENTITY_PLAYER_LEVELUP, 1, 1);
                player.sendTitle(Chat.f("&6チュートリアル", false), Chat.f("&6TUTORIAL", false), 10, 70, 20);
            }, 10); // 0.5s遅延
        } else {
            sender.sendMessage(Chat.f("&cプレイヤーになりましょう。", true));
        }
        return true;
    }
}
