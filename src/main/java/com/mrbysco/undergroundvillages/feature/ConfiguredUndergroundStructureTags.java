package com.mrbysco.undergroundvillages.feature;

import com.mrbysco.undergroundvillages.UndergroundVillages;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;

public interface ConfiguredUndergroundStructureTags {
	TagKey<ConfiguredStructureFeature<?, ?>> UNDERGROUND_VILLAGE = create(UndergroundVillages.MOD_ID + ":underground_village");

	private static TagKey<ConfiguredStructureFeature<?, ?>> create(String id) {
		return TagKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, new ResourceLocation(id));
	}
}
