package fr.ruins.mod.registers;

import fr.ruins.mod.RuinsMod;
import fr.ruins.mod.blocks.BarbedWireBlock;
import fr.ruins.mod.blocks.BarbedWireT1Block;
import fr.ruins.mod.blocks.ZincOreBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, RuinsMod.MODID);

    public static final RegistryObject<Block> BARBED_WIRE = BLOCKS.register("barbed_wire",
            () -> new BarbedWireBlock());

    public static final RegistryObject<Block> BARBED_WIRE_T1 = BLOCKS.register("barbed_wire_t1",
            () -> new BarbedWireT1Block());

    public static final RegistryObject<Block> ZINC_ORE = BLOCKS.register("zinc_ore",
            () -> new ZincOreBlock());

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
