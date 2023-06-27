package net.alba.oldworld.item.custom.staffs;

import net.alba.oldworld.item.custom.tools.MagicItems;
import net.alba.oldworld.magic.MagicCalc;
import net.minecraft.block.BlockState;
import net.minecraft.entity.mob.EvokerFangsEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BiteStaff extends MagicItems{

    public BiteStaff(Settings settings) {
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
        double centerY = blockP.getY() + 1;
        double centerZ = blockP.getZ() + 0.5;
        double offsetX = 1.3;

        float[] rotations = {45, 0, -45};

        for (float rotation : rotations) {
            EvokerFangsEntity fang = new EvokerFangsEntity(world, centerX + offsetX, centerY, centerZ, rotation, 0, player);
            offsetX -= 1.3;
            world.spawnEntity(fang);
        }
    }

    @Override
    public String setToolTipString() {
        return "item.oldworld.bite_staff.tooltip";
    }
}
