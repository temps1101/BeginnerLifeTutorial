package beginnerlifetutorial.beginnerlifetutorial.listeners;

import beginnerlifetutorial.beginnerlifetutorial.BeginnerLifeTutorial;
import beginnerlifetutorial.beginnerlifetutorial.enums.TutorialItems;
import beginnerlifetutorial.beginnerlifetutorial.enums.TutorialPhase;
import beginnerlifetutorial.beginnerlifetutorial.enums.TutorialType;
import beginnerlifetutorial.beginnerlifetutorial.events.TutorialStepEvent;
import beginnerlifetutorial.beginnerlifetutorial.utils.PlayerStatus;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.MerchantInventory;

public class BuyPickaxeListener implements Listener {
  @EventHandler
  public void onInventoryClicked(InventoryClickEvent event) {
    MerchantInventory merchantInventory = (MerchantInventory) event.getClickedInventory();
    if (merchantInventory != null) {
      return;
    }

    if (!(merchantInventory.getSelectedRecipe().getResult() == TutorialItems.getTutorialPickaxe())) {
      return;
    }

    Player player = (Player) event.getWhoClicked();
    if (BeginnerLifeTutorial.getPlayerCache().containsKey(player)) {
      PlayerStatus playerStatus = BeginnerLifeTutorial.getPlayerCache().get(player);
      if (playerStatus.getTutorialType() == TutorialType.SHOPMONEY &&
          playerStatus.getTutorialPhase() == TutorialPhase.OTT_GAVE) {
          playerStatus.setTutorialPhase(TutorialPhase.PICKAXE_BOUGHT);
          BeginnerLifeTutorial.getPlayerCache().replace(player, playerStatus);
          Bukkit.getPluginManager().callEvent(new TutorialStepEvent(player));
      }
    }
  }
}
