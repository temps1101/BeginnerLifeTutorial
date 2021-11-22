package beginnerlifetutorial.beginnerlifetutorial.commands;

import beginnerlifetutorial.beginnerlifetutorial.BeginnerLifeTutorial;
import beginnerlifetutorial.beginnerlifetutorial.utils.Chat;
import beginnerlifetutorial.beginnerlifetutorial.utils.TutorialConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


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

                default:
                    sender.sendMessage(Chat.f("&6/ltutorialAdmin reload", true));
                    return true;
            }
        }
        return true;
    }
}