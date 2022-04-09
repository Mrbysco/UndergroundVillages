package com.mrbysco.undergroundvillages.jigsaw;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.JigsawBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pools.EmptyPoolElement;
import net.minecraft.world.level.levelgen.structure.pools.JigsawJunction;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.apache.commons.lang3.mutable.MutableObject;
import org.slf4j.Logger;

import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.function.Predicate;

public class CustomJigsawPlacement {
	static final Logger LOGGER = LogUtils.getLogger();

	public static Optional<PieceGenerator<JigsawConfiguration>> addPieces(PieceGeneratorSupplier.Context<JigsawConfiguration> context, CustomJigsawPlacement.PieceFactory factory, BlockPos pos, boolean p_210288_, boolean p_210289_) {
		WorldgenRandom worldgenrandom = new WorldgenRandom(new LegacyRandomSource(0L));
		worldgenrandom.setLargeFeatureSeed(context.seed(), context.chunkPos().x, context.chunkPos().z);
		RegistryAccess registryaccess = context.registryAccess();
		JigsawConfiguration jigsawconfiguration = context.config();
		ChunkGenerator chunkgenerator = context.chunkGenerator();
		StructureManager structuremanager = context.structureManager();
		LevelHeightAccessor levelheightaccessor = context.heightAccessor();
		Predicate<Holder<Biome>> predicate = context.validBiome();
		StructureFeature.bootstrap();
		Registry<StructureTemplatePool> registry = registryaccess.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY);
		Rotation rotation = Rotation.getRandom(worldgenrandom);
		StructureTemplatePool structuretemplatepool = jigsawconfiguration.startPool().value();
		StructurePoolElement structurepoolelement = structuretemplatepool.getRandomTemplate(worldgenrandom);
		if (structurepoolelement == EmptyPoolElement.INSTANCE) {
			return Optional.empty();
		} else {
			PoolElementStructurePiece poolelementstructurepiece = factory.create(structuremanager, structurepoolelement, pos, structurepoolelement.getGroundLevelDelta(), rotation, structurepoolelement.getBoundingBox(structuremanager, pos, rotation));
			BoundingBox boundingbox = poolelementstructurepiece.getBoundingBox();
			int i = (boundingbox.maxX() + boundingbox.minX()) / 2;
			int j = (boundingbox.maxZ() + boundingbox.minZ()) / 2;
			int k = pos.getY();

			if (!predicate.test(chunkgenerator.getNoiseBiome(QuartPos.fromBlock(i), QuartPos.fromBlock(k), QuartPos.fromBlock(j)))) {
				return Optional.empty();
			} else {
				int l = boundingbox.minY() + poolelementstructurepiece.getGroundLevelDelta();
				poolelementstructurepiece.move(0, k - l, 0);
				return Optional.of((p_210282_, p_210283_) -> {
					List<PoolElementStructurePiece> list = Lists.newArrayList();
					list.add(poolelementstructurepiece);
					if (jigsawconfiguration.maxDepth() > 0) {
						int i1 = 80;
						AABB aabb = new AABB((double) (i - i1), (double) (k - i1), (double) (j - i1), (double) (i + i1 + 1), (double) (k + i1 + 1), (double) (j + i1 + 1));
						CustomJigsawPlacement.Placer jigsawplacement$placer = new CustomJigsawPlacement.Placer(registry, jigsawconfiguration.maxDepth(), factory, chunkgenerator, structuremanager, list, worldgenrandom);
						jigsawplacement$placer.placing.addLast(new CustomJigsawPlacement.PieceState(poolelementstructurepiece, new MutableObject<>(Shapes.join(Shapes.create(aabb), Shapes.create(AABB.of(boundingbox)), BooleanOp.ONLY_FIRST)), 0));

						while (!jigsawplacement$placer.placing.isEmpty()) {
							CustomJigsawPlacement.PieceState jigsawplacement$piecestate = jigsawplacement$placer.placing.removeFirst();
							jigsawplacement$placer.tryPlacingChildren(jigsawplacement$piecestate.piece, jigsawplacement$piecestate.free, jigsawplacement$piecestate.depth, p_210288_, levelheightaccessor);
						}

						list.forEach(p_210282_::addPiece);
					}
				});
			}
		}
	}

	public static void addPieces(RegistryAccess registryAccess, PoolElementStructurePiece piece, int maxDepth,
								 CustomJigsawPlacement.PieceFactory factory, ChunkGenerator chunkGenerator,
								 StructureManager structureManager, List<? super PoolElementStructurePiece> pieces, Random random, LevelHeightAccessor level) {
		Registry<StructureTemplatePool> registry = registryAccess.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY);
		CustomJigsawPlacement.Placer jigsawplacement$placer = new CustomJigsawPlacement.Placer(registry, maxDepth, factory, chunkGenerator, structureManager, pieces, random);
		jigsawplacement$placer.placing.addLast(new CustomJigsawPlacement.PieceState(piece, new MutableObject<>(Shapes.INFINITY), 0));

		while (!jigsawplacement$placer.placing.isEmpty()) {
			CustomJigsawPlacement.PieceState jigsawplacement$piecestate = jigsawplacement$placer.placing.removeFirst();
			jigsawplacement$placer.tryPlacingChildren(jigsawplacement$piecestate.piece, jigsawplacement$piecestate.free, jigsawplacement$piecestate.depth, false, level);
		}
	}

	public interface PieceFactory {
		PoolElementStructurePiece create(StructureManager structureManager, StructurePoolElement poolElement, BlockPos pos, int groundLevelDelta, Rotation rotation, BoundingBox boundingBox);
	}

	static final class PieceState {
		final PoolElementStructurePiece piece;
		final MutableObject<VoxelShape> free;
		final int depth;

		PieceState(PoolElementStructurePiece piece, MutableObject<VoxelShape> free, int depth) {
			this.piece = piece;
			this.free = free;
			this.depth = depth;
		}
	}

	static final class Placer {
		private final Registry<StructureTemplatePool> pools;
		private final int maxDepth;
		private final CustomJigsawPlacement.PieceFactory factory;
		private final ChunkGenerator chunkGenerator;
		private final StructureManager structureManager;
		private final List<? super PoolElementStructurePiece> pieces;
		private final Random random;
		final Deque<CustomJigsawPlacement.PieceState> placing = Queues.newArrayDeque();

		Placer(Registry<StructureTemplatePool> pools, int maxDepth, CustomJigsawPlacement.PieceFactory factory, ChunkGenerator chunkGenerator, StructureManager structureManager, List<? super PoolElementStructurePiece> pieces, Random random) {
			this.pools = pools;
			this.maxDepth = maxDepth;
			this.factory = factory;
			this.chunkGenerator = chunkGenerator;
			this.structureManager = structureManager;
			this.pieces = pieces;
			this.random = random;
		}

		void tryPlacingChildren(PoolElementStructurePiece structurePiece, MutableObject<VoxelShape> p_210335_, int p_210336_, boolean p_210337_, LevelHeightAccessor level) {
			StructurePoolElement structurepoolelement = structurePiece.getElement();
			BlockPos blockpos = structurePiece.getPosition();
			Rotation rotation = structurePiece.getRotation();
			StructureTemplatePool.Projection structuretemplatepool$projection = structurepoolelement.getProjection();
			MutableObject<VoxelShape> mutableobject = new MutableObject<>();
			BoundingBox boundingbox = structurePiece.getBoundingBox();
			int i = boundingbox.minY();

			label139:
			for (StructureTemplate.StructureBlockInfo structuretemplate$structureblockinfo : structurepoolelement.getShuffledJigsawBlocks(this.structureManager, blockpos, rotation, this.random)) {
				Direction direction = JigsawBlock.getFrontFacing(structuretemplate$structureblockinfo.state);
				BlockPos blockpos1 = structuretemplate$structureblockinfo.pos;
				BlockPos blockpos2 = blockpos1.relative(direction);
				int j = blockpos1.getY() - i;
				int k = -1;
				ResourceLocation resourcelocation = new ResourceLocation(structuretemplate$structureblockinfo.nbt.getString("pool"));
				Optional<StructureTemplatePool> optional = this.pools.getOptional(resourcelocation);
				if (optional.isPresent() && (optional.get().size() != 0 || Objects.equals(resourcelocation, Pools.EMPTY.location()))) {
					ResourceLocation resourcelocation1 = optional.get().getFallback();
					Optional<StructureTemplatePool> optional1 = this.pools.getOptional(resourcelocation1);
					if (optional1.isPresent() && (optional1.get().size() != 0 || Objects.equals(resourcelocation1, Pools.EMPTY.location()))) {
						boolean flag1 = boundingbox.isInside(blockpos2);
						MutableObject<VoxelShape> mutableobject1;
						if (flag1) {
							mutableobject1 = mutableobject;
							if (mutableobject.getValue() == null) {
								mutableobject.setValue(Shapes.create(AABB.of(boundingbox)));
							}
						} else {
							mutableobject1 = p_210335_;
						}

						List<StructurePoolElement> list = Lists.newArrayList();
						if (p_210336_ != this.maxDepth) {
							list.addAll(optional.get().getShuffledTemplates(this.random));
						}

						list.addAll(optional1.get().getShuffledTemplates(this.random));

						for (StructurePoolElement structurepoolelement1 : list) {
							if (structurepoolelement1 == EmptyPoolElement.INSTANCE) {
								break;
							}

							for (Rotation rotation1 : Rotation.getShuffled(this.random)) {
								List<StructureTemplate.StructureBlockInfo> list1 = structurepoolelement1.getShuffledJigsawBlocks(this.structureManager, BlockPos.ZERO, rotation1, this.random);
								BoundingBox boundingbox1 = structurepoolelement1.getBoundingBox(this.structureManager, BlockPos.ZERO, rotation1);
								int l;
								if (boundingbox1.getYSpan() <= 16) {
									l = list1.stream().mapToInt((structureBlockInfo) -> {
										if (!boundingbox1.isInside(structureBlockInfo.pos.relative(JigsawBlock.getFrontFacing(structureBlockInfo.state)))) {
											return 0;
										} else {
											ResourceLocation resourcelocation2 = new ResourceLocation(structureBlockInfo.nbt.getString("pool"));
											Optional<StructureTemplatePool> optional2 = this.pools.getOptional(resourcelocation2);
											Optional<StructureTemplatePool> optional3 = optional2.flatMap((pool) -> {
												return this.pools.getOptional(pool.getFallback());
											});
											int j3 = optional2.map((pool) -> {
												return pool.getMaxSize(this.structureManager);
											}).orElse(0);
											int k3 = optional3.map((pool) -> {
												return pool.getMaxSize(this.structureManager);
											}).orElse(0);
											return Math.max(j3, k3);
										}
									}).max().orElse(0);
								} else {
									l = 0;
								}

								for (StructureTemplate.StructureBlockInfo structuretemplate$structureblockinfo1 : list1) {
									if (JigsawBlock.canAttach(structuretemplate$structureblockinfo, structuretemplate$structureblockinfo1)) {
										BlockPos blockpos3 = structuretemplate$structureblockinfo1.pos;
										BlockPos blockpos4 = blockpos2.subtract(blockpos3);
										BoundingBox boundingbox2 = structurepoolelement1.getBoundingBox(this.structureManager, blockpos4, rotation1);
										int i1 = boundingbox2.minY();
										StructureTemplatePool.Projection structuretemplatepool$projection1 = structurepoolelement1.getProjection();
										int j1 = blockpos3.getY();
										int k1 = j - j1 + JigsawBlock.getFrontFacing(structuretemplate$structureblockinfo.state).getStepY();
										int l1 = i + k1;

										int i2 = l1 - i1;
										BoundingBox boundingbox3 = boundingbox2.moved(0, i2, 0);
										BlockPos blockpos5 = blockpos4.offset(0, i2, 0);
										if (l > 0) {
											int j2 = Math.max(l + 1, boundingbox3.maxY() - boundingbox3.minY());
											boundingbox3.encapsulate(new BlockPos(boundingbox3.minX(), boundingbox3.minY() + j2, boundingbox3.minZ()));
										}

										if (!Shapes.joinIsNotEmpty(mutableobject1.getValue(), Shapes.create(AABB.of(boundingbox3).deflate(0.25D)), BooleanOp.ONLY_SECOND)) {
											mutableobject1.setValue(Shapes.joinUnoptimized(mutableobject1.getValue(), Shapes.create(AABB.of(boundingbox3)), BooleanOp.ONLY_FIRST));
											int i3 = structurePiece.getGroundLevelDelta();
											int k2 = i3 - k1;

											PoolElementStructurePiece poolelementstructurepiece = this.factory.create(this.structureManager, structurepoolelement1, blockpos5, k2, rotation1, boundingbox3);
											int l2;
											if (k == -1) {
												k = blockpos1.getY() + getRandomBetween(random, -3, 3);
											}

											l2 = k + k1 / 2;

											structurePiece.addJunction(new JigsawJunction(blockpos2.getX(), l2 - j + i3, blockpos2.getZ(), k1, structuretemplatepool$projection1));
											poolelementstructurepiece.addJunction(new JigsawJunction(blockpos1.getX(), l2 - j1 + k2, blockpos1.getZ(), -k1, structuretemplatepool$projection));
											this.pieces.add(poolelementstructurepiece);
											if (p_210336_ + 1 <= this.maxDepth) {
												this.placing.addLast(new CustomJigsawPlacement.PieceState(poolelementstructurepiece, mutableobject1, p_210336_ + 1));
											}
											continue label139;
										}
									}
								}
							}
						}
					} else {
						CustomJigsawPlacement.LOGGER.warn("Empty or non-existent fallback pool: {}", (Object) resourcelocation1);
					}
				} else {
					CustomJigsawPlacement.LOGGER.warn("Empty or non-existent pool: {}", (Object) resourcelocation);
				}
			}

		}
	}

	public static int getRandomBetween(Random random, int min, int max) {
		OptionalInt randomNumber = random.ints(min, (max + 1)).findFirst();
		return (randomNumber.isPresent() ? randomNumber.getAsInt() : min);
	}
}
