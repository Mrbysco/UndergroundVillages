package com.mrbysco.undergroundvillages.registry;

import com.mrbysco.undergroundvillages.UndergroundVillages;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;

import java.util.List;

public class ModStructureSets {
	public static final ResourceKey<StructureSet> UNDERGROUND_VILLAGES = ResourceKey.create(Registries.STRUCTURE_SET, new ResourceLocation(UndergroundVillages.MOD_ID, "underground_villages"));

	public static void bootstrap(BootstapContext<StructureSet> context) {
		HolderGetter<Structure> structureHolderGetter = context.lookup(Registries.STRUCTURE);
		context.register(UNDERGROUND_VILLAGES, new StructureSet(
				List.of(
						StructureSet.entry(structureHolderGetter.getOrThrow(ModStructures.UNDERGROUND_VILLAGE))
				),
				new RandomSpreadStructurePlacement(32, 7, RandomSpreadType.LINEAR, 11841195)));
	}
}
