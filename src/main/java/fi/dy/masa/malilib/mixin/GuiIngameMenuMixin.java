package fi.dy.masa.malilib.mixin;

import fi.dy.masa.malilib.ManyLibConfig;
import fi.dy.masa.malilib.gui.DrawContext;
import fi.dy.masa.malilib.gui.MenuIconButton;
import fi.dy.masa.malilib.gui.screen.FakeModMenu;
import fi.dy.masa.malilib.render.RenderUtils;
import fi.dy.masa.malilib.util.StringUtils;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiIngameMenu;
import net.minecraft.src.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(GuiIngameMenu.class)
public abstract class GuiIngameMenuMixin extends GuiScreen {
    @Unique
    private static final int optionsButtonID = 28251197;
    @Unique
    private GuiButton button;

    @SuppressWarnings("unchecked")
    @Inject(method = "initGui", at = @At("RETURN"))
    private void addButton(CallbackInfo ci) {
        if (ManyLibConfig.HideConfigButton.getBooleanValue()) return;
        this.button = new MenuIconButton(optionsButtonID, this.width / 2 + 100 + 5, this.height / 4 + 72 - 16);
        this.buttonList.add(this.button);
    }

    @Inject(method = "drawScreen", at = @At("RETURN"))
    private void tooltip(int par1, int par2, float par3, CallbackInfo ci) {
        if (ManyLibConfig.HideConfigButton.getBooleanValue()) return;
        if (this.button.func_82252_a()) {
            RenderUtils.drawHoverText(par1, par2, List.of(StringUtils.translate("manyLib.gui.button.options")), new DrawContext());
        }
    }

    @Inject(method = "actionPerformed", at = @At("RETURN"))
    private void action(GuiButton par1GuiButton, CallbackInfo ci) {
        if (ManyLibConfig.HideConfigButton.getBooleanValue()) return;
        if (par1GuiButton.id == optionsButtonID) {
            this.mc.displayGuiScreen(new FakeModMenu(this));
        }
    }
}
