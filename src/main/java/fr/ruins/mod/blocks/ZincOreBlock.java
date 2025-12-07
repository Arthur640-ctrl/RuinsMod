package fr.ruins.mod.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class ZincOreBlock extends Block {

    public ZincOreBlock() {
        super(BlockBehaviour.Properties.of(Material.STONE)
                .strength(3f, 3f)
                .requiresCorrectToolForDrops());
    }

}
