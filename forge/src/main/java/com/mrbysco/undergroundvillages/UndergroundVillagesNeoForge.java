package com.mrbysco.undergroundvillages;

import com.mrbysco.undergroundvillages.config.UndergroundConfigForge;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(Constants.MOD_ID)
public class UndergroundVillagesNeoForge {

	public UndergroundVillagesNeoForge(IEventBus eventBus) {
		CommonClass.init();

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, UndergroundConfigForge.commonSpec);
	}
}