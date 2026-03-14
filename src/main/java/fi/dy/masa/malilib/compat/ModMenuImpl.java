package fi.dy.masa.malilib.compat;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import fi.dy.masa.malilib.ManyLibConfig;

public class ModMenuImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> ManyLibConfig.getInstance().getConfigScreen(parent);
    }
}