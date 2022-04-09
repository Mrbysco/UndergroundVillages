package com.mrbysco.undergroundvillages.feature.placement;

import com.mrbysco.undergroundvillages.UndergroundVillages;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.PileFeatures;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.compress.utils.Lists;

public class UndergroundVillagePlacements {
	public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, UndergroundVillages.MOD_ID);

	public static final RegistryObject<PlacedFeature> PILE_HAY_VILLAGE = PLACED_FEATURES.register("pile_hay", () -> new PlacedFeature(Holder.hackyErase(PileFeatures.PILE_HAY), Lists.newArrayList()));
	public static final RegistryObject<PlacedFeature> OAK_VILLAGE = PLACED_FEATURES.register("oak", () -> new PlacedFeature(Holder.hackyErase(TreeFeatures.OAK), Lists.newArrayList()));
	public static final RegistryObject<PlacedFeature> FLOWER_PLAIN_VILLAGE = PLACED_FEATURES.register("flower_plain", () -> new PlacedFeature(Holder.hackyErase(VegetationFeatures.FLOWER_PLAIN), Lists.newArrayList()));

//	public static final Holder<PlacedFeature> PILE_HAY_VILLAGE = PlacementUtils.register("pile_hay", PileFeatures.PILE_HAY);
//	public static final Holder<PlacedFeature> OAK_VILLAGE = PlacementUtils.register("oak", TreeFeatures.OAK, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING));
//	public static final Holder<PlacedFeature> FLOWER_PLAIN_VILLAGE = PlacementUtils.register("flower_plain", VegetationFeatures.FLOWER_PLAIN);


}
