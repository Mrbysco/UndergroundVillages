package com.mrbysco.undergroundvillages.height;

import com.mojang.serialization.MapCodec;
import com.mrbysco.undergroundvillages.config.UndergroundConfig;
import com.mrbysco.undergroundvillages.registration.ModHeightProvider;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.heightproviders.HeightProviderType;

public class ConfigHeight extends HeightProvider {
	public static final MapCodec<ConfigHeight> CODEC = MapCodec.unit(ConfigHeight::new);

	@Override
	public int sample(RandomSource randomSource, WorldGenerationContext generationContext) {
		return UndergroundConfig.COMMON.yLevel.get();
	}

	@Override
	public HeightProviderType<?> getType() {
		return ModHeightProvider.CONFIG.get();
	}
}