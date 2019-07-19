package at.saith.twasi.hydrated;

import at.saith.twasi.hydrated.command.StayHydratedCommand;
import at.saith.twasi.service.TimerService;
import net.twasi.core.plugin.api.TwasiUserPlugin;
import net.twasi.core.plugin.api.events.TwasiEnableEvent;
import net.twasi.core.plugin.api.events.TwasiInstallEvent;
import net.twasi.core.services.ServiceRegistry;


public class StayHydratedUserPlugin extends TwasiUserPlugin {
    public StayHydratedUserPlugin() {
        registerCommand(StayHydratedCommand.class);
    }

    @Override
    public void onEnable(TwasiEnableEvent e) {
        TimerService service = ServiceRegistry.get(TimerService.class);
        try {
            service.registerTimer(this.getTwasiInterface(), "hydrated", 10);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onInstall(TwasiInstallEvent e) {
        e.getModeratorsGroup().addKey("twasi.hydrated.mod.*");
    }

    @Override
    public void onUninstall(TwasiInstallEvent e) {
        e.getModeratorsGroup().removeKey("twasi.hydrated.mod.*");
    }
}
