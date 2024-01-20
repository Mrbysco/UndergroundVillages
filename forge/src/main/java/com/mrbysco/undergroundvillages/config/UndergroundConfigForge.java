package com.mrbysco.undergroundvillages.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.IntValue;
import org.apache.commons.lang3.tuple.Pair;

public class UndergroundConfigForge {
	public static class Common {
		public final IntValue yLevel;

		Common(ModConfigSpec.Builder builder) {
			builder.comment("Generation settings")
					.push("Generation");

			yLevel = builder
					.comment("The Y level it'll try to generate villages at [default: -20]")
					.defineInRange("yLevel", -20, Integer.MIN_VALUE, Integer.MAX_VALUE);

			builder.pop();
		}
	}

	public static final ModConfigSpec commonSpec;
	public static final Common COMMON;

	static {
		final Pair<Common, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(Common::new);
		commonSpec = specPair.getRight();
		COMMON = specPair.getLeft();
	}
}
