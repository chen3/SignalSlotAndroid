package cn.qiditu.signalslot.slots;

import android.support.annotation.Nullable;

import cn.qiditu.signalslot.Slot;

/**
 * A slot with 2 generic arguments.
 *
 * @param <T> The type of the first argument.
 * @param <U> The type of the second argument.
 */
public interface Slot2<T, U> extends Slot {

    void accept(@Nullable final T t, @Nullable final U u);

}
