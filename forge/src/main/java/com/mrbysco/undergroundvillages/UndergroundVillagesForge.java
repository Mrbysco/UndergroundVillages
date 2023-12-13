package com.mrbysco.undergroundvillages;

import com.mrbysco.undergroundvillages.config.UndergroundConfigForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class UndergroundVillagesForge {

	public UndergroundVillagesForge() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

		CommonClass.init();

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, UndergroundConfigForge.commonSpec);
	}
}