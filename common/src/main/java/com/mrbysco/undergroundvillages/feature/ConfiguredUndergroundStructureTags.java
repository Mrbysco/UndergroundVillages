package com.mrbysco.undergroundvillages.feature;

import com.mrbysco.undergroundvillages.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;

public interface ConfiguredUndergroundStructureTags {
	TagKey<Structure> UNDERGROUND_VILLAGE = create("underground_village");

	private static TagKey<Structure> create(String path) {
		return TagKey.create(Registries.STRUCTURE, Identifier.fromNamespaceAndPath(Constants.MOD_ID, path));
	}
}
