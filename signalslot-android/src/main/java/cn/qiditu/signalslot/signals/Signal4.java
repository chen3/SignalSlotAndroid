package cn.qiditu.signalslot.signals;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import cn.qiditu.signalslot.Signal;
import cn.qiditu.signalslot.Slot;
import cn.qiditu.signalslot.slots.Slot4;

import java.lang.ref.WeakReference;

abstract class BaseSignal4<T, U, V, W> extends BaseSignal3<T, U, V> {

    @SuppressWarnings("WeakerAccess")
    protected BaseSignal4(@NonNull Object sender) {
        super(sender);
    }

    @SuppressWarnings("WeakerAccess")
    protected BaseSignal4(@NonNull WeakReference<Object> sender) {
        super(sender);
    }

    /**
     * @see Signal#connect(Signal)
     */
    @SuppressWarnings("unchecked")
    public Slot4<T, U, V, W> connect(@NonNull final Signal4<T, U, V, W> signal) {
        return (Slot4<T, U, V, W>)super.connect(signal);
    }

    /**
     * @see Signal#connect(Signal, int)
     */
    @SuppressWarnings("unchecked")
    public Slot4<T, U, V, W> connect(@NonNull final Signal4<T, U, V, W> signal,
                                     int count) {
        return (Slot4<T, U, V, W>)super.connect(signal, count);
    }

    /**
     * @see Signal#connect(Slot)
     */
    public Slot4<T, U, V, W> connect(@NonNull final Slot4<T, U, V, W> slot) {
        super.connect(slot);
        return slot;
    }

    /**
     * @see Signal#connect(Slot, int)
     */
    public Slot4<T, U, V, W> connect(@NonNull final Slot4<T, U, V, W> slot,
                                     final int count) {
        super.connect(slot, count);
        return slot;
    }

    /**
     * @see Signal#disconnect(Signal)
     */
    public boolean disconnect(@NonNull final Signal4<T, U, V, W> signal) {
        return super.disconnect(signal);
    }

    /**
     * @see Signal#disconnect(Slot)
     */
    public boolean disconnect(@NonNull final Slot4<T, U, V, W> slot) {
        return super.disconnect(slot);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void actuate(@NonNull final Slot slot, @NonNull final Object[] args) {
        if(slot instanceof Slot4) {
            ((Slot4<T, U, V, W>)slot).accept((T)args[0], (U)args[1], (V)args[2],
                                             (W)args[3]);
        }
        else {
            super.actuate(slot, args);
        }
//        ((Slot4<T, U, V, W>)slot).accept((T)args[0], (U)args[1], (V)args[2], (W)args[3]);
    }
}

/**
 * A signalslot with 4 generic arguments.
 *
 * @param <T> The type of the first argument.
 * @param <U> The type of the second argument.
 * @param <V> The type of the third argument.
 * @param <W> The type of the forth argument.
 */
@SuppressWarnings("WeakerAccess")
public class Signal4<T, U, V, W> extends BaseSignal4<T, U, V, W> {

    @SuppressWarnings("unused")
    public Signal4(@NonNull Object sender) {
        super(sender);
    }

    @SuppressWarnings("unused")
    public Signal4(@NonNull WeakReference<Object> sender) {
        super(sender);
    }

    private final Slot4<T, U, V, W> slotEmit = new Slot4<T, U, V, W>() {
        @Override
        public void accept(@Nullable T t, @Nullable U u, @Nullable V v, @Nullable W w) {
            Signal4.this.emit(t, u, v, w);
        }
    };
    @Override
    protected Slot getSlotEmit() {
        return slotEmit;
    }

    /**
     * @see Signal#emit(Object...)
     */
    public void emit(@Nullable final T t, @Nullable final U u, @Nullable final V v,
                     @Nullable final W w) {
        super.emit(t, u, v, w);
    }

}
