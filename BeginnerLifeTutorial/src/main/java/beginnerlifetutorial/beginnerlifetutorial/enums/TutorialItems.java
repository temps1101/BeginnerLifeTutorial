package beginnerlifetutorial.beginnerlifetutorial.enums;

import beginnerlifetutorial.beginnerlifetutorial.utils.ItemCreator;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Material.*;
import static org.bukkit.enchantments.Enchantment.*;
import static org.bukkit.inventory.ItemFlag.HIDE_ENCHANTS;
import static org.bukkit.inventory.ItemFlag.HIDE_UNBREAKABLE;

public enum TutorialItems {
    RESOURCE_HOE(ItemCreator.start()
                            .material(DIAMOND_HOE)
                            .name("&6チュートリアル用クワ")
                            .unbreakable(true)
                            .lore("&fチュートリアル用に作られた桑")
                            .count(1)
                            .flags(HIDE_UNBREAKABLE)
                            .create()),

    TUTORIAL_OTT(ItemCreator.start()
                            .material(PAPER)
                            .count(1)
                            .name("&aオンタイムチケット")
                            .lore("ログインしていると一定時間ごとにもらえる。")
                            .lore("換金、特殊アイテムとの交換等に使える。")
                            .unbreakable(true)
                            .enchantment(MENDING, 1)
                            .flags(HIDE_ENCHANTS)
                            .flags(HIDE_UNBREAKABLE)
                            .create()),

    RESOURCE_PICKAXE(ItemCreator.start()
                                .material(DIAMOND_PICKAXE)
                                .count(1)
                                .name("&b資源ピッケル")
                                .lore("&5資源ワールドでの採取に適したピッケル")
                                .lore("&5資源ワールドに眠ったチケットを掘り起こせるかも？")
                                .enchantment(DIG_SPEED, 5)
                                .enchantment(DURABILITY, 3)
                                .create()),

    SIGEN_TICKET(ItemCreator.start()
                            .material(PAPER)
                            .count(1)
                            .name("&b資源チケット")
                            .lore("資源ワールドで頑張って資源集めをした証")
                            .unbreakable(true)
                            .enchantment(MENDING, 1)
                            .flags(HIDE_ENCHANTS)
                            .flags(HIDE_UNBREAKABLE)
                            .create());
    private final ItemStack tutorialItem;
    TutorialItems(ItemStack tutorialItem) {
        this.tutorialItem = tutorialItem;
    }
    public ItemStack getItem() {
        return tutorialItem;
    }
}
