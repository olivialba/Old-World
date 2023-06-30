package net.alba.oldworld.event;

import net.alba.oldworld.networking.OldPackets;
import net.alba.oldworld.registry.OldClient;
import net.alba.oldworld.registry.OldItems;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;

public class OldClientEvents {

    @SuppressWarnings("resource")
    public static void registerEvents() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            PlayerEntity player = MinecraftClient.getInstance().player;
            if (player != null && hasGrimoireInHand(player)) {
                if (OldClient.NextSpell.wasPressed()) {
                    ClientPlayNetworking.send(OldPackets.SPELL_CHANGE_ID, CreatePacketByteBuf(1));
                }
                else if (OldClient.PreviousSpell.wasPressed()) {
                    ClientPlayNetworking.send(OldPackets.SPELL_CHANGE_ID, CreatePacketByteBuf(-1));
                }
            }
        });
    }

    private static boolean hasGrimoireInHand(PlayerEntity player) {
        ItemStack mainHandStack = player.getMainHandStack();
        return !mainHandStack.isEmpty() && mainHandStack.getItem() == OldItems.GRIMOIRE_BASIC;
    }

    private static PacketByteBuf CreatePacketByteBuf(int amount) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeByte(amount);
        return buf;
    }
}
