package net.alba.oldworld.item.custom.staffs;

import net.alba.oldworld.item.custom.StaffWeapons;
import net.alba.oldworld.magic.MagicCalc;
import net.minecraft.block.BlockState;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CrystalStaff extends StaffWeapons{

    public CrystalStaff(Settings settings) {
        super(settings);
        
    }

    @Override
    public void rightClick(World world, PlayerEntity player, ItemStack stack, Hand hand) {
        BlockPos blockP = MagicCalc.getBlockPosition(world, player, 60);

        BlockState blockState = world.getBlockState(blockP);
        if (blockState.isAir()) {
            return;
        }

        double centerX = blockP.getX() + 0.5;
        double centerY = blockP.getY();
        double centerZ = blockP.getZ() + 0.5;
        
        EndCrystalEntity endCrystalEntity = new EndCrystalEntity(world, centerX, centerY, centerZ);
        endCrystalEntity.setShowBottom(false);
        world.spawnEntity(endCrystalEntity);
    }

    @Override
    public String setToolTipString() {
        return "item.oldworld.crystal_staff.tooltip";
    }
}
