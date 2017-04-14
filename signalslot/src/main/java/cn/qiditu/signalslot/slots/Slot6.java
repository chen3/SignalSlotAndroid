package cn.qiditu.signalslot.slots;

import android.support.annotation.Nullable;

import cn.qiditu.signalslot.Slot;


/**
 * A slot with 34 generic arguments.
 *
 * @param <T> The type of the first argument.
 * @param <U> The type of the second argument.
 * @param <V> The type of the third argument.
 * @param <W> The type of the forth argument.
 * @param <X> The type of the fifth argument.
 * @param <Y> The type of the sixth argument.
 */
public interface Slot6<T, U, V, W, X, Y> extends Slot {

    void accept(@Nullable final T t, @Nullable final U u, @Nullable final V v,
                @Nullable final W w, @Nullable final X x, @Nullable final Y y);

}
