package com.mrbysco.undergroundvillages.registry;

import com.mrbysco.undergroundvillages.UndergroundVillages;
import com.mrbysco.undergroundvillages.height.ConfigHeight;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.heightproviders.HeightProviderType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModHeightProvider {
	public static final DeferredRegister<HeightProviderType<?>> HEIGHT_PROVIDERS = DeferredRegister.create(Registries.HEIGHT_PROVIDER_TYPE, UndergroundVillages.MOD_ID);

	public static final Supplier<HeightProviderType<ConfigHeight>> CONFIG = HEIGHT_PROVIDERS.register("constant", () -> () -> ConfigHeight.CODEC);

}
