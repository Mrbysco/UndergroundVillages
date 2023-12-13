package com.mrbysco.undergroundvillages.platform;

import com.mrbysco.undergroundvillages.config.UndergroundConfigForge;
import com.mrbysco.undergroundvillages.platform.services.IPlatformHelper;

public class ForgePlatformHelper implements IPlatformHelper {

	@Override
	public int getYLevel() {
		return UndergroundConfigForge.COMMON.yLevel.get();
	}
}
