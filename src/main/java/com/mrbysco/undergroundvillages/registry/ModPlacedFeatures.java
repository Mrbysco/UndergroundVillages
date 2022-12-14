package com.mrbysco.undergroundvillages.registry;

import com.google.common.collect.Lists;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.PileFeatures;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModPlacedFeatures {
	public static final ResourceKey<PlacedFeature> PILE_HAY_VILLAGE = PlacementUtils.createKey("underground_villages:pile_hay");
	public static final ResourceKey<PlacedFeature> OAK_VILLAGE = PlacementUtils.createKey("underground_villages:oak");
	public static final ResourceKey<PlacedFeature> FLOWER_PLAIN_VILLAGE = PlacementUtils.createKey("underground_villages:flower_plain");

	public static void bootstrap(BootstapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);

		PlacementUtils.register(context, PILE_HAY_VILLAGE, holdergetter.getOrThrow(PileFeatures.PILE_HAY), Lists.newArrayList());
		PlacementUtils.register(context, OAK_VILLAGE, holdergetter.getOrThrow(TreeFeatures.OAK), Lists.newArrayList());
		PlacementUtils.register(context, FLOWER_PLAIN_VILLAGE, holdergetter.getOrThrow(VegetationFeatures.FLOWER_PLAIN), Lists.newArrayList());
	}
}
