package cn.qiditu.signalslot.signals;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import cn.qiditu.signalslot.Slot;
import cn.qiditu.signalslot.Signal;
import cn.qiditu.signalslot.slots.Slot7;

import java.lang.ref.WeakReference;

abstract class BaseSignal7<T, U, V, W, X, Y, Z> extends BaseSignal6<T, U, V, W, X, Y> {

    @SuppressWarnings("WeakerAccess")
    protected BaseSignal7(@NonNull Object sender) {
        super(sender);
    }

    @SuppressWarnings("WeakerAccess")
    protected BaseSignal7(@NonNull WeakReference<Object> sender) {
        super(sender);
    }

    /**
     * @see Signal#connect(Signal)
     */
    @SuppressWarnings("unchecked")
    public Slot7<T, U, V, W, X, Y, Z>
    connect(@NonNull final Signal7<T, U, V, W, X, Y, Z> signal) {
        return (Slot7<T, U, V, W, X, Y, Z>)super.connect(signal);
    }

    /**
     * @see Signal#connect(Signal, int)
     */
    @SuppressWarnings("unchecked")
    public Slot7<T, U, V, W, X, Y, Z>
    connect(@NonNull final Signal7<T, U, V, W, X, Y, Z> signal, int count) {
        return (Slot7<T, U, V, W, X, Y, Z>)super.connect(signal, count);
    }

    /**
     * @see Signal#connect(Slot)
     */
    public Slot7<T, U, V, W, X, Y, Z>
    connect(@NonNull final Slot7<T, U, V, W, X, Y, Z> slot) {
        super.connect(slot);
        return slot;
    }

    /**
     * @see Signal#connect(Slot, int)
     */
    public Slot7<T, U, V, W, X, Y, Z>
    connect(@NonNull final Slot7<T, U, V, W, X, Y, Z> slot, final int count) {
        super.connect(slot, count);
        return slot;
    }

    /**
     * @see Signal#disconnect(Signal)
     */
    public boolean disconnect(@NonNull final Signal7<T, U, V, W, X, Y, Z> signal) {
        return super.disconnect(signal);
    }

    /**
     * @see Signal#disconnect(Slot)
     */
    public boolean disconnect(@NonNull final Slot7<T, U, V, W, X, Y, Z> slot) {
        return super.disconnect(slot);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void actuate(@NonNull final Slot slot, @NonNull final Object[] args) {
        if(slot instanceof Slot7) {
            ((Slot7<T, U, V, W, X, Y, Z>)slot).accept(
                                                (T)args[0], (U)args[1], (V)args[2],
                                                (W)args[3], (X)args[4], (Y)args[5],
                                                (Z)args[6]);
        }
        else {
            super.actuate(slot, args);
        }
//        ((Slot4<T, U, V, W>)slot).accept((T)args[0], (U)args[1], (V)args[2], (W)args[3]);
    }
}

/**
 * A signalslot with 7 generic arguments.
 *
 * @param <T> The type of the first argument.
 * @param <U> The type of the second argument.
 * @param <V> The type of the third argument.
 * @param <W> The type of the forth argument.
 * @param <X> The type of the fifth argument.
 * @param <Y> The type of the sixth argument.
 * @param <Z> The type of the seventh argument.
 */
@SuppressWarnings("WeakerAccess")
public class Signal7<T, U, V, W, X, Y, Z> extends BaseSignal7<T, U, V, W, X, Y, Z> {

    @SuppressWarnings("unused")
    public Signal7(@NonNull Object sender) {
        super(sender);
    }

    @SuppressWarnings("unused")
    public Signal7(@NonNull WeakReference<Object> sender) {
        super(sender);
    }

    private final Slot7<T, U, V, W, X, Y, Z> slotEmit =
        new Slot7<T, U, V, W, X, Y, Z>() {
        @Override
        public void accept(@Nullable T t, @Nullable U u, @Nullable V v,
                           @Nullable W w, @Nullable X x, @Nullable Y y,
                           @Nullable Z z) {
            Signal7.this.emit(t, u, v, w, x, y, z);
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
                     @Nullable final W w, @Nullable final X x, @Nullable final Y y,
                     @Nullable final Z z) {
        super.emit(t, u, v, w, x, y, z);
    }

}
