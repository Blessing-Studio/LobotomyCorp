package com.lambdainnovation.lambdalib2.registry;

import com.lambdainnovation.lambdalib2.registry.impl.RegistryManager;
import com.lambdainnovation.lambdalib2.registry.impl.RegistryManager.ModContext;
import com.lambdainnovation.lambdalib2.util.Debug;

/**
 * Provides contextual information during startup registry.
 */
public class RegistryContext {

    /**
     * Gets current mod that {@link StateEventCallback} callbacks are being executed on.
     */
    public static Object getActiveMod() {
        return RegistryManager.INSTANCE.getActiveMod();
    }

    public static Object getModForPackage(String classPath)  {
        ModContext ctx = Debug.assertNotNull(RegistryManager.INSTANCE.findMod(classPath),
            "No mod handling class " + classPath + " found.");
        return Debug.assertNotNull(ctx.modObject, "Mod hasn't be constructed! Use this method only AFTER preInit stage.");
    }

}
