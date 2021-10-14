package com.chillaxowner.chillaxscoreboard;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.plugin.Plugin;

@Plugin(
        id = "chillaxscoreboard",
        name = "ChillaxScoreboard",
        description = "Scoreboard for Chillax RLCraft",
        url = "https://www.chillax-mc.com",
        authors = {
                "ChillaxOwner"
        }
)
public class ChillaxScoreboard {

    @Inject
    private Logger logger;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
    }
}
