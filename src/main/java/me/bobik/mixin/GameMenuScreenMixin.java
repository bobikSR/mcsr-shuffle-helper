package me.bobik.mixin;

import com.redlimerl.speedrunigt.timer.InGameTimer;
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
        if (!InGameTimer.getInstance().isCompleted())
            saveButton.active = false;
        return saveButton;
    }

    @ModifyVariable(
            method = "initWidgets",
            at = @At("TAIL"),
            ordinal = 0
    )
    private ButtonWidget disableOpenToLan(ButtonWidget openToLanButton){
        if (!InGameTimer.getInstance().isCompleted())
            openToLanButton.active = false;
        return openToLanButton;
    }
}
