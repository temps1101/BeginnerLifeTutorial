package beginnerlifetutorial.beginnerlifetutorial.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TutorialStartEvent extends Event {
    public static enum TutorialType {
        RESOURCE, SHOPMONEY, DUNGEON, RAID
    }
    private final Player player;
    private final TutorialType tutorialType;

    public TutorialStartEvent(Player player, TutorialType tutorialType) {
        this.player = player;
        this.tutorialType = tutorialType;
    }

    public Player getPlayer() {
        return player;
    }

    public TutorialType getTutorialType() {
        return tutorialType;
    }

    private static final HandlerList HANDLERS = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
