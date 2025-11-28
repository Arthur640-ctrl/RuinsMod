package fr.ruins.mod.procedures;

import fr.ruins.mod.blocks.BarbedWireT1Block;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;

public class OnTickBarbedWireBlockT1 {

    public static void execute(BlockState state, Level level, BlockPos pos, boolean dump) {
        if (!level.isClientSide && state.getValue(BarbedWireT1Block.WATERLOGGED)) {

            double radius = 1.5D;
            AABB area = new AABB(
                    pos.getX() - radius, pos.getY() - radius, pos.getZ() - radius,
                    pos.getX() + 1 + radius, pos.getY() + 1 + radius, pos.getZ() + 1 + radius
            );

            for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, area)) {

                BlockPos entityPos = entity.blockPosition();
                FluidState fluidAtEntity = level.getFluidState(entityPos);

                if (fluidAtEntity.getType() == Fluids.WATER) {

                    // Vérifie si l'eau de l'entité est **adjacente au barbelé**
                    boolean touchBarbedWire = false;
                    for (int dx = -1; dx <= 1 && !touchBarbedWire; dx++) {
                        for (int dy = -1; dy <= 1 && !touchBarbedWire; dy++) {
                            for (int dz = -1; dz <= 1 && !touchBarbedWire; dz++) {
                                BlockPos checkPos = entityPos.offset(dx, dy, dz);
                                if (checkPos.equals(pos)) { // l'eau touche le barbelé
                                    touchBarbedWire = true;
                                }
                            }
                        }
                    }

                    if (touchBarbedWire) {
                        entity.hurt(DamageSource.CACTUS, 0.40F);
                    }

                    if (dump) {
                        entity.hurt(DamageSource.CACTUS, 3F);
                    }
                }
            }
        }
    }
}
