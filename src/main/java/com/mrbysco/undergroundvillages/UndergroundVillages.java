package com.mrbysco.undergroundvillages;

import com.mrbysco.undergroundvillages.config.UndergroundConfig;
import com.mrbysco.undergroundvillages.feature.placement.UndergroundVillagePlacements;
import com.mrbysco.undergroundvillages.registry.ModConfiguredStructureFeatures;
import com.mrbysco.undergroundvillages.registry.ModHeightProvider;
import com.mrbysco.undergroundvillages.registry.ModStructureProcessorList;
import com.mrbysco.undergroundvillages.registry.ModStructureSets;
import com.mrbysco.undergroundvillages.registry.ModTemplatePools;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(UndergroundVillages.MOD_ID)
public class UndergroundVillages {
	public static final String MOD_ID = "underground_villages";

	public UndergroundVillages() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, UndergroundConfig.commonSpec);

		ModStructureProcessorList.STRUCTURE_PROCESSORS.register(eventBus);
		UndergroundVillagePlacements.PLACED_FEATURES.register(eventBus);
		ModTemplatePools.TEMPLATE_POOLS.register(eventBus);
		ModConfiguredStructureFeatures.CONFIGURED_STRUCTURES.register(eventBus);
		ModStructureSets.STRUCTURE_SETS.register(eventBus);
		ModHeightProvider.HEIGHT_PROVIDERS.register(eventBus);
	}
}
