package net.kelsoncraft.kcmod.util;

import com.google.common.base.Suppliers;
import net.kelsoncraft.kcmod.KCMod;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class NBTUtil {

    //-----
    // Taken from FunctionalStorage in FSAttachements.java, licensed under MIT
    // https://github.com/Buuz135/FunctionalStorage/blob/1.21/src/main/java/com/buuz135/functionalstorage/item/FSAttachments.java
    public static final DeferredRegister<DataComponentType<?>> DR = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, KCMod.MOD_ID);
    public static final Supplier<DataComponentType<CompoundTag>> FLY_ITEM = register("fly_item", CompoundTag::new, op -> op.persistent(CompoundTag.CODEC));


    private static <T> ComponentSupplier<T> register(String name, Supplier<T> defaultVal, UnaryOperator<DataComponentType.Builder<T>> op) {
        var registered = DR.register(name, () -> op.apply(DataComponentType.builder()).build());
        return new ComponentSupplier<>(registered, defaultVal);
    }

    public static class ComponentSupplier<T> implements Supplier<DataComponentType<T>> {
        private final Supplier<DataComponentType<T>> type;
        private final Supplier<T> defaultSupplier;

        public ComponentSupplier(Supplier<DataComponentType<T>> type, Supplier<T> defaultSupplier) {
            this.type = type;
            this.defaultSupplier = Suppliers.memoize(defaultSupplier::get);
        }

        public T get(ItemStack stack) {
            return stack.getOrDefault(type, defaultSupplier.get());
        }

        @Override
        public DataComponentType<T> get() {
            return type.get();
        }
    }

    //-----

}
