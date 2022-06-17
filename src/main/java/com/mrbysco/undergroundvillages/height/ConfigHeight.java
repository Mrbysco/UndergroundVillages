package com.mrbysco.undergroundvillages.height;

import com.mojang.serialization.Codec;
import com.mrbysco.undergroundvillages.config.UndergroundConfig;
import com.mrbysco.undergroundvillages.registry.ModHeightProvider;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.heightproviders.HeightProviderType;

public class ConfigHeight extends HeightProvider {
	public static final Codec<ConfigHeight> CODEC = Codec.unit(ConfigHeight::new);

	@Override
	public int sample(RandomSource randomSource, WorldGenerationContext generationContext) {
		return UndergroundConfig.COMMON.yLevel.get();
	}

	@Override
	public HeightProviderType<?> getType() {
		return ModHeightProvider.CONFIG.get();
	}
}