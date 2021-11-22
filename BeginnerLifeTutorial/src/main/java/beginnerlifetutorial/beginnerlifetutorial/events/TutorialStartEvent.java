package beginnerlifetutorial.beginnerlifetutorial.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TutorialStartEvent extends Event {
    public static enum TutorialType {
        RESOURCE, SHOPMONEY, DUNGEON, RAID
    }
    public final Player player;
    private final HandlerList HANDLERS = new HandlerList();
    private final TutorialType tutorialType;

    public TutorialStartEvent(Player player, TutorialType tutorialType) {
        this.player = player;
        this.tutorialType = tutorialType;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public Player getPlayer() {
        return player;
    }

    public TutorialType getTutorialType() {
        return tutorialType;
    }
}
