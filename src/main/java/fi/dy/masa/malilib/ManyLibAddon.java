package fi.dy.masa.malilib;

import api.AddonHandler;
import api.BTWAddon;
import fi.dy.masa.malilib.command.CommandMain;
import fi.dy.masa.malilib.event.InitializationHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ManyLibAddon extends BTWAddon {
    public static final String MOD_ID = "manylib";
    public static final String MOD_NAME = "ManyLib";
    public static final String RESOURCE_DOMAIN = "manylib";
    public static final Logger logger = LogManager.getLogger(MOD_ID);

    @Override
    public void initialize() {
        AddonHandler.registerCommand(new CommandMain(), false);
        ManyLibConfig.getInstance().load();
        InitializationHandler.getInstance().registerInitializationHandler(new ManyLibInitHandler());
    }
}