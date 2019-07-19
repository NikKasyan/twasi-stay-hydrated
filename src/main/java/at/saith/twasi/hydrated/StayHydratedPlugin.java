package at.saith.twasi.hydrated;

import net.twasi.core.plugin.TwasiPlugin;
import net.twasi.core.plugin.api.TwasiUserPlugin;

public class StayHydratedPlugin extends TwasiPlugin {
    public Class<? extends TwasiUserPlugin> getUserPluginClass() {
        return StayHydratedUserPlugin.class;
    }
}
