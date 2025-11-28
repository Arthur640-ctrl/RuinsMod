package fr.ruins.mod.registers;

import fr.ruins.mod.RuinsMod;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTabs {

    public static final CreativeModeTab RUINS_TAB = new CreativeModeTab(RuinsMod.MODID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.BARBED_WIRE_ITEM.get());
        }
    };

}
