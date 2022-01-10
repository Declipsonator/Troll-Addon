package me.declipsonator.trolladdon.mixins;


import me.declipsonator.trolladdon.AlternativeClasses.AlternativeObject;
import me.declipsonator.trolladdon.TrollAddon;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Module;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(Module.class)
public class ModuleMixin {

    @Mutable
    @Final
    @Shadow
    public String title;

    @Mutable
    @Final
    @Shadow
    public String description;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeCharacteristics(Category category, String name, String description, CallbackInfo ci) {
        TrollAddon.altModulesReady();
        if(TrollAddon.altModules.containsKey(name)) {
            AlternativeObject module = (AlternativeObject) TrollAddon.altModules.get(name);
            this.title = getRandomSelection(module.getAltNames());
            this.description = getRandomSelection(module.getAltDescriptions());
        }
    }


    private String getRandomSelection(String[] array) {
        Random random = new Random();
        int index = random.nextInt(array.length);
        return array[index];
    }

}
