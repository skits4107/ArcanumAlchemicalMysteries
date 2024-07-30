package net.skits4107.arcanum.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;

public class AlchemicalCauldronEntity extends BlockEntity {

    public ArrayList<String> ingredients = new ArrayList<>();
    public AlchemicalCauldronEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.ALCHEMICAL_CAULDRON_ENTITY.get(), pPos, pBlockState);
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState, AlchemicalCauldronEntity pBlockEntity) {
        if(pLevel.isClientSide) {
            return;
        }
    }
}
