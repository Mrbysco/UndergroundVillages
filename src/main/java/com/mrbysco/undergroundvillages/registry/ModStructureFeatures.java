package com.mrbysco.undergroundvillages.registry;

import com.mrbysco.undergroundvillages.UndergroundVillages;
import com.mrbysco.undergroundvillages.feature.UndergroundVillageFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModStructureFeatures {
	public static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, UndergroundVillages.MOD_ID);

	public static final RegistryObject<StructureFeature<JigsawConfiguration>> UNDERGROUND_VILLAGE = STRUCTURES.register("underground_village", () ->
			new UndergroundVillageFeature(JigsawConfiguration.CODEC));
}
