package com.chillaxowner.chillaxscoreboard;

import com.google.inject.Inject;
import dk.xakeps.view.api.sidebar.Sidebar;
import dk.xakeps.view.api.sidebar.SidebarManager;
import dk.xakeps.view.api.sidebar.StaticText;
import me.rojo8399.placeholderapi.PlaceholderService;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

import java.util.Optional;

@Plugin(
        id = "chillaxscoreboard",
        name = "ChillaxScoreboard",
        description = "Scoreboard for Chillax RLCraft",
        url = "https://www.chillax-mc.com",
        authors = {
                "ChillaxOwner"
        },
        dependencies = {
                @Dependency(id = "placeholderapi"),
                @Dependency(id = "view-sponge")
        }
)
public class ChillaxScoreboard {

    @Inject
    private Logger logger;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {

    }

    PlaceholderService placeholderService;
//    @Listener(order = Order.LATE)
//    public void onClientConnectionJoin(ClientConnectionEvent.Join event) {
//        Player p = event.getTargetEntity();
//        UUID uuid = p.getUniqueId();
//        try {
//            PlayerConfig pc = PlayerConfig.of(uuid);
//            pc.init();
//            TaskManager.update(p, pc);
//        } catch (IOException e) {
//            logger.warn("load failed", e);
//        }
//    }

    @Listener
    public void onPlayerJoin(ClientConnectionEvent.Join event){
        Optional<SidebarManager> sidebarManagerOpt = Sponge.getServiceManager().provide(SidebarManager.class);
        if (sidebarManagerOpt.isPresent()) {
            Player p = event.getTargetEntity();
            SidebarManager sidebarManager = sidebarManagerOpt.get();
            Sidebar sidebar = sidebarManager.createSidebar(p);
            sidebar.setTitle(new StaticText(Text.of("Title!")));
            sidebar.addLine(new StaticText(Text.of("Hello world!")));
            sidebar.addLine(new StaticText(Text.of(p.getName())));
            sidebar.addLine(sidebar.getEmptyText());

            Text joinText = placeholderService.replacePlaceholders("%player_name%", p, p);
            sidebar.addLine(new StaticText(Text.of("Player: " + joinText)));
        }
    }

    @Listener
    public void onPlayerQuit(ClientConnectionEvent.Disconnect event) {
        Optional<SidebarManager> sidebarManagerOpt = Sponge.getServiceManager().provide(SidebarManager.class);
        Player p = event.getTargetEntity();
        if (sidebarManagerOpt.isPresent()) {
            SidebarManager sidebarManager = sidebarManagerOpt.get();
            sidebarManager.removeSidebar(p);
        }
    }


}
