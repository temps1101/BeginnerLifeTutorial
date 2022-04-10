package beginnerlifetutorial.beginnerlifetutorial.enums;

import beginnerlifetutorial.beginnerlifetutorial.utils.ItemCreator;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class TutorialItems {
  private static ItemStack ontimeTicket = ItemCreator.start()
      .material(Material.PAPER)
      .count(1)
      .name("&aオンタイムチケット")
      .lore("ログインしていると一定時間ごとにもらえる。")
      .lore("換金、特殊アイテムとの交換等に使える。")
      .unbreakable(true)
      .enchantment(Enchantment.MENDING, 1)
      .flags(ItemFlag.HIDE_ENCHANTS)
      .flags(ItemFlag.HIDE_UNBREAKABLE)
      .create();
  private static ItemStack tutorialPickaxe = ItemCreator.start()
      .material(Material.DIAMOND_PICKAXE)
      .count(1)
      .name("&2チュートリアル用ツルハシ")
      .lore("めっちゃ安い")
      .create();

  public static ItemStack getOntimeTicket() {
    return ontimeTicket;
  }

  public static ItemStack getTutorialPickaxe() {
    return tutorialPickaxe;
  }
}
