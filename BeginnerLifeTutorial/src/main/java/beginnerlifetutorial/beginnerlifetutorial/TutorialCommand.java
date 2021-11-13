package beginnerlifetutorial.beginnerlifetutorial;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class TutorialCommand implements CommandExecutor, Listener {

    private final BeginnerLifeTutorial plugin;

    public TutorialCommand(BeginnerLifeTutorial beginnerLifeTutorial) {
        this.plugin = beginnerLifeTutorial;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("ltutorial")) {
            if (args.length <= 0) {
                return true;
            }
            if(args[0].equalsIgnoreCase("reload")){
                if (sender.hasPermission("ltutorial.permission.admin")) {
                    plugin.reloadConfig();
                    sender.sendMessage("configリロードしました");
                }
                return true;
            }
        }
        return true;
    }
}
