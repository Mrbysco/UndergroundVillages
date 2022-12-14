package com.mrbysco.undergroundvillages.feature;

import com.mrbysco.undergroundvillages.UndergroundVillages;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;

public interface ConfiguredUndergroundStructureTags {
	TagKey<Structure> UNDERGROUND_VILLAGE = create(UndergroundVillages.MOD_ID + ":underground_village");

	private static TagKey<Structure> create(String id) {
		return TagKey.create(Registries.STRUCTURE, new ResourceLocation(id));
	}
}
