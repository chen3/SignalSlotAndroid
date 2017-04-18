package cn.qiditu.signalslot.slots;

import android.support.annotation.Nullable;

import cn.qiditu.signalslot.Slot;

/**
 * A slot with 3 generic arguments.
 *
 * @param <T> The type of the first argument.
 * @param <U> The type of the second argument.
 * @param <V> The type of the third argument.
 */
public interface Slot3<T, U, V> extends Slot {

    void accept(@Nullable final T t, @Nullable final U u, @Nullable final V v);

}
