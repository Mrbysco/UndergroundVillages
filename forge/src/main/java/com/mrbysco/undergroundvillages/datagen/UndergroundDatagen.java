package com.mrbysco.undergroundvillages.datagen;

import com.mrbysco.undergroundvillages.Constants;
import com.mrbysco.undergroundvillages.feature.ConfiguredUndergroundStructureTags;
import com.mrbysco.undergroundvillages.registration.ModPlacedFeatures;
import com.mrbysco.undergroundvillages.registration.ModProcessorLists;
import com.mrbysco.undergroundvillages.registration.ModStructureSets;
import com.mrbysco.undergroundvillages.registration.ModStructures;
import com.mrbysco.undergroundvillages.registration.ModTemplatePools;
import com.mrbysco.undergroundvillages.util.UndergroundBiomeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.data.tags.StructureTagsProvider;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber
public class UndergroundDatagen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent.Client event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();

		event.createDatapackRegistryObjects(BUILDER);
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

		generator.addProvider(true, new UndergroundStructureFeatureTagProvider(packOutput, lookupProvider));
		generator.addProvider(true, new UndergroundBiomeTagProvider(packOutput, lookupProvider));
		generator.addProvider(true, new UndergroundLanguageProvider(packOutput));

		generator.addProvider(true, new StructureUpdater("structure/village", packOutput, event.getResourceManager(PackType.SERVER_DATA)));
	}

	public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
			.add(Registries.PROCESSOR_LIST, ModProcessorLists::bootstrap)
			.add(Registries.STRUCTURE, ModStructures::bootstrap)
			.add(Registries.STRUCTURE_SET, ModStructureSets::bootstrap)
			.add(Registries.TEMPLATE_POOL, ModTemplatePools::bootstrap);

	private static class UndergroundLanguageProvider extends LanguageProvider {
		public UndergroundLanguageProvider(PackOutput packOutput) {
			super(packOutput, Constants.MOD_ID, "en_us");
		}

		@Override
		protected void addTranslations() {
			this.addConfig("Generation", "Generation", "Generation Settings");
			this.addConfig("yLevel", "Y Level", "The Y level it'll try to generate villages at");

			this.add("text.autoconfig.underground_villages.title", "Underground Villages");
			this.add("text.autoconfig.underground_villages.option.generation", "Generation");
			this.add("text.autoconfig.underground_villages.option.generation.yLevel", "Y Level");
		}

		private void addConfig(String path, String name, @Nullable String description) {
			this.add(Constants.MOD_ID + ".configuration." + path, name);
			if (description != null && !description.isEmpty())
				this.add(Constants.MOD_ID + ".configuration." + path + ".tooltip", description);
		}
	}

	public static class UndergroundStructureFeatureTagProvider extends StructureTagsProvider {
		public UndergroundStructureFeatureTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
			super(packOutput, lookupProvider, Constants.MOD_ID);
		}

		@Override
		protected void addTags(HolderLookup.Provider provider) {
			this.tag(ConfiguredUndergroundStructureTags.UNDERGROUND_VILLAGE)
					.add(ModStructures.UNDERGROUND_VILLAGE);
		}

		@Override
		public String getName() {
			return "Configured Underground Structure Feature Tags";
		}
	}

	public static class UndergroundBiomeTagProvider extends BiomeTagsProvider {
		public UndergroundBiomeTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
			super(packOutput, lookupProvider, Constants.MOD_ID);
		}

		@Override
		protected void addTags(HolderLookup.Provider provider) {
			this.tag(UndergroundBiomeTags.HAS_VILLAGE_UNDERGROUND)
					.add(Biomes.DESERT, Biomes.PLAINS, Biomes.MEADOW, Biomes.SAVANNA, Biomes.SNOWY_PLAINS, Biomes.TAIGA);
		}
	}
}
