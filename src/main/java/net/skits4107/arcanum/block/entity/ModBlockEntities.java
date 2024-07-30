package net.skits4107.arcanum.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.skits4107.arcanum.AAMMod;
import net.skits4107.arcanum.block.ModBlocks;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, AAMMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<AlchemicalCauldronEntity>> ALCHEMICAL_CAULDRON_ENTITY = BLOCK_ENTITIES.register("alchemical_cauldron_entity", ()->
            BlockEntityType.Builder.of(AlchemicalCauldronEntity::new, ModBlocks.ALCHEMICAL_CAULDRON.get()).build(null)
    );

    public static void register(IEventBus bus){
        BLOCK_ENTITIES.register(bus);
    }

}
