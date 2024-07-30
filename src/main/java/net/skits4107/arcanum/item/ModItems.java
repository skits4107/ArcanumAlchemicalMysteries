package net.skits4107.arcanum.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.skits4107.arcanum.AAMMod;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AAMMod.MOD_ID);

    public static void register(IEventBus bus){
        ITEMS.register(bus);
    }
}
