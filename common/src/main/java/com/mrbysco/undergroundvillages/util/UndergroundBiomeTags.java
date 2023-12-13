package com.mrbysco.undergroundvillages.util;

import com.mrbysco.undergroundvillages.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class UndergroundBiomeTags {
	public static final TagKey<Biome> HAS_VILLAGE_UNDERGROUND = create("has_structure/village_underground");

	private static TagKey<Biome> create(String id) {
		return TagKey.create(Registries.BIOME, new ResourceLocation(Constants.MOD_ID, id));
	}
}
