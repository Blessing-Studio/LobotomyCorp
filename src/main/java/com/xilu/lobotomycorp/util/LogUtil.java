package com.xilu.lobotomycorp.util;

import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Level;

public class LogUtil {
    public static void log(String text) {
        FMLLog.log("lobotomy corp", Level.INFO, text);
    }
}
