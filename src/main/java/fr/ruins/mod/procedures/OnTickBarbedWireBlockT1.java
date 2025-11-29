package fr.ruins.mod.procedures;

import fr.ruins.mod.blocks.BarbedWireT1Block;
import fr.ruins.mod.registers.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class OnTickBarbedWireBlockT1 {

    private static final int MAX_WATER_SCAN = 4;

    public static void execute(BlockState state, Level level, BlockPos pos, boolean dump) {
        if (!level.isClientSide && state.getValue(BarbedWireT1Block.WATERLOGGED)) {

            double radius = 2.5D;
            AABB area = new AABB(
                    pos.getX() - radius, pos.getY() - radius, pos.getZ() - radius,
                    pos.getX() + 1 + radius, pos.getY() + 1 + radius, pos.getZ() + 1 + radius
            );

            for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, area)) {

                // 👉 Nouveau : check si le joueur est dans la MÊME eau connectée
                if (!isPlayerConnectedToWater(level, pos, entity)) {
                    continue; // si pas dans la même eau, skip
                }

                float baseDamage = 3;

                // 👉 Réduction avec ton armure isolante
                if (entity instanceof Player player) {
                    ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
                    ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
                    ItemStack legs = player.getItemBySlot(EquipmentSlot.LEGS);
                    ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);

                    boolean hasHelmet = helmet.getItem() == ModItems.INSULATE_ARMOR_HELMET.get();
                    boolean hasChestplate = chestplate.getItem() == ModItems.INSULATE_ARMOR_CHESTPLATE.get();
                    boolean hasLegs = legs.getItem() == ModItems.INSULATE_ARMOR_LEGGINGS.get();
                    boolean hasBoots = boots.getItem() == ModItems.INSULATE_ARMOR_BOOTS.get();

                    if (hasHelmet) baseDamage -= 0.25f;
                    if (hasBoots) baseDamage -= 0.25f;
                    if (hasChestplate) baseDamage -= 1.5f;
                    if (hasLegs) baseDamage -= 1f;

                }

                if (dump) {
                    entity.hurt(DamageSource.CACTUS, baseDamage);
                }
            }
        }
    }


    private static boolean isPlayerConnectedToWater(Level world, BlockPos barbedPos, LivingEntity player) {
        Queue<BlockPos> open = new LinkedList<>();
        HashSet<BlockPos> visited = new HashSet<>();

        open.add(barbedPos);

        while (!open.isEmpty() && visited.size() <= 50) {
            BlockPos current = open.poll();
            visited.add(current);

            // On check les 6 directions
            for (Direction dir : Direction.values()) {
                BlockPos next = current.relative(dir);

                // ignorer si déjà visité
                if (visited.contains(next)) continue;

                // vérifier si c’est de l’eau
                if (world.getFluidState(next).is(Fluids.WATER)) {

                    // Vérifier si la hitbox du joueur touche ce bloc
                    AABB waterAABB = new AABB(next);
                    if (player.getBoundingBox().intersects(waterAABB)) {
                        return true; // joueur connecté !
                    }

                    // ajouter à la liste pour continuer à scroller
                    if (barbedPos.distManhattan(next) <= MAX_WATER_SCAN) {
                        open.add(next);
                    }
                }
            }
        }
        return false;
    }

}
