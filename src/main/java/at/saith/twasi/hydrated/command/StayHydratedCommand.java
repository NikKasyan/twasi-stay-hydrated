package at.saith.twasi.hydrated.command;

import net.twasi.core.database.models.TwitchAccount;
import net.twasi.core.plugin.api.TwasiUserPlugin;
import net.twasi.core.plugin.api.customcommands.TwasiCustomCommandEvent;
import net.twasi.core.plugin.api.customcommands.TwasiPluginCommand;
import net.twasi.core.translations.renderer.TranslationRenderer;
import net.twasi.twitchapi.helix.streams.response.StreamDTO;
import net.twasi.twitchapi.options.TwitchRequestOptions;

import java.util.Date;

import static net.twasi.twitchapi.TwitchAPI.helix;

public class StayHydratedCommand extends TwasiPluginCommand {

    public StayHydratedCommand(TwasiUserPlugin twasiUserPlugin) {
        super(twasiUserPlugin);
    }

    @Override
    protected void process(TwasiCustomCommandEvent event) {
        TranslationRenderer renderer = event.getRenderer();
        TwitchAccount twitchacc = event.getStreamer().getUser().getTwitchAccount();
        StreamDTO stream = helix().streams().getStreamsByUser(twitchacc.getTwitchId(), 1, new TwitchRequestOptions().withAuth(twitchacc.toAuthContext())).getData().get(0);
        if (stream == null) {
            event.reply(renderer.render("twasi.error.nostream"));
            return;
        }
        long duration = (new Date().getTime() - stream.getStartedAt().getTime()) / (1000 * 60 * 60);
        long hydration = 80 * duration;

        renderer.bind("hydration", hydration + "");
        event.reply(renderer.render("twasi.hydration"));

    }

    @Override
    public String requirePermissionKey() {
        return "twasi.hydrated.mod.*";
    }

    @Override
    public boolean allowsTimer() {
        return true;
    }

    public String getCommandName() {
        return "hydrated";
    }
}
