package cn.qiditu.signalslot.signals;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import cn.qiditu.signalslot.Signal;
import cn.qiditu.signalslot.Slot;
import cn.qiditu.signalslot.slots.Slot3;

import java.lang.ref.WeakReference;

abstract class BaseSignal3<T, U, V> extends BaseSignal2<T, U> {

    @SuppressWarnings("WeakerAccess")
    protected BaseSignal3(@NonNull Object sender) {
        super(sender);
    }

    @SuppressWarnings("WeakerAccess")
    protected BaseSignal3(@NonNull WeakReference<Object> sender) {
        super(sender);
    }

    /**
     * @see Signal#connect(Signal)
     */
    @SuppressWarnings("unchecked")
    public Slot3<T, U, V> connect(@NonNull final Signal3<T, U, V> signal) {
        return (Slot3<T, U, V>)super.connect(signal);
    }

    /**
     * @see Signal#connect(Signal, int)
     */
    @SuppressWarnings("unchecked")
    public Slot3<T, U, V> connect(@NonNull final Signal3<T, U, V> signal, int count) {
        return (Slot3<T, U, V>)super.connect(signal, count);
    }

    /**
     * @see Signal#connect(Slot)
     */
    public Slot3<T, U, V> connect(@NonNull final Slot3<T, U, V> slot) {
        super.connect(slot);
        return slot;
    }

    /**
     * @see Signal#connect(Slot, int)
     */
    public Slot3<T, U, V> connect(@NonNull final Slot3<T, U, V> slot, final int count) {
        super.connect(slot, count);
        return slot;
    }

    /**
     * @see Signal#disconnect(Signal)
     */
    public boolean disconnect(@NonNull final Signal3<T, U, V> signal) {
        return super.disconnect(signal);
    }

    /**
     * @see Signal#disconnect(Slot)
     */
    public boolean disconnect(@NonNull final Slot3<T, U, V> slot) {
        return super.disconnect(slot);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void actuate(@NonNull final Slot slot, @NonNull final Object[] args) {
        if(slot instanceof Slot3) {
            ((Slot3<T, U, V>)slot).accept((T)args[0], (U)args[1], (V)args[2]);
        }
        else {
            super.actuate(slot, args);
        }
//        ((Slot3<T, U, V>)slot).accept((T)args[0], (U)args[1], (V)args[2]);
    }
}

/**
 * A signalslot with 3 generic arguments.
 *
 * @param <T> The type of the first argument.
 * @param <U> The type of the second argument.
 * @param <V> The type of the third argument.
 */
@SuppressWarnings("WeakerAccess")
public class Signal3<T, U, V> extends BaseSignal3<T, U, V> {

    @SuppressWarnings("unused")
    public Signal3(@NonNull Object sender) {
        super(sender);
    }

    @SuppressWarnings("unused")
    public Signal3(@NonNull WeakReference<Object> sender) {
        super(sender);
    }

    private final Slot3<T, U, V> slotEmit = new Slot3<T, U, V>() {
        @Override
        public void accept(@Nullable T t, @Nullable U u, @Nullable V v) {
            Signal3.this.emit(t, u, v);
        }
    };
    @Override
    protected Slot getSlotEmit() {
        return slotEmit;
    }

    /**
     * @see Signal#emit(Object...)
     */
    public void emit(@Nullable final T t, @Nullable final U u, @Nullable final V v) {
        super.emit(t, u, v);
    }

}
