package fi.dy.masa.malilib.command;

import net.minecraft.src.ICommandSender;

import java.util.List;

public interface IManyLibCommand {
    void processCommand(ICommandSender iCommandSender, String[] strings);

    List<String> addTabCompletionOptions(ICommandSender iCommandSender, String[] strings);
}
