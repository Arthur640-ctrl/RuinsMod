package fr.ruins.mod.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class OnEntityInsideBarbedWireBlockT1 {

    public static void execute(BlockState state, Level level, BlockPos pos, Entity entity, boolean dump) {
        if (entity instanceof LivingEntity living) {
            living.makeStuckInBlock(state, new Vec3(0.1D, 0.05D, 0.1D));
            living.hurt(DamageSource.CACTUS, 0.40F);

            if (dump) {
                living.hurt(DamageSource.CACTUS, 3);
            }
        }

    }

}
