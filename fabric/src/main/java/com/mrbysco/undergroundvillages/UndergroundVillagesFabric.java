package com.mrbysco.undergroundvillages;

import com.mrbysco.undergroundvillages.config.UndergroundConfigFabric;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;

public class UndergroundVillagesFabric implements ModInitializer {
	public static ConfigHolder<UndergroundConfigFabric> config;

	@Override
	public void onInitialize() {
		config = AutoConfig.register(UndergroundConfigFabric.class, Toml4jConfigSerializer::new);

		CommonClass.init();
	}
}
