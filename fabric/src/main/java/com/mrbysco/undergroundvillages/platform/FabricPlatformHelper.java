package com.mrbysco.undergroundvillages.platform;

import com.mrbysco.undergroundvillages.UndergroundVillagesFabric;
import com.mrbysco.undergroundvillages.platform.services.IPlatformHelper;

public class FabricPlatformHelper implements IPlatformHelper {
	@Override
	public int getYLevel() {
		return UndergroundVillagesFabric.config.get().generation.yLevel;
	}
}
