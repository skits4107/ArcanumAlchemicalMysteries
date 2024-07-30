package net.skits4107.arcanum.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.skits4107.arcanum.block.entity.AlchemicalCauldronEntity;
import net.skits4107.arcanum.block.entity.ModBlockEntities;
import net.skits4107.arcanum.item.ModItems;
import org.jetbrains.annotations.Nullable;

public class AlchemicalCauldron extends BaseEntityBlock {


    public static final Property<Boolean> FILLED = BooleanProperty.create("filled");

    protected static final VoxelShape LEG_ONE = box(0,0,0,4,2,4);
    protected static final VoxelShape LEG_TWO = box(12,0,0,16,2,4);
    protected static final VoxelShape LEG_THREE = box(0,0,12,4,2,16);
    protected static final VoxelShape LEG_FOUR = box(12,0,12,16,2,16);
    protected static final VoxelShape BODY = box(0,2,0,16,16,16);

    protected static final VoxelShape SHAPE = Shapes.or(LEG_ONE,LEG_TWO,LEG_THREE,LEG_FOUR, BODY);



    public AlchemicalCauldron(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FILLED, false));

    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FILLED); //registers properties to block states

    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new AlchemicalCauldronEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if(pLevel.isClientSide()){
            return null;
        }

        return createTickerHelper(pBlockEntityType, ModBlockEntities.ALCHEMICAL_CAULDRON_ENTITY.get(), (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick(pLevel1, pPos, pState1, pBlockEntity));

    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pLevel.isClientSide){
            return InteractionResult.PASS;
        }
        if (!pHand.equals(InteractionHand.MAIN_HAND)){
            return InteractionResult.PASS;
        }
        ItemStack item = pPlayer.getItemInHand(pHand);
        if (item.is(Items.WATER_BUCKET)){
            boolean filled = pState.getValue(FILLED);
            if (!filled){
                item.shrink(1);
                pLevel.setBlock(pPos, pState.setValue(FILLED, true), 3);
                //give empty bucket
                pPlayer.getInventory().add((new ItemStack(Items.BUCKET)));
                //indicate successful interaction
                return InteractionResult.CONSUME;
            }
        }
        else if (item.is(Items.BUCKET)){
            boolean filled = pState.getValue(FILLED);
            if (filled){
                item.shrink(1);
                pLevel.setBlock(pPos, pState.setValue(FILLED, false), 3);
                pPlayer.getInventory().add((new ItemStack(Items.WATER_BUCKET)));
                //indicate successful interaction
                return InteractionResult.CONSUME;
            }
        }

        return InteractionResult.PASS;

    }
}
