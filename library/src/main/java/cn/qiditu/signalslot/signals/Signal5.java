package cn.qiditu.signalslot.signals;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import cn.qiditu.signalslot.Slot;
import cn.qiditu.signalslot.Signal;
import cn.qiditu.signalslot.slots.Slot5;

import java.lang.ref.WeakReference;

abstract class BaseSignal5<T, U, V, W, X> extends BaseSignal4<T, U, V, W> {

    @SuppressWarnings("WeakerAccess")
    protected BaseSignal5(@NonNull Object sender) {
        super(sender);
    }

    @SuppressWarnings("WeakerAccess")
    protected BaseSignal5(@NonNull WeakReference<Object> sender) {
        super(sender);
    }

    /**
     * @see Signal#connect(Signal)
     */
    @SuppressWarnings("unchecked")
    public Slot5<T, U, V, W, X> connect(@NonNull final Signal5<T, U, V, W, X> signal) {
        return (Slot5<T, U, V, W, X>)super.connect(signal);
    }

    /**
     * @see Signal#connect(Signal, int)
     */
    @SuppressWarnings("unchecked")
    public Slot5<T, U, V, W, X> connect(@NonNull final Signal5<T, U, V, W, X> signal,
                                     int count) {
        return (Slot5<T, U, V, W, X>)super.connect(signal, count);
    }

    /**
     * @see Signal#connect(Slot)
     */
    public Slot5<T, U, V, W, X> connect(@NonNull final Slot5<T, U, V, W, X> slot) {
        super.connect(slot);
        return slot;
    }

    /**
     * @see Signal#connect(Slot, int)
     */
    public Slot5<T, U, V, W, X> connect(@NonNull final Slot5<T, U, V, W, X> slot,
                                        final int count) {
        super.connect(slot, count);
        return slot;
    }

    /**
     * @see Signal#disconnect(Signal)
     */
    public boolean disconnect(@NonNull final Signal5<T, U, V, W, X> signal) {
        return super.disconnect(signal);
    }

    /**
     * @see Signal#disconnect(Slot)
     */
    public boolean disconnect(@NonNull final Slot5<T, U, V, W, X> slot) {
        return super.disconnect(slot);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void actuate(@NonNull final Slot slot, @NonNull final Object[] args) {
        if(slot instanceof Slot5) {
            ((Slot5<T, U, V, W, X>)slot).accept((T)args[0], (U)args[1], (V)args[2],
                                                (W)args[3], (X)args[4]);
        }
        else {
            super.actuate(slot, args);
        }
//        ((Slot4<T, U, V, W>)slot).accept((T)args[0], (U)args[1], (V)args[2], (W)args[3]);
    }
}

/**
 * A signalslot with 5 generic arguments.
 *
 * @param <T> The type of the first argument.
 * @param <U> The type of the second argument.
 * @param <V> The type of the third argument.
 * @param <W> The type of the forth argument.
 * @param <X> The type of the fifth argument.
 */
@SuppressWarnings("WeakerAccess")
public class Signal5<T, U, V, W, X> extends BaseSignal5<T, U, V, W, X> {

    @SuppressWarnings("unused")
    public Signal5(@NonNull Object sender) {
        super(sender);
    }

    @SuppressWarnings("unused")
    public Signal5(@NonNull WeakReference<Object> sender) {
        super(sender);
    }

    private final Slot5<T, U, V, W, X> slotEmit = new Slot5<T, U, V, W, X>() {
        @Override
        public void accept(@Nullable T t, @Nullable U u, @Nullable V v,
                           @Nullable W w, @Nullable X x) {
            Signal5.this.emit(t, u, v, w, x);
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
                     @Nullable final W w, @Nullable final X x) {
        super.emit(t, u, v, w, x);
    }

}
