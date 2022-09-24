package com.github.neapovil.sendcoords.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "sendcoords")
public final class ModConfig implements ConfigData
{
    public String commandPrefix = "/la chat neapovil";
}
