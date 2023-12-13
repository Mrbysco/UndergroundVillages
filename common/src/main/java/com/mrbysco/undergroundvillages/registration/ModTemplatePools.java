package com.mrbysco.undergroundvillages.registration;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;

public class ModTemplatePools {
	public static final ResourceKey<StructureTemplatePool> UNDERGROUND_START = Pools.createKey("underground_villages:village/underground/town_centers");
	public static final ResourceKey<StructureTemplatePool> UNDERGROUND_TERMINATORS_KEY = Pools.createKey("underground_villages:village/underground/terminators");

	public static void bootstrap(BootstapContext<StructureTemplatePool> context) {
		HolderGetter<PlacedFeature> holdergetter = context.lookup(Registries.PLACED_FEATURE);
		Holder<PlacedFeature> oakVillageHolder = holdergetter.getOrThrow(ModPlacedFeatures.OAK_VILLAGE);
		Holder<PlacedFeature> flowerPlainHolder = holdergetter.getOrThrow(ModPlacedFeatures.FLOWER_PLAIN_VILLAGE);
		Holder<PlacedFeature> pileHayHolder = holdergetter.getOrThrow(ModPlacedFeatures.PILE_HAY_VILLAGE);
		HolderGetter<StructureProcessorList> processorListHolderGetter = context.lookup(Registries.PROCESSOR_LIST);
		Holder<StructureProcessorList> mossify10Holder = processorListHolderGetter.getOrThrow(ProcessorLists.MOSSIFY_10_PERCENT);
		Holder<StructureProcessorList> mossify20Holder = processorListHolderGetter.getOrThrow(ProcessorLists.MOSSIFY_20_PERCENT);
		Holder<StructureProcessorList> mossify70Holder = processorListHolderGetter.getOrThrow(ProcessorLists.MOSSIFY_70_PERCENT);
		Holder<StructureProcessorList> zombiePlainsHolder = processorListHolderGetter.getOrThrow(ProcessorLists.ZOMBIE_PLAINS);
		Holder<StructureProcessorList> streetCaveHolder = processorListHolderGetter.getOrThrow(ModProcessorLists.STREET_CAVE);
		Holder<StructureProcessorList> farmPlainsHolder = processorListHolderGetter.getOrThrow(ProcessorLists.FARM_PLAINS);
		HolderGetter<StructureTemplatePool> templateGetter = context.lookup(Registries.TEMPLATE_POOL);
		Holder<StructureTemplatePool> emptyPoolHolder = templateGetter.getOrThrow(Pools.EMPTY);
		Holder<StructureTemplatePool> terminatorPoolHolder = templateGetter.getOrThrow(UNDERGROUND_TERMINATORS_KEY);

		context.register(UNDERGROUND_START, new StructureTemplatePool(emptyPoolHolder,
				ImmutableList.of(
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/town_centers/fountain_01", mossify20Holder), 50),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/town_centers/meeting_point_1", mossify20Holder), 50),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/town_centers/meeting_point_2"), 50),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/town_centers/meeting_point_3", mossify70Holder), 50),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/town_centers/fountain_01", zombiePlainsHolder), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/town_centers/meeting_point_1", zombiePlainsHolder), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/town_centers/meeting_point_2", zombiePlainsHolder), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/town_centers/meeting_point_3", zombiePlainsHolder), 1)),
				StructureTemplatePool.Projection.RIGID));
		Pools.register(context, "underground_villages:village/underground/streets", new StructureTemplatePool(terminatorPoolHolder,
				ImmutableList.of(
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/streets/corner_01", streetCaveHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/streets/corner_02", streetCaveHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/streets/corner_03", streetCaveHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/streets/straight_01", streetCaveHolder), 4),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/streets/straight_02", streetCaveHolder), 4),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/streets/straight_03", streetCaveHolder), 7),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/streets/straight_04", streetCaveHolder), 7),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/streets/straight_05", streetCaveHolder), 3),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/streets/straight_06", streetCaveHolder), 4),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/streets/crossroad_01", streetCaveHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/streets/crossroad_02", streetCaveHolder), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/streets/crossroad_03", streetCaveHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/streets/crossroad_04", streetCaveHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/streets/crossroad_05", streetCaveHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/streets/crossroad_06", streetCaveHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/streets/turn_01", streetCaveHolder), 3)),
				StructureTemplatePool.Projection.RIGID));
		Pools.register(context, "underground_villages:village/underground/zombie/streets", new StructureTemplatePool(terminatorPoolHolder,
				ImmutableList.of(
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/streets/corner_01", streetCaveHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/streets/corner_02", streetCaveHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/streets/corner_03", streetCaveHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/streets/straight_01", streetCaveHolder), 4),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/streets/straight_02", streetCaveHolder), 4),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/streets/straight_03", streetCaveHolder), 7),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/streets/straight_04", streetCaveHolder), 7),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/streets/straight_05", streetCaveHolder), 3),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/streets/straight_06", streetCaveHolder), 4),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/streets/crossroad_01", streetCaveHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/streets/crossroad_02", streetCaveHolder), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/streets/crossroad_03", streetCaveHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/streets/crossroad_04", streetCaveHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/streets/crossroad_05", streetCaveHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/streets/crossroad_06", streetCaveHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/streets/turn_01", streetCaveHolder), 3)),
				StructureTemplatePool.Projection.RIGID));
		Pools.register(context, "underground_villages:village/underground/houses", new StructureTemplatePool(terminatorPoolHolder,
				ImmutableList.of(
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/small_house_1", mossify10Holder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/small_house_2", mossify10Holder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/small_house_3", mossify10Holder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/small_house_4", mossify10Holder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/small_house_5", mossify10Holder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/small_house_6", mossify10Holder), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/small_house_7", mossify10Holder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/small_house_8", mossify10Holder), 3),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/medium_house_1", mossify10Holder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/medium_house_2", mossify10Holder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/big_house_1", mossify10Holder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/butcher_shop_1", mossify10Holder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/butcher_shop_2", mossify10Holder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/tool_smith_1", mossify10Holder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/fletcher_house_1", mossify10Holder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/shepherds_house_1"), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/armorer_house_1", mossify10Holder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/fisher_cottage_1", mossify10Holder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/tannery_1", mossify10Holder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/cartographer_1", mossify10Holder), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/library_1", mossify10Holder), 5),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/library_2", mossify10Holder), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/masons_house_1", mossify10Holder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/weaponsmith_1", mossify10Holder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/temple_3", mossify10Holder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/temple_4", mossify10Holder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/stable_1", mossify10Holder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/stable_2"), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/large_farm_1", farmPlainsHolder), 4),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/small_farm_1", farmPlainsHolder), 4),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/animal_pen_1"), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/animal_pen_2"), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/animal_pen_3"), 5),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/accessory_1"), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/meeting_point_4", mossify70Holder), 3),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/meeting_point_5"), 1),
						Pair.of(StructurePoolElement.empty(), 10)),
				StructureTemplatePool.Projection.RIGID));
		Pools.register(context, "underground_villages:village/underground/zombie/houses", new StructureTemplatePool(terminatorPoolHolder,
				ImmutableList.of(
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/houses/small_house_1", zombiePlainsHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/houses/small_house_2", zombiePlainsHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/houses/small_house_3", zombiePlainsHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/houses/small_house_4", zombiePlainsHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/houses/small_house_5", zombiePlainsHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/houses/small_house_6", zombiePlainsHolder), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/houses/small_house_7", zombiePlainsHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/houses/small_house_8", zombiePlainsHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/houses/medium_house_1", zombiePlainsHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/houses/medium_house_2", zombiePlainsHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/houses/big_house_1", zombiePlainsHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/butcher_shop_1", zombiePlainsHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/houses/butcher_shop_2", zombiePlainsHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/tool_smith_1", zombiePlainsHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/houses/fletcher_house_1", zombiePlainsHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/houses/shepherds_house_1", zombiePlainsHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/armorer_house_1", zombiePlainsHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/fisher_cottage_1", zombiePlainsHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/tannery_1", zombiePlainsHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/cartographer_1", zombiePlainsHolder), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/library_1", zombiePlainsHolder), 3),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/library_2", zombiePlainsHolder), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/masons_house_1", zombiePlainsHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/weaponsmith_1", zombiePlainsHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/temple_3", zombiePlainsHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/temple_4", zombiePlainsHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/houses/stable_1", zombiePlainsHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/stable_2", zombiePlainsHolder), 2),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/large_farm_1", zombiePlainsHolder), 4),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/small_farm_1", zombiePlainsHolder), 4),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/animal_pen_1", zombiePlainsHolder), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/houses/animal_pen_2", zombiePlainsHolder), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/houses/animal_pen_3", zombiePlainsHolder), 5),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/houses/meeting_point_4", zombiePlainsHolder), 3),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/houses/meeting_point_5", zombiePlainsHolder), 1),
						Pair.of(StructurePoolElement.empty(), 10)),
				StructureTemplatePool.Projection.RIGID));

		context.register(UNDERGROUND_TERMINATORS_KEY, new StructureTemplatePool(emptyPoolHolder,
				ImmutableList.of(
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/terminators/terminator_01", streetCaveHolder), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/terminators/terminator_02", streetCaveHolder), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/terminators/terminator_03", streetCaveHolder), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/terminators/terminator_04", streetCaveHolder), 1)),
				StructureTemplatePool.Projection.RIGID));
		Pools.register(context, "underground_villages:village/underground/trees", new StructureTemplatePool(emptyPoolHolder,
				ImmutableList.of(
						Pair.of(StructurePoolElement.feature(oakVillageHolder), 1)),
				StructureTemplatePool.Projection.RIGID));
		Pools.register(context, "underground_villages:village/underground/decor", new StructureTemplatePool(emptyPoolHolder,
				ImmutableList.of(
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/lamp_1"), 2),
						Pair.of(StructurePoolElement.feature(oakVillageHolder), 1),
						Pair.of(StructurePoolElement.feature(flowerPlainHolder), 1),
						Pair.of(StructurePoolElement.feature(pileHayHolder), 1),
						Pair.of(StructurePoolElement.empty(), 2)),
				StructureTemplatePool.Projection.RIGID));
		Pools.register(context, "underground_villages:village/underground/zombie/decor", new StructureTemplatePool(emptyPoolHolder,
				ImmutableList.of(
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/lamp_1", zombiePlainsHolder), 1),
						Pair.of(StructurePoolElement.feature(oakVillageHolder), 1),
						Pair.of(StructurePoolElement.feature(flowerPlainHolder), 1),
						Pair.of(StructurePoolElement.feature(pileHayHolder), 1),
						Pair.of(StructurePoolElement.empty(), 2)),
				StructureTemplatePool.Projection.RIGID));
		Pools.register(context, "underground_villages:village/underground/villagers", new StructureTemplatePool(emptyPoolHolder,
				ImmutableList.of(
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/villagers/nitwit"), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/villagers/baby"), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/villagers/unemployed"), 10)),
				StructureTemplatePool.Projection.RIGID));
		Pools.register(context, "underground_villages:village/underground/zombie/villagers", new StructureTemplatePool(emptyPoolHolder,
				ImmutableList.of(
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/villagers/nitwit"), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/underground/zombie/villagers/unemployed"), 10)),
				StructureTemplatePool.Projection.RIGID));
		Pools.register(context, "underground_villages:village/common/animals", new StructureTemplatePool(emptyPoolHolder,
				ImmutableList.of(
						Pair.of(StructurePoolElement.legacy("underground_villages:village/common/animals/cows_1"), 7),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/common/animals/pigs_1"), 7),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/common/animals/horses_1"), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/common/animals/horses_2"), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/common/animals/horses_3"), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/common/animals/horses_4"), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/common/animals/horses_5"), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/common/animals/sheep_1"), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/common/animals/sheep_2"), 1),
						Pair.of(StructurePoolElement.empty(), 5)),
				StructureTemplatePool.Projection.RIGID));
		Pools.register(context, "underground_villages:village/common/sheep", new StructureTemplatePool(emptyPoolHolder,
				ImmutableList.of(
						Pair.of(StructurePoolElement.legacy("underground_villages:village/common/animals/sheep_1"), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/common/animals/sheep_2"), 1)),
				StructureTemplatePool.Projection.RIGID));
		Pools.register(context, "underground_villages:village/common/cats", new StructureTemplatePool(emptyPoolHolder,
				ImmutableList.of(
						Pair.of(StructurePoolElement.legacy("underground_villages:village/common/animals/cat_black"), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/common/animals/cat_british"), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/common/animals/cat_calico"), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/common/animals/cat_persian"), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/common/animals/cat_ragdoll"), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/common/animals/cat_red"), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/common/animals/cat_siamese"), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/common/animals/cat_tabby"), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/common/animals/cat_white"), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/common/animals/cat_jellie"), 1),
						Pair.of(StructurePoolElement.empty(), 3)),
				StructureTemplatePool.Projection.RIGID));
		Pools.register(context, "underground_villages:village/common/butcher_animals", new StructureTemplatePool(emptyPoolHolder,
				ImmutableList.of(
						Pair.of(StructurePoolElement.legacy("underground_villages:village/common/animals/cows_1"), 3),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/common/animals/pigs_1"), 3),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/common/animals/sheep_1"), 1),
						Pair.of(StructurePoolElement.legacy("underground_villages:village/common/animals/sheep_2"), 1)),
				StructureTemplatePool.Projection.RIGID));
		Pools.register(context, "underground_villages:village/common/iron_golem", new StructureTemplatePool(emptyPoolHolder,
				ImmutableList.of(
						Pair.of(StructurePoolElement.legacy("underground_villages:village/common/iron_golem"), 1)),
				StructureTemplatePool.Projection.RIGID));
		Pools.register(context, "underground_villages:village/common/well_bottoms", new StructureTemplatePool(emptyPoolHolder,
				ImmutableList.of(
						Pair.of(StructurePoolElement.legacy("underground_villages:village/common/well_bottom"), 1)),
				StructureTemplatePool.Projection.RIGID));
	}
}