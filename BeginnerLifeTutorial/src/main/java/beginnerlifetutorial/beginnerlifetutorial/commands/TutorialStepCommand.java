package beginnerlifetutorial.beginnerlifetutorial.commands;

import beginnerlifetutorial.beginnerlifetutorial.BeginnerLifeTutorial;
import beginnerlifetutorial.beginnerlifetutorial.events.StepTutorial;
import beginnerlifetutorial.beginnerlifetutorial.utils.Chat;
import beginnerlifetutorial.beginnerlifetutorial.utils.PlayerStatus;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class TutorialStepCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Chat.f("&cプレイヤーになってからコマンドを実行してください", true));
            return true;
        }

        if (!args[0].equals("caravan")) {
            sender.sendMessage(Chat.f("&cパスワードが違います", true));
            return true;
        }

        if (args.length != 2) {
            sender.sendMessage(Chat.f("&c構文が違います", true));
            return true;
        }

        Player player = (Player) sender;
        PlayerStatus playerStatus = BeginnerLifeTutorial.getPlayerStatus(player);

        if (playerStatus == null) {
            return true;
        }

        if (playerStatus.getTutorialPhase() == Integer.parseInt(args[1])) {
            playerStatus.setTutorialPhase(Integer.parseInt(args[1]) + 1);
            BeginnerLifeTutorial.changePlayerStatus(player, playerStatus);

            Bukkit.getPluginManager().callEvent(new StepTutorial(player));
        }

        return true;
    }
}
