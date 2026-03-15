package fi.dy.masa.malilib.command;

import fi.dy.masa.malilib.config.ConfigManager;
import net.minecraft.src.ChatMessageComponent;
import net.minecraft.src.CommandBase;
import net.minecraft.src.ICommandSender;

import java.util.List;

public class CommandReloadAll implements IManyLibCommand {

    @Override
    public void processCommand(ICommandSender iCommandSender, String[] strings)
    {
        if (strings.length == 0)
        {
            ConfigManager.getInstance().loadAllConfigs();
            CommandBase.notifyAdmins(iCommandSender, "commands.manyLib.reloadAll.success");
        }
        else
        {
            iCommandSender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("commands.manyLib.reloadAll.usage"));
        }
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender iCommandSender, String[] strings) {
        return null;
    }
}