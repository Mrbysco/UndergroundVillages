package com.mrbysco.undergroundvillages.registry;

import com.mrbysco.undergroundvillages.UndergroundVillages;
import com.mrbysco.undergroundvillages.height.ConfigHeight;
import com.mrbysco.undergroundvillages.util.UndergroundBiomeTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;

import java.util.Map;

public class ModStructures {

	public static final ResourceKey<Structure> UNDERGROUND_VILLAGE =
			ResourceKey.create(Registries.STRUCTURE, new ResourceLocation(UndergroundVillages.MOD_ID, "underground_village"));

	public static void bootstrap(BootstapContext<Structure> context) {
		HolderGetter<Biome> biomeHolderGetter = context.lookup(Registries.BIOME);
		HolderGetter<StructureTemplatePool> templateHolderGetter = context.lookup(Registries.TEMPLATE_POOL);

		context.register(UNDERGROUND_VILLAGE,
				new JigsawStructure(structure(biomeHolderGetter.getOrThrow(UndergroundBiomeTags.HAS_VILLAGE_UNDERGROUND), TerrainAdjustment.BEARD_THIN),
						templateHolderGetter.getOrThrow(ModTemplatePools.UNDERGROUND_START), 6, new ConfigHeight(), true));
	}


	private static Structure.StructureSettings structure(HolderSet<Biome> biomeHolderSet,
														 Map<MobCategory, StructureSpawnOverride> spawnOverrideMap,
														 GenerationStep.Decoration decoration, TerrainAdjustment terrainAdjustment) {
		return new Structure.StructureSettings(biomeHolderSet, spawnOverrideMap, decoration, terrainAdjustment);
	}

	private static Structure.StructureSettings structure(HolderSet<Biome> biomeHolderSet, TerrainAdjustment terrainAdjustment) {
		return structure(biomeHolderSet, Map.of(), GenerationStep.Decoration.UNDERGROUND_DECORATION, terrainAdjustment);
	}

}