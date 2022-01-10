package me.declipsonator.trolladdon.mixins;

import me.declipsonator.trolladdon.AlternativeClasses.AlternativeObject;
import me.declipsonator.trolladdon.TrollAddon;
import meteordevelopment.meteorclient.systems.commands.Command;
import meteordevelopment.meteorclient.utils.Utils;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mixin(Command.class)
public class CommandMixin {

    @Mutable
    @Final
    @Shadow
    private String name;

    @Mutable
    @Final
    @Shadow
    private String title;

    @Mutable
    @Final
    @Shadow
    private String description;

    @Mutable
    @Final
    @Shadow
    private List<String> aliases = new ArrayList<>();


    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeCharacteristics(String name, String description, String[] aliases, CallbackInfo ci) {
        TrollAddon.altCommandsReady();
        if(TrollAddon.altCommands.containsKey(name)) {
            AlternativeObject module = (AlternativeObject) TrollAddon.altCommands.get(name);
            this.name = getRandomSelection(module.getAltNames());
            this.title = Utils.nameToTitle(this.name);
            this.description = getRandomSelection(module.getAltDescriptions());
            //I decided against making new aliases. I'm just going to remove them and confuse ppl more
            this.aliases.clear();
        }


    }


    private String getRandomSelection(String[] array) {
        Random random = new Random();
        int index = random.nextInt(array.length);
        return array[index];
    }
}
