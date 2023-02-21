package com.mrbysco.undergroundvillages.datagen;

import com.mrbysco.undergroundvillages.UndergroundVillages;
import com.mrbysco.undergroundvillages.feature.ConfiguredUndergroundStructureTags;
import com.mrbysco.undergroundvillages.registry.ModConfiguredStructureFeatures;
import com.mrbysco.undergroundvillages.util.UndergroundBiomeTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.data.tags.StructureTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class UndergroundDatagen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper helper = event.getExistingFileHelper();

		generator.addProvider(event.includeServer(), new UndergroundStructureFeatureTagProvider(generator, helper));
		generator.addProvider(event.includeServer(), new UndergroundBiomeTagProvider(generator, helper));
	}

	public static class UndergroundStructureFeatureTagProvider extends StructureTagsProvider {
		public UndergroundStructureFeatureTagProvider(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
			super(generator, UndergroundVillages.MOD_ID, existingFileHelper);
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
					.addTags(Tags.Biomes.IS_DESERT, Tags.Biomes.IS_PLAINS, BiomeTags.IS_SAVANNA, BiomeTags.IS_TAIGA);
		}
	}
}
