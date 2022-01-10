package me.declipsonator.trolladdon;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import me.declipsonator.trolladdon.AlternativeClasses.AlternativeObject;
import meteordevelopment.meteorclient.MeteorClient;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.utils.network.Http;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.Map;


public class TrollAddon extends MeteorAddon {
    public static final Logger LOG = LogManager.getLogger();
    public static LinkedTreeMap altModules;
    public static LinkedTreeMap altCommands;
    public static LinkedTreeMap hudModules;


    @Override
    public void onInitialize() {
        LOG.info("Initializing Banana Plus");

        // Required when using @EventHandler
        MeteorClient.EVENT_BUS.registerLambdaFactory("me.declipsonator.trolladdon", (lookupInMethod, klass) -> (MethodHandles.Lookup) lookupInMethod.invoke(null, klass, MethodHandles.lookup()));


    }


    public static void altModulesReady() {
        if(altModules == null) {
            String modulesString = Http.get("https://raw.githubusercontent.com/Declipsonator/Troll-Addon-Code/main/Module-Alternatives.json").sendString().replace("\n", "");

            altModules = new Gson().fromJson(modulesString, new TypeToken<Map<String, AlternativeObject>>(){}.getType());
        }
    }

    public static void altCommandsReady() {
        if(altCommands == null) {
            String modulesString = Http.get("https://raw.githubusercontent.com/Declipsonator/Troll-Addon-Code/main/Command-Alternatives.json").sendString().replace("\n", "");

            altCommands = new Gson().fromJson(modulesString, new TypeToken<Map<String, AlternativeObject>>(){}.getType());
        }
    }



}
