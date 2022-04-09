package com.mrbysco.undergroundvillages.feature;

import com.mojang.serialization.Codec;
import com.mrbysco.undergroundvillages.config.UndergroundConfig;
import com.mrbysco.undergroundvillages.jigsaw.CustomJigsawPlacement;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.feature.JigsawFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier.Context;

import java.util.Optional;
import java.util.function.Predicate;

public class UndergroundVillageFeature extends JigsawFeature {
	public UndergroundVillageFeature(Codec<JigsawConfiguration> configurationCodec) {
		super(configurationCodec, -30, true, false, (context) -> true);
	}

	@Override
	public PieceGeneratorSupplier<JigsawConfiguration> pieceGeneratorSupplier() {
		Predicate<Context<JigsawConfiguration>> predicate = (context) -> true;

		return (configurationContext) -> {
			if (!predicate.test(configurationContext)) {
				return Optional.empty();
			} else {
				BlockPos blockpos = new BlockPos(configurationContext.chunkPos().getMinBlockX(), UndergroundConfig.COMMON.yLevel.get(), configurationContext.chunkPos().getMinBlockZ());
				Pools.bootstrap();
				return CustomJigsawPlacement.addPieces(configurationContext, PoolElementStructurePiece::new, blockpos, false, false);
			}
		};
	}

	@Override
	public Decoration step() {
		return Decoration.UNDERGROUND_STRUCTURES;
	}
}
