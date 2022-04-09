package com.mrbysco.undergroundvillages.registry;

import com.google.common.collect.ImmutableList;
import com.mrbysco.undergroundvillages.UndergroundVillages;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.AlwaysTrueTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.ProcessorRule;
import net.minecraft.world.level.levelgen.structure.templatesystem.RandomBlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModStructureProcessorList {
	public static final DeferredRegister<StructureProcessorList> STRUCTURE_PROCESSORS = DeferredRegister.create(Registry.PROCESSOR_LIST_REGISTRY, UndergroundVillages.MOD_ID);

	public static final RegistryObject<StructureProcessorList> STREET_CAVE = STRUCTURE_PROCESSORS.register("street_cave", () -> new StructureProcessorList(
			ImmutableList.of(new RuleProcessor(ImmutableList.of(new ProcessorRule(new BlockMatchTest(Blocks.CALCITE), new BlockMatchTest(Blocks.WATER), Blocks.OAK_PLANKS.defaultBlockState()), new ProcessorRule(new RandomBlockMatchTest(Blocks.CALCITE, 0.1F), AlwaysTrueTest.INSTANCE, Blocks.GRASS_BLOCK.defaultBlockState()), new ProcessorRule(new BlockMatchTest(Blocks.GRASS_BLOCK), new BlockMatchTest(Blocks.WATER), Blocks.WATER.defaultBlockState()), new ProcessorRule(new BlockMatchTest(Blocks.DIRT), new BlockMatchTest(Blocks.WATER), Blocks.WATER.defaultBlockState()))))));
}
