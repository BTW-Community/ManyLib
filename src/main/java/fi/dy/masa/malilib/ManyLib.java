package fi.dy.masa.malilib;

import fi.dy.masa.malilib.event.InitializationHandler;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ManyLib implements ModInitializer {

    public static final String MOD_ID = "manylib";
    public static final String MOD_NAME = "ManyLib";
    public static final String RESOURCE_DOMAIN = "manylib";
    public static final Logger logger = LogManager.getLogger(MOD_ID);

    @Override
    public void onInitialize() {

        ManyLibConfig.getInstance().load();
        InitializationHandler.getInstance().registerInitializationHandler(new ManyLibInitHandler());
    }
}