package beginnerlifetutorial.beginnerlifetutorial.commands;

import beginnerlifetutorial.beginnerlifetutorial.BeginnerLifeTutorial;
import beginnerlifetutorial.beginnerlifetutorial.enums.TutorialPhase;
import beginnerlifetutorial.beginnerlifetutorial.events.TutorialStepEvent;
import beginnerlifetutorial.beginnerlifetutorial.utils.PlayerStatus;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class NavigatorCommand implements CommandExecutor {
    private String COMMAND_PASSWORD = "caravan";
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (Objects.equals(args[0], COMMAND_PASSWORD)) {
                if (BeginnerLifeTutorial.getPlayerCache().containsKey(player)) {
                    PlayerStatus playerStatus = BeginnerLifeTutorial.getPlayerCache().get(player);
                    playerStatus.setTutorialPhase(TutorialPhase.valueOf(args[1]));

                    BeginnerLifeTutorial.getPlayerCache().replace(player, playerStatus);

                    Bukkit.getPluginManager().callEvent(new TutorialStepEvent(player));
                }
            }
        }
        return true;
    }
}