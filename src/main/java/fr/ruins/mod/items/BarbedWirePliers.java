package fr.ruins.mod.items;

import fr.ruins.mod.blocks.BarbedWireBlock;
import fr.ruins.mod.registers.ModCreativeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class BarbedWirePliers extends Item {

    public BarbedWirePliers() {
        super(new Item.Properties().tab(ModCreativeTabs.RUINS_TAB).durability(100));
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return state.getBlock() instanceof BarbedWireBlock;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if (state.getBlock() instanceof  BarbedWireBlock) {
            return 6f;
        } else {
            return 1f;
        }

    }

}
