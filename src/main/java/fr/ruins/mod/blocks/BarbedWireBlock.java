package fr.ruins.mod.blocks;

import com.mojang.math.Vector3f;
import fr.ruins.mod.items.BarbedWirePliers;
import fr.ruins.mod.procedures.OnEntityInsideBarbedWireBlock;
import fr.ruins.mod.registers.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
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

public class BarbedWireBlock extends Block implements SimpleWaterloggedBlock {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public BarbedWireBlock() {
        super(BlockBehaviour.Properties
                .of(Material.METAL)
                .sound(SoundType.CHAIN)
                .noCollission()
                .noOcclusion()
                .strength(3.5F)
        );

        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
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
        OnEntityInsideBarbedWireBlock.execute(state, level, pos, entity);
        super.entityInside(state, level, pos, entity);
    }

    @Override
    public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
        if (!world.isClientSide) {
            world.removeBlock(pos, false);

            ItemStack drop;
            ItemStack held = player.getMainHandItem();

            if (held.getItem() instanceof BarbedWirePliers) {
                drop = new ItemStack(ModItems.BARBED_WIRE_ITEM.get());
            } else {
                Random rand = new Random();
                int amount = 1 + rand.nextInt(3);

                drop = new ItemStack(ModItems.WIRE.get(), amount);
            }

            Block.popResource(world, pos, drop);
        }
    }

}
