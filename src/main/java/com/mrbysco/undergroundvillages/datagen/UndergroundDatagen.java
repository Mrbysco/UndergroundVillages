package com.mrbysco.undergroundvillages.datagen;

import com.mrbysco.undergroundvillages.UndergroundVillages;
import com.mrbysco.undergroundvillages.feature.ConfiguredUndergroundStructureTags;
import com.mrbysco.undergroundvillages.registry.ModConfiguredStructureFeatures;
import com.mrbysco.undergroundvillages.util.UndergroundBiomeTags;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class UndergroundDatagen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper helper = event.getExistingFileHelper();

		generator.addProvider(new UndergroundStructureFeatureTagProvider(generator, helper));
		generator.addProvider(new UndergroundBiomeTagProvider(generator, helper));
	}

	public static class UndergroundStructureFeatureTagProvider extends TagsProvider<ConfiguredStructureFeature<?, ?>> {
		public UndergroundStructureFeatureTagProvider(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
			super(generator, BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, UndergroundVillages.MOD_ID, existingFileHelper);
		}

		@Override
		protected void addTags() {
			this.tag(ConfiguredUndergroundStructureTags.UNDERGROUND_VILLAGE)
					.add(ModConfiguredStructureFeatures.UNDERGROUND_VILLAGE.getKey());
		}

		public String getName() {
			return "Configured Underground Structure Feature Tags";
		}
	}

	public static class UndergroundBiomeTagProvider extends BiomeTagsProvider {
		public UndergroundBiomeTagProvider(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
			super(generator, UndergroundVillages.MOD_ID, existingFileHelper);
		}

		@Override
		protected void addTags() {
			this.tag(UndergroundBiomeTags.HAS_VILLAGE_UNDERGROUND)
					.add(Biomes.DESERT, Biomes.PLAINS, Biomes.MEADOW, Biomes.SAVANNA, Biomes.SNOWY_PLAINS, Biomes.TAIGA);
		}
	}
}
