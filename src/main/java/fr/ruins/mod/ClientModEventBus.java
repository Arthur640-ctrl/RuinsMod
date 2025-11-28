package fr.ruins.mod;

import fr.ruins.mod.registers.ModBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@Mod.EventBusSubscriber(modid = RuinsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventBus {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        // Déclare que BARBED_WIRE utilise cutout pour la transparence
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BARBED_WIRE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BARBED_WIRE_T1.get(), RenderType.cutout());
    }
}
