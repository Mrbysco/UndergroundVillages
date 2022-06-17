package com.mrbysco.undergroundvillages.registry;

import com.mrbysco.undergroundvillages.UndergroundVillages;
import com.mrbysco.undergroundvillages.height.ConfigHeight;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.heightproviders.HeightProviderType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModHeightProvider {
	public static final DeferredRegister<HeightProviderType<?>> HEIGHT_PROVIDERS = DeferredRegister.create(Registry.HEIGHT_PROVIDER_TYPE_REGISTRY, UndergroundVillages.MOD_ID);

	public static final RegistryObject<HeightProviderType<ConfigHeight>> CONFIG = HEIGHT_PROVIDERS.register("constant", () -> () -> ConfigHeight.CODEC);

}
