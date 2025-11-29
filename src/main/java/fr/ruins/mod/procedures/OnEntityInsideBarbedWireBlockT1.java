package fr.ruins.mod.procedures;

import fr.ruins.mod.registers.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class OnEntityInsideBarbedWireBlockT1 {

    public static void execute(BlockState state, Level level, BlockPos pos, Entity entity, boolean dump) {
        if (entity instanceof LivingEntity living) {
            living.makeStuckInBlock(state, new Vec3(0.1D, 0.05D, 0.1D));
            living.hurt(DamageSource.CACTUS, 0.40F);

            if (dump) {

                float baseDamage = 3;

                if (living instanceof Player player) {
                    ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
                    ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
                    ItemStack legs = player.getItemBySlot(EquipmentSlot.LEGS);
                    ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);

                    boolean hasHelmet = helmet.getItem() == ModItems.INSULATE_ARMOR_HELMET.get();
                    boolean hasChestplate = chestplate.getItem() == ModItems.INSULATE_ARMOR_CHESTPLATE.get();
                    boolean hasLegs = legs.getItem() == ModItems.INSULATE_ARMOR_LEGGINGS.get();
                    boolean hasBoots = boots.getItem() == ModItems.INSULATE_ARMOR_BOOTS.get();

                    if (hasHelmet == true) {
                        baseDamage -= 0.25;
                    }

                    if (hasBoots == true) {
                        baseDamage -= 0.25;
                    }

                    if (hasChestplate == true) {
                        baseDamage -= 1.5;
                    }

                    if (hasLegs == true) {
                        baseDamage -= 1;
                    }
                }

                living.hurt(DamageSource.CACTUS, baseDamage);
            }
        }

    }

}
