package beginnerlifetutorial.beginnerlifetutorial.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TutorialStepEvent extends Event{
    private final Player player;
    private final HandlerList HANDLERS = new HandlerList();

    public TutorialStepEvent(Player player) {
        this.player = player;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public Player getPlayer() {
        return player;
    }
}
