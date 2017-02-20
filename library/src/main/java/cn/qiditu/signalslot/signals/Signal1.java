package cn.qiditu.signalslot.signals;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import cn.qiditu.signalslot.Signal;
import cn.qiditu.signalslot.Slot;
import cn.qiditu.signalslot.slots.Slot1;

import java.lang.ref.WeakReference;

abstract class BaseSignal1<T> extends BaseSignal0 {

    @SuppressWarnings("WeakerAccess")
    protected BaseSignal1(@NonNull Object sender) {
        super(sender);
    }

    @SuppressWarnings("WeakerAccess")
    protected BaseSignal1(@NonNull WeakReference<Object> sender) {
        super(sender);
    }

    /**
     * @see Signal#connect(Signal)
     */
    @SuppressWarnings("unchecked")
    public Slot1<T> connect(@NonNull final Signal1<T> signal) {
        return (Slot1<T>)super.connect(signal);
    }

    /**
     * @see Signal#connect(Signal, int)
     */
    @SuppressWarnings("unchecked")
    public Slot1<T> connect(@NonNull final Signal1<T> signal, int count) {
        return (Slot1<T>)super.connect(signal, count);
    }

    /**
     * @see Signal#connect(Slot)
     */
    public Slot1<T> connect(@NonNull final Slot1<T> slot) {
        super.connect(slot);
        return slot;
    }

    /**
     * @see Signal#connect(Slot, int)
     */
    public Slot1<T> connect(@NonNull final Slot1<T> slot, final int count) {
        super.connect(slot, count);
        return slot;
    }

    /**
     * @see Signal#disconnect(Signal)
     */
    public boolean disconnect(@NonNull final Signal1<T> signal) {
        return super.disconnect(signal);
    }

    /**
     * @see Signal#disconnect(Slot)
     */
    public boolean disconnect(@NonNull final Slot1<T> slot) {
        return super.disconnect(slot);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void actuate(@NonNull final Slot slot, @NonNull final Object[] args) {
        if(slot instanceof Slot1) {
            ((Slot1<T>)slot).accept((T)args[0]);
        }
        else {
            super.actuate(slot, args);
        }
    }
}

/**
 * A signalslot with 1 generic argument.
 *
 * @param <T> The Type of the argument.
 */
@SuppressWarnings("WeakerAccess")
public class Signal1<T> extends BaseSignal1<T> {

    @SuppressWarnings("unused")
    public Signal1(@NonNull Object sender) {
        super(sender);
    }

    @SuppressWarnings("unused")
    public Signal1(@NonNull WeakReference<Object> sender) {
        super(sender);
    }

    private final Slot1<T> slotEmit = new Slot1<T>() {
        @Override
        public void accept(@Nullable T t) {
            Signal1.this.emit(t);
        }
    };
    @Override
    protected Slot getSlotEmit() {
        return slotEmit;
    }

    /**
     * @see Signal#emit(Object...)
     */
    public void emit(@Nullable final T t) {
        super.emit(t);
    }

}
