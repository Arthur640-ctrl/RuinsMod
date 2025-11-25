package fr.ruins.mod.registers;

import fr.ruins.mod.RuinsMod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;
import fr.ruins.mod.registers.ModBlocks;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RuinsMod.MODID);


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
