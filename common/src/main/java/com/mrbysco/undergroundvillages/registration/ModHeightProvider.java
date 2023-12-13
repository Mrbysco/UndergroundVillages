package com.mrbysco.undergroundvillages.registration;

import com.mrbysco.undergroundvillages.Constants;
import com.mrbysco.undergroundvillages.height.ConfigHeight;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.heightproviders.HeightProviderType;

public class ModHeightProvider {
	public static final RegistrationProvider<HeightProviderType<?>> HEIGHT_PROVIDERS = RegistrationProvider.get(Registries.HEIGHT_PROVIDER_TYPE, Constants.MOD_ID);

	public static final RegistryObject<HeightProviderType<ConfigHeight>> CONFIG = HEIGHT_PROVIDERS.register("constant", () -> () -> ConfigHeight.CODEC);

	// Called in the mod initializer / constructor in order to make sure that items are registered
	public static void loadClass() {
	}
}
