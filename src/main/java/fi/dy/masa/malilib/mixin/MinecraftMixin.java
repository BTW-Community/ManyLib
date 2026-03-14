package fi.dy.masa.malilib.mixin;

import fi.dy.masa.malilib.event.InitializationHandler;
import fi.dy.masa.malilib.event.InputEventHandler;
import fi.dy.masa.malilib.event.TickHandler;
import fi.dy.masa.malilib.event.WorldLoadHandler;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.KeyBinding;
import net.minecraft.src.Minecraft;
import net.minecraft.src.WorldClient;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
    @Shadow
    public WorldClient theWorld;

    @Unique
    private WorldClient worldBefore;

    @Inject(method = "startGame", at = @At("RETURN"))
    private void onStartGameComplete(CallbackInfo ci) {
        ((InitializationHandler) InitializationHandler.getInstance()).onGameStartDone();
    }

    @Inject(method = "runTick", at = @At("RETURN"))
    private void hotKeyListener(CallbackInfo ci) {
        TickHandler.getInstance().onClientTick((Minecraft) (Object) this);
    }

    @Inject(method = "loadWorld(Lnet/minecraft/src/WorldClient;Ljava/lang/String;)V", at = @At("HEAD"))
    private void onLoadWorldPre(WorldClient worldClientIn, String par2Str, CallbackInfo ci) {
        if (this.theWorld != null) {
            this.worldBefore = this.theWorld;
        }
        ((WorldLoadHandler) WorldLoadHandler.getInstance()).onWorldLoadPre(this.theWorld, worldClientIn, (Minecraft) (Object) this);
    }

    @Inject(method = "loadWorld(Lnet/minecraft/src/WorldClient;Ljava/lang/String;)V", at = @At("RETURN"))
    private void onLoadWorldPost(WorldClient worldClientIn, String par2Str, CallbackInfo ci) {
        ((WorldLoadHandler) WorldLoadHandler.getInstance()).onWorldLoadPost(this.worldBefore, worldClientIn, (Minecraft) (Object) this);
        if (this.worldBefore != null) {
            this.worldBefore = null;
        }
    }

    @Redirect(method = "runTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/KeyBinding;setKeyBindState(IZ)V", ordinal = 1))
    private void onKeyInput(int keyCode, boolean pressed)
    {
        boolean cancel = ((InputEventHandler) InputEventHandler.getInputManager()).onKeyInput(keyCode, 0, 0, pressed ? GLFW.GLFW_PRESS : GLFW.GLFW_RELEASE);
        if (!cancel)
        {
            KeyBinding.setKeyBindState(keyCode, pressed);
        }
    }

    @Inject(method = "displayGuiScreen", at = @At("HEAD"))
    private void onGuiChange(GuiScreen par1GuiScreen, CallbackInfo ci) {
        if (par1GuiScreen == null) {
            ((InputEventHandler) InputEventHandler.getInputManager()).setTexting(false);
        }
    }
}
