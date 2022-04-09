package com.mrbysco.undergroundvillages.registry;

import com.mrbysco.undergroundvillages.UndergroundVillages;
import com.mrbysco.undergroundvillages.util.UndergroundBiomeTags;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModConfiguredStructureFeatures {
	public static final DeferredRegister<ConfiguredStructureFeature<?, ?>> CONFIGURED_STRUCTURES = DeferredRegister.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, UndergroundVillages.MOD_ID);

	public static final RegistryObject<ConfiguredStructureFeature<?, ?>> UNDERGROUND_VILLAGE = CONFIGURED_STRUCTURES.register("underground_village", () ->
			ModStructureFeatures.UNDERGROUND_VILLAGE.get().configured(new JigsawConfiguration(ModTemplatePools.UNDERGROUND_START.getHolder().get(), 6), UndergroundBiomeTags.HAS_VILLAGE_UNDERGROUND, true));
}
