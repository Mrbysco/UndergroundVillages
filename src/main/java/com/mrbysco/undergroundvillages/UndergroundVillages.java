package com.mrbysco.undergroundvillages;

import com.mrbysco.undergroundvillages.config.UndergroundConfig;
import com.mrbysco.undergroundvillages.registry.ModHeightProvider;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(UndergroundVillages.MOD_ID)
public class UndergroundVillages {
	public static final String MOD_ID = "underground_villages";

	public UndergroundVillages() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, UndergroundConfig.commonSpec);

		ModHeightProvider.HEIGHT_PROVIDERS.register(eventBus);
	}
}
