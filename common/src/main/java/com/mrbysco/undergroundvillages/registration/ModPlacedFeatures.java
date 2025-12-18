package com.mrbysco.undergroundvillages.registration;

import com.google.common.collect.Lists;
import com.mrbysco.undergroundvillages.Constants;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.PileFeatures;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModPlacedFeatures {
	public static final ResourceKey<PlacedFeature> PILE_HAY_VILLAGE = createKey("pile_hay");
	public static final ResourceKey<PlacedFeature> OAK_VILLAGE = createKey("oak");
	public static final ResourceKey<PlacedFeature> FLOWER_PLAIN_VILLAGE = createKey("flower_plain");

	public static ResourceKey<PlacedFeature> createKey(String path) {
		return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(Constants.MOD_ID, path));
	}

	public static void bootstrap(BootstrapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);

		PlacementUtils.register(context, PILE_HAY_VILLAGE, holdergetter.getOrThrow(PileFeatures.PILE_HAY), Lists.newArrayList());
		PlacementUtils.register(context, OAK_VILLAGE, holdergetter.getOrThrow(TreeFeatures.OAK), Lists.newArrayList());
		PlacementUtils.register(context, FLOWER_PLAIN_VILLAGE, holdergetter.getOrThrow(VegetationFeatures.FLOWER_PLAIN), Lists.newArrayList());
	}
}
