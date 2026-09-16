package me.bobik.mixin;

import me.bobik.client.MCSRShuffleHelper;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GameMenuScreen.class)
public abstract class GameMenuScreenMixin extends Screen {

    protected GameMenuScreenMixin(Text title) {
        super(title);
    }

    @ModifyVariable(
            method = "initWidgets",
            at = @At("STORE"),
            ordinal = 1
    )
    private ButtonWidget disableSaveAndQuit(ButtonWidget saveButton){
        if (MCSRShuffleHelper.completed)
            return saveButton;
        saveButton.active = false;
        return saveButton;
    }

    @ModifyVariable(
            method = "initWidgets",
            at = @At("STORE"),
            ordinal = 0
    )
    private ButtonWidget disableOpenToLan(ButtonWidget openToLanButton){
        if (MCSRShuffleHelper.completed)
            return openToLanButton;
        openToLanButton.active = false;
        return openToLanButton;
    }
}
