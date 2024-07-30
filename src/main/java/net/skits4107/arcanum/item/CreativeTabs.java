package net.skits4107.arcanum.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.skits4107.arcanum.AAMMod;
import net.skits4107.arcanum.block.ModBlocks;

public class CreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AAMMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> CHAOTIC_TAB = CREATIVE_MODE_TABS.register("arcanum_tab", () ->
            CreativeModeTab.builder().icon(() -> new ItemStack(Items.BREAD)) //to set modded item as display: ModItems.ITEM_NAME.get()
                    .title(Component.translatable("creativetab.arcanum_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        /* put items here to add to modded creative mode tab
                            pOutput.accept(ModItems.ITEM.get()); //example
                        */
                        pOutput.accept(ModBlocks.ALCHEMICAL_CAULDRON.get());
                    })

                    .build());

    public static void register(IEventBus EventBus){
        CREATIVE_MODE_TABS.register(EventBus);

    }
}
