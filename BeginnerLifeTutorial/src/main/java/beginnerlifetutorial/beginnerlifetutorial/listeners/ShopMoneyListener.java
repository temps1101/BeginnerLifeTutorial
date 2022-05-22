package beginnerlifetutorial.beginnerlifetutorial.listeners;

import beginnerlifetutorial.beginnerlifetutorial.BeginnerLifeTutorial;
import beginnerlifetutorial.beginnerlifetutorial.enums.TutorialItems;
import beginnerlifetutorial.beginnerlifetutorial.enums.TutorialType;
import beginnerlifetutorial.beginnerlifetutorial.events.StepTutorial;
import beginnerlifetutorial.beginnerlifetutorial.utils.Chat;
import beginnerlifetutorial.beginnerlifetutorial.utils.PlayerStatus;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ShopMoneyListener implements Listener {
    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {

        if (!event.getMessage().equals("/money")) {
            return;
        }

        Player player = event.getPlayer();
        PlayerStatus playerStatus = BeginnerLifeTutorial.getPlayerStatus(player);

        if (playerStatus == null) {
            return;
        }
        if (playerStatus.getTutorialType() != TutorialType.SHOPMONEY) {
            return;
        }

        if (playerStatus.getTutorialPhase() == 2) {
            player.sendMessage(Chat.f("&aBalance: $10.60", false));

            playerStatus.setTutorialPhase(3);
            BeginnerLifeTutorial.changePlayerStatus(player, playerStatus);
            Bukkit.getPluginManager().callEvent(new StepTutorial(player));

            event.setCancelled(true);
        } else {
            if (!player.hasPermission("tutorial.permission.admin")) {
                player.sendTitle(Chat.f("&aヒント", false), Chat.f("&6チュートリアル中はMONEYコマンドは許可されていない時は使えません！", false), 0, 140, 0);
                event.setCancelled(true);
            }
        }
    }
}
