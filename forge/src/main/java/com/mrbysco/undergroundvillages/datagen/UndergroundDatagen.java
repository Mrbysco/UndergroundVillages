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
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.data.tags.StructureTagsProvider;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class UndergroundDatagen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		ExistingFileHelper helper = event.getExistingFileHelper();
		CompletableFuture<HolderLookup.Provider> lookupProvider = CompletableFuture.supplyAsync(UndergroundDatagen::getProvider);

		generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(
				packOutput, lookupProvider, Set.of(Constants.MOD_ID)));

		generator.addProvider(event.includeServer(), new UndergroundStructureFeatureTagProvider(packOutput, lookupProvider, helper));
		generator.addProvider(event.includeServer(), new UndergroundBiomeTagProvider(packOutput, lookupProvider, helper));
	}


	private static HolderLookup.Provider getProvider() {
		final RegistrySetBuilder registryBuilder = new RegistrySetBuilder();
		registryBuilder.add(Registries.CONFIGURED_FEATURE, context -> {
		});
		registryBuilder.add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
		registryBuilder.add(Registries.PROCESSOR_LIST, ModProcessorLists::bootstrap);
		registryBuilder.add(Registries.STRUCTURE, ModStructures::bootstrap);
		registryBuilder.add(Registries.STRUCTURE_SET, ModStructureSets::bootstrap);
		registryBuilder.add(Registries.TEMPLATE_POOL, ModTemplatePools::bootstrap);
		// We need the BIOME registry to be present, so we can use a biome tag, doesn't matter that it's empty
		registryBuilder.add(Registries.BIOME, context -> {
		});
		RegistryAccess.Frozen regAccess = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);
		return registryBuilder.buildPatch(regAccess, VanillaRegistries.createLookup());
	}

	public static class UndergroundStructureFeatureTagProvider extends StructureTagsProvider {
		public UndergroundStructureFeatureTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
			super(packOutput, lookupProvider, Constants.MOD_ID, existingFileHelper);
		}

		@Override
		protected void addTags(HolderLookup.Provider provider) {
			this.tag(ConfiguredUndergroundStructureTags.UNDERGROUND_VILLAGE)
					.add(ModStructures.UNDERGROUND_VILLAGE);
		}

		public String getName() {
			return "Configured Underground Structure Feature Tags";
		}
	}

	public static class UndergroundBiomeTagProvider extends BiomeTagsProvider {
		public UndergroundBiomeTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
			super(packOutput, lookupProvider, Constants.MOD_ID, existingFileHelper);
		}

		@Override
		protected void addTags(HolderLookup.Provider provider) {
			this.tag(UndergroundBiomeTags.HAS_VILLAGE_UNDERGROUND)
					.add(Biomes.DESERT, Biomes.PLAINS, Biomes.MEADOW, Biomes.SAVANNA, Biomes.SNOWY_PLAINS, Biomes.TAIGA);
		}
	}
}
