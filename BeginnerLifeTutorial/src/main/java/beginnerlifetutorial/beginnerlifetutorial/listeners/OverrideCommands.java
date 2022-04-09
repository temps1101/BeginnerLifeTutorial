package beginnerlifetutorial.beginnerlifetutorial.listeners;

import beginnerlifetutorial.beginnerlifetutorial.BeginnerLifeTutorial;
import beginnerlifetutorial.beginnerlifetutorial.events.TutorialStepEvent;
import beginnerlifetutorial.beginnerlifetutorial.utils.Chat;
import beginnerlifetutorial.beginnerlifetutorial.utils.ItemCreator;
import beginnerlifetutorial.beginnerlifetutorial.utils.PlayerStatus;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import static beginnerlifetutorial.beginnerlifetutorial.enums.TutorialPhase.NEXT_MESSAGE_PRESSED_14;
import static beginnerlifetutorial.beginnerlifetutorial.enums.TutorialPhase.OTT_GAVE;
import static beginnerlifetutorial.beginnerlifetutorial.enums.TutorialType.RESOURCE;

public class OverrideCommands implements Listener {
    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {
        switch (event.getMessage().toLowerCase()) {
            case "/ott":
                Player player = event.getPlayer();

                if (player.hasPermission("ltutorial.permission.admin")) {
                    return;
                }

                if (BeginnerLifeTutorial.getPlayerCache().containsKey(player)) {
                    PlayerStatus playerStatus = BeginnerLifeTutorial.getPlayerCache().get(player);

                    if (playerStatus.getTutorialType() == RESOURCE) {
                        event.setCancelled(true);

                        if (playerStatus.getTutorialPhase() != NEXT_MESSAGE_PRESSED_14) {
                            player.sendMessage(Chat.f("&c資源チュートリアル中にこのコマンドは使用できません！", false));
                            return;
                        }

                        if (playerStatus.getDummyOntimePoint() != 0) {
                            event.getPlayer().sendMessage("here");
                            int amount = playerStatus.getDummyOntimePoint() / 10;
                            ItemStack ontimeTicket = ItemCreator.start()
                                    .material(Material.PAPER)
                                    .count(amount)
                                    .name("&aオンタイムチケット")
                                    .lore("ログインしていると一定時間ごとにもらえる。")
                                    .lore("換金、特殊アイテムとの交換等に使える。")
                                    .unbreakable(true)
                                    .enchantment(Enchantment.MENDING, 1)
                                    .flags(ItemFlag.HIDE_ENCHANTS)
                                    .flags(ItemFlag.HIDE_UNBREAKABLE)
                                    .create();

                            player.getInventory().addItem(ontimeTicket);
                            player.sendMessage(Chat.f("&b{0} オンタイムポイントをオンタイムチケット{1}枚に変換しました。", false, playerStatus.getDummyOntimePoint(), amount));

                            playerStatus.setDummyOntimePoint(0);
                            playerStatus.setTutorialPhase(OTT_GAVE);
                            BeginnerLifeTutorial.getPlayerCache().replace(player, playerStatus);
                            Bukkit.getPluginManager().callEvent(new TutorialStepEvent(player));
                        }
                    }
                }

                break;

            default:
                break;
        }
    }
}
