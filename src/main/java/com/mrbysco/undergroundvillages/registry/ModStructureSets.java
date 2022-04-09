package com.mrbysco.undergroundvillages.registry;

import com.mrbysco.undergroundvillages.UndergroundVillages;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModStructureSets {
	public static final DeferredRegister<StructureSet> STRUCTURE_SETS = DeferredRegister.create(Registry.STRUCTURE_SET_REGISTRY, UndergroundVillages.MOD_ID);

	public static final RegistryObject<StructureSet> UNDERGROUND_VILLAGES = STRUCTURE_SETS.register("underground_villages", () ->
			new StructureSet(List.of(StructureSet.entry(ModConfiguredStructureFeatures.UNDERGROUND_VILLAGE.getHolder().get())),
					new RandomSpreadStructurePlacement(32, 7, RandomSpreadType.LINEAR, 11841195)));
}
