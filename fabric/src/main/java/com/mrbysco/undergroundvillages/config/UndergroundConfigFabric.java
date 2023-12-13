package com.mrbysco.undergroundvillages.config;

import com.mrbysco.undergroundvillages.Constants;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.CollapsibleObject;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = Constants.MOD_ID)
public class UndergroundConfigFabric implements ConfigData {

	@CollapsibleObject
	public Generation generation = new Generation();

	public static class Generation {
		//General
		@Comment("The Y level it'll try to generate villages at [default: -20]")
		public int yLevel = -20;
	}
}