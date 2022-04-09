package com.mrbysco.undergroundvillages.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import org.apache.commons.lang3.tuple.Pair;

public class UndergroundConfig {
	public static class Common {
		public final IntValue yLevel;

		Common(ForgeConfigSpec.Builder builder) {
			builder.comment("Generation settings")
					.push("Generation");

			yLevel = builder
					.comment("The Y level it'll try to generate villages at [default: -20]")
					.defineInRange("yLevel", -20, Integer.MIN_VALUE, Integer.MAX_VALUE);

			builder.pop();
		}
	}

	public static final ForgeConfigSpec commonSpec;
	public static final Common COMMON;

	static {
		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		commonSpec = specPair.getRight();
		COMMON = specPair.getLeft();
	}
}
