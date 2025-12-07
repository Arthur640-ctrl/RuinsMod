package fr.ruins.mod.registers;

import fr.ruins.mod.RuinsMod;
import fr.ruins.mod.armors.InsulateArmorMaterials;
import fr.ruins.mod.items.BarbedWirePliers;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RuinsMod.MODID);

    public static final RegistryObject<Item> BARBED_WIRE_ITEM = ITEMS.register("barbed_wire",
            () -> new BlockItem(ModBlocks.BARBED_WIRE.get(),
                    new Item.Properties().tab(ModCreativeTabs.RUINS_TAB)));

    public static final RegistryObject<Item> BARBED_WIRE_T1_ITEM = ITEMS.register("barbed_wire_t1",
            () -> new BlockItem(ModBlocks.BARBED_WIRE_T1.get(),
                    new Item.Properties().tab(ModCreativeTabs.RUINS_TAB)));

    public static final RegistryObject<Item> INSULATE_ARMOR_HELMET = ITEMS.register("insulate_armor_helmet",
            () -> new ArmorItem(InsulateArmorMaterials.INSULATE_ARMOR, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeTabs.RUINS_TAB)));

    public static final RegistryObject<Item> INSULATE_ARMOR_CHESTPLATE = ITEMS.register("insulate_armor_chestplate",
            () -> new ArmorItem(InsulateArmorMaterials.INSULATE_ARMOR, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModCreativeTabs.RUINS_TAB)));

    public static final RegistryObject<Item> INSULATE_ARMOR_LEGGINGS = ITEMS.register("insulate_armor_leggings",
            () -> new ArmorItem(InsulateArmorMaterials.INSULATE_ARMOR, EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModCreativeTabs.RUINS_TAB)));

    public static final RegistryObject<Item> INSULATE_ARMOR_BOOTS = ITEMS.register("insulate_armor_boots",
            () -> new ArmorItem(InsulateArmorMaterials.INSULATE_ARMOR, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModCreativeTabs.RUINS_TAB)));

    public static final RegistryObject<Item> WIRE = ITEMS.register("wire",
            () -> new Item(new Item.Properties().tab(ModCreativeTabs.RUINS_TAB)));

    public static final RegistryObject<Item> BARBED_WIRE_PLIERS = ITEMS.register("barbed_wire_pliers",
            () -> new BarbedWirePliers());

    public static final RegistryObject<Item> COPPER_WIRE = ITEMS.register("copper_wire",
            () -> new Item(new Item.Properties().tab(ModCreativeTabs.RUINS_TAB)));

    public static final RegistryObject<Item> RUBBER = ITEMS.register("rubber",
            () -> new Item(new Item.Properties().tab(ModCreativeTabs.RUINS_TAB)));

    public static final RegistryObject<Item> ZINC_ORE_ITEM = ITEMS.register("zinc_ore",
            () -> new BlockItem(ModBlocks.ZINC_ORE.get(),
                    new Item.Properties().tab(ModCreativeTabs.RUINS_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
