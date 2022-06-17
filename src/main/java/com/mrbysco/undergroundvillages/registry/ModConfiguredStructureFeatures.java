package com.mrbysco.undergroundvillages.registry;

import com.mrbysco.undergroundvillages.UndergroundVillages;
import com.mrbysco.undergroundvillages.config.UndergroundConfig;
import com.mrbysco.undergroundvillages.height.ConfigHeight;
import com.mrbysco.undergroundvillages.util.UndergroundBiomeTags;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;

public class ModConfiguredStructureFeatures {
	public static final DeferredRegister<Structure> CONFIGURED_STRUCTURES = DeferredRegister.create(Registry.STRUCTURE_REGISTRY, UndergroundVillages.MOD_ID);

	public static final RegistryObject<Structure> UNDERGROUND_VILLAGE = CONFIGURED_STRUCTURES.register("underground_village", () ->
			new JigsawStructure(structure(UndergroundBiomeTags.HAS_VILLAGE_UNDERGROUND, TerrainAdjustment.BEARD_THIN),
					ModTemplatePools.UNDERGROUND_START.getHolder().get(), 6, new ConfigHeight(), true));

	private static Structure.StructureSettings structure(TagKey<Biome> biomeTagKey, TerrainAdjustment adjustment) {
		return structure(biomeTagKey, Map.of(), Decoration.UNDERGROUND_DECORATION, adjustment);
	}

	private static Structure.StructureSettings structure(TagKey<Biome> tagKey, Map<MobCategory, StructureSpawnOverride> categoryStructureSpawnOverrideMap,
														 GenerationStep.Decoration decoration, TerrainAdjustment terrainAdjustment) {
		return new Structure.StructureSettings(biomes(tagKey), categoryStructureSpawnOverrideMap, decoration, terrainAdjustment);
	}

	private static HolderSet<Biome> biomes(TagKey<Biome> tagKey) {
		return BuiltinRegistries.BIOME.getOrCreateTag(tagKey);
	}
}