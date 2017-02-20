package cn.qiditu.signalslot.signals;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import cn.qiditu.signalslot.Slot;
import cn.qiditu.signalslot.Signal;
import cn.qiditu.signalslot.slots.Slot6;

import java.lang.ref.WeakReference;

abstract class BaseSignal6<T, U, V, W, X, Y> extends BaseSignal5<T, U, V, W, X> {

    @SuppressWarnings("WeakerAccess")
    protected BaseSignal6(@NonNull Object sender) {
        super(sender);
    }

    @SuppressWarnings("WeakerAccess")
    protected BaseSignal6(@NonNull WeakReference<Object> sender) {
        super(sender);
    }

    /**
     * @see Signal#connect(Signal)
     */
    @SuppressWarnings("unchecked")
    public Slot6<T, U, V, W, X, Y>
    connect(@NonNull final Signal6<T, U, V, W, X, Y> signal) {
        return (Slot6<T, U, V, W, X, Y>)super.connect(signal);
    }

    /**
     * @see Signal#connect(Signal, int)
     */
    @SuppressWarnings("unchecked")
    public Slot6<T, U, V, W, X, Y>
    connect(@NonNull final Signal6<T, U, V, W, X, Y> signal, int count) {
        return (Slot6<T, U, V, W, X, Y>)super.connect(signal, count);
    }

    /**
     * @see Signal#connect(Slot)
     */
    public Slot6<T, U, V, W, X, Y> connect(@NonNull final Slot6<T, U, V, W, X, Y> slot) {
        super.connect(slot);
        return slot;
    }

    /**
     * @see Signal#connect(Slot, int)
     */
    public Slot6<T, U, V, W, X, Y> connect(@NonNull final Slot6<T, U, V, W, X, Y> slot,
                                           final int count) {
        super.connect(slot, count);
        return slot;
    }

    /**
     * @see Signal#disconnect(Signal)
     */
    public boolean disconnect(@NonNull final Signal6<T, U, V, W, X, Y> signal) {
        return super.disconnect(signal);
    }

    /**
     * @see Signal#disconnect(Slot)
     */
    public boolean disconnect(@NonNull final Slot6<T, U, V, W, X, Y> slot) {
        return super.disconnect(slot);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void actuate(@NonNull final Slot slot, @NonNull final Object[] args) {
        if(slot instanceof Slot6) {
            ((Slot6<T, U, V, W, X, Y>)slot).accept((T)args[0], (U)args[1], (V)args[2],
                                                   (W)args[3], (X)args[4], (Y)args[5]);
        }
        else {
            super.actuate(slot, args);
        }
//        ((Slot4<T, U, V, W>)slot).accept((T)args[0], (U)args[1], (V)args[2], (W)args[3]);
    }
}

/**
 * A signalslot with 6 generic arguments.
 *
 * @param <T> The type of the first argument.
 * @param <U> The type of the second argument.
 * @param <V> The type of the third argument.
 * @param <W> The type of the forth argument.
 * @param <X> The type of the fifth argument.
 * @param <Y> The type of the sixth argument.
 */
@SuppressWarnings("WeakerAccess")
public class Signal6<T, U, V, W, X, Y> extends BaseSignal6<T, U, V, W, X, Y> {

    @SuppressWarnings("unused")
    public Signal6(@NonNull Object sender) {
        super(sender);
    }

    @SuppressWarnings("unused")
    public Signal6(@NonNull WeakReference<Object> sender) {
        super(sender);
    }

    private final Slot6<T, U, V, W, X, Y> slotEmit = new Slot6<T, U, V, W, X, Y>() {
        @Override
        public void accept(@Nullable T t, @Nullable U u, @Nullable V v,
                           @Nullable W w, @Nullable X x, @Nullable Y y) {
            Signal6.this.emit(t, u, v, w, x, y);
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
                     @Nullable final W w, @Nullable final X x, @Nullable final Y y) {
        super.emit(t, u, v, w, x, y);
    }

}
