package net.alba.oldworld.block.entity;

import org.jetbrains.annotations.Nullable;

import net.alba.oldworld.block.ModBlockEntities;
import net.alba.oldworld.item.ModItems;
import net.alba.oldworld.item.custom.crystals.FireCrystal;
import net.alba.oldworld.screen.CrystalImbuerBlock.CrystalImbuerScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CrystalImbuerBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory{
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(6, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;

    public CrystalImbuerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CRYSTAL_IMBUER, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                switch (index) {
                    case 0: return CrystalImbuerBlockEntity.this.progress;
                    case 1: return CrystalImbuerBlockEntity.this.maxProgress;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: CrystalImbuerBlockEntity.this.progress = value; break;
                    case 1: CrystalImbuerBlockEntity.this.maxProgress = value; break;
                }
            }

            public int size() {
                return 2;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Crystal Imbuer");
    }

    private void resetProgress() {
        this.progress = 0;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("crystal_imbuer.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        progress = nbt.getInt("crystal_imbuer.progress");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new CrystalImbuerScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    public static void tick(World world, BlockPos blockPos, BlockState state, CrystalImbuerBlockEntity entity) {
        if (world.isClient) {
            return;
        }

        if (hasRecipe(entity)) {
            entity.progress++;
            markDirty(world, blockPos, state);
            if (entity.progress >= entity.maxProgress) {
                craftItem(entity);
            }
        }
        else {
            entity.resetProgress();
            markDirty(world, blockPos, state);
        }
    }

    private static void craftItem(CrystalImbuerBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        if (hasRecipe(entity)) {
            transferSpells(entity, inventory, entity.getStack(0).getItem());
            entity.removeStack(1,1);

            //entity.setStack(0, new ItemStack(ModItems.OLD_SWORD, entity.getStack(0).getCount() + 1));
            entity.resetProgress();
        }
    }

    private static boolean hasRecipe(CrystalImbuerBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        boolean hasGrimoireInFirstSlot = entity.getStack(0).getItem() == ModItems.GRIMOIRE_BASIC;
        return hasGrimoireInFirstSlot && hasOnlyCrystalsInSlots(inventory, ModItems.TAG_SPELL_CRYSTALS);
    }

    private static boolean hasOnlyCrystalsInSlots(SimpleInventory inventory, TagKey<Item> tag) {
        ItemStack currentSlotStack;
        boolean atLeastOne = false;
        for (int i = 1; i <= 5; i++) {
            currentSlotStack = inventory.getStack(i);
            if (!currentSlotStack.isEmpty()) {
                if (currentSlotStack.isIn(tag)) {
                    atLeastOne = true;
                }
                else {
                    return false;
                }
            }
        }
        return atLeastOne;
    }

    private static void transferSpells(CrystalImbuerBlockEntity entity, SimpleInventory inventory, Item item) {
        ItemStack grimoireStack = inventory.getStack(0);
        ItemStack crystalSlotStack;

        if (item == ModItems.GRIMOIRE_BASIC) {

            for (int i = 1; i <= 3; i++) {
                crystalSlotStack = inventory.getStack(i);
                if (crystalSlotStack.isEmpty()) {
                    continue;
                }
                
                // TRANSFER OF SPELLS
                else if (crystalSlotStack.getItem() instanceof FireCrystal fireCrystal) {    
                    String spell = fireCrystal.getSpellKey();

                    NbtCompound tag = crystalSlotStack.getOrCreateNbt();
                    tag.putString("spell" + i, spell);
                    grimoireStack.setNbt(tag);
                    entity.removeStack(i, 1);
                }
            }
        }
    }
}