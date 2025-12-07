package fr.ruins.mod;

import com.mojang.logging.LogUtils;
import fr.ruins.mod.registers.ModBlocks;
import fr.ruins.mod.registers.ModItems;
import fr.ruins.mod.worldgen.ModWorldGen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(RuinsMod.MODID)
public class RuinsMod
{
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "ruinsmod";

    public RuinsMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }
}
