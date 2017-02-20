package cn.qiditu.signalslot.signals;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import cn.qiditu.signalslot.Signal;
import cn.qiditu.signalslot.Slot;
import cn.qiditu.signalslot.slots.Slot2;

import java.lang.ref.WeakReference;

abstract class BaseSignal2<T, U> extends BaseSignal1<T> {

    @SuppressWarnings("WeakerAccess")
    protected BaseSignal2(@NonNull Object sender) {
        super(sender);
    }

    @SuppressWarnings("WeakerAccess")
    protected BaseSignal2(@NonNull WeakReference<Object> sender) {
        super(sender);
    }

    /**
     * @see Signal#connect(Signal)
     */
    @SuppressWarnings("unchecked")
    public Slot2<T, U> connect(@NonNull final Signal2<T, U> signal) {
        return (Slot2<T, U>)super.connect(signal);
    }

    /**
     * @see Signal#connect(Signal, int)
     */
    @SuppressWarnings("unchecked")
    public Slot2<T, U> connect(@NonNull final Signal2<T, U> signal, int count) {
        return (Slot2<T, U>)super.connect(signal, count);
    }

    /**
     * @see Signal#connect(Slot)
     */
    public Slot2<T, U> connect(@NonNull final Slot2<T, U> slot) {
        super.connect(slot);
        return slot;
    }

    /**
     * @see Signal#connect(Slot, int)
     */
    public Slot2<T, U> connect(@NonNull final Slot2<T, U> slot, final int count) {
        super.connect(slot, count);
        return slot;
    }

    /**
     * @see Signal#disconnect(Signal)
     */
    public boolean disconnect(@NonNull final Signal2<T, U> signal) {
        return super.disconnect(signal);
    }

    /**
     * @see Signal#disconnect(Slot)
     */
    public boolean disconnect(@NonNull final Slot2<T, U> slot) {
        return super.disconnect(slot);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void actuate(@NonNull final Slot slot, @NonNull final Object[] args) {
        if(slot instanceof Slot2) {
            ((Slot2<T, U>)slot).accept((T)args[0], (U)args[1]);
        }
        else {
            super.actuate(slot, args);
        }
    }
}

/**
 * A signalslot with 2 generic arguments.
 *
 * @param <T> The type of the first argument.
 * @param <U> The type of the second argument.
 */
@SuppressWarnings("WeakerAccess")
public class Signal2<T, U> extends BaseSignal2<T, U> {

    @SuppressWarnings("unused")
    public Signal2(@NonNull Object sender) {
        super(sender);
    }

    @SuppressWarnings("unused")
    public Signal2(@NonNull WeakReference<Object> sender) {
        super(sender);
    }

    private final Slot2<T, U> slotEmit = new Slot2<T, U>() {
        @Override
        public void accept(@Nullable T t, @Nullable U u) {
            Signal2.this.emit(t, u);
        }
    };
    @Override
    protected Slot getSlotEmit() {
        return slotEmit;
    }

    /**
     * @see Signal#emit(Object...)
     */
    public void emit(@Nullable final T t, @Nullable final U u) {
        super.emit(t, u);
    }

}
