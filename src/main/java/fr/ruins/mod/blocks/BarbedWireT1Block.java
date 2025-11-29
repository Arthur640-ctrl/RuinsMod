package fr.ruins.mod.blocks;

import com.mojang.math.Vector3f;
import fr.ruins.mod.procedures.OnEntityInsideBarbedWireBlock;
import fr.ruins.mod.procedures.OnEntityInsideBarbedWireBlockT1;
import fr.ruins.mod.procedures.OnTickBarbedWireBlockT1;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;

import java.util.Random;

public class BarbedWireT1Block extends Block implements SimpleWaterloggedBlock {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public BarbedWireT1Block() {
        super(BlockBehaviour.Properties
                .of(Material.METAL)
                .sound(SoundType.CHAIN)
                .noCollission()
                .noOcclusion());

        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }

    @Override
    public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
        super.onPlace(blockstate, world, pos, oldState, moving);
        world.scheduleTick(pos, this, 10);
    }


    @Override
    public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, Random random) {
        double randomNumber = Math.random();
        boolean dump = false;
        if (randomNumber >= 0.60) {
            dump = true;
        }

        OnTickBarbedWireBlockT1.execute(blockstate, world, pos, dump);

        world.scheduleTick(pos, this, 10);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        double randomNumber = Math.random();
        boolean dump = false;
        if (randomNumber >= 0.98) {
            dump = true;
        }

        OnEntityInsideBarbedWireBlockT1.execute(state, level, pos, entity, dump);
        super.entityInside(state, level, pos, entity);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, Random random) {
        if (!level.isClientSide) return;

        // --- Particules normales (bleues / électriques) ---
        for (int i = 0; i < 3; i++) {
            double x = pos.getX() + 0.2 + random.nextDouble() * 0.6;
            double y = pos.getY() + 0.1 + random.nextDouble() * 0.8;
            double z = pos.getZ() + 0.2 + random.nextDouble() * 0.6;

            level.addParticle(
                    new DustParticleOptions(new Vector3f(0.0f, 0.7f, 1.0f), 1.0f),
                    x, y, z,
                    0, 0.01, 0
            );

        }

        // --- Étincelle électrique rare ---
        if (random.nextFloat() < 0.12f) { // 12% de chance par tick
            double x = pos.getX() + random.nextDouble();
            double y = pos.getY() + random.nextDouble();
            double z = pos.getZ() + random.nextDouble();

            level.addParticle(
                    ParticleTypes.ELECTRIC_SPARK,
                    x, y, z,
                    0.0D,
                    0.05D,
                    0.0D
            );

            // Son d'étincelle électrique
//             level.playLocalSound(
//                     x, y, z,
//                     net.minecraft.sounds.SoundEvents.,
//                     net.minecraft.sounds.SoundSource.BLOCKS,
//                     0.4F,                // volume
//                     0.8F + random.nextFloat() * 0.4F, // pitch random
//                     false
//             );
        }

        // --- Petit bruit d'électricité constant ---
        if (random.nextFloat() < 0.02f) { // bruit très rare
            level.playLocalSound(
                    pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                    net.minecraft.sounds.SoundEvents.REDSTONE_TORCH_BURNOUT,
                    net.minecraft.sounds.SoundSource.BLOCKS,
                    0.15F,
                    2.0F + random.nextFloat() * 0.2F,
                    false
            );
        }
    }

}
