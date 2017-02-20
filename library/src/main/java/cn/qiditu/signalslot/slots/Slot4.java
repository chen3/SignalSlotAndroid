package cn.qiditu.signalslot.slots;

import android.support.annotation.Nullable;

import cn.qiditu.signalslot.Slot;

/**
 * A slot with 4 generic arguments.
 *
 * @param <T> The type of the first argument.
 * @param <U> The type of the second argument.
 * @param <V> The type of the third argument.
 * @param <W> The type of the forth argument.
 */
public interface Slot4<T, U, V, W> extends Slot {

    void accept(@Nullable final T t, @Nullable final U u, @Nullable final V v,
                @Nullable final W w);

}
