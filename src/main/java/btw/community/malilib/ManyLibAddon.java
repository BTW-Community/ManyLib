package btw.community.malilib;

import api.AddonHandler;
import api.BTWAddon;
import fi.dy.masa.malilib.command.CommandMain;

public class ManyLibAddon extends BTWAddon {

    @Override
    public void initialize() {
        AddonHandler.logMessage(getName() + " v" + getVersionString() + " Initializing...");
        AddonHandler.registerCommand(new CommandMain(), false);
    }
}