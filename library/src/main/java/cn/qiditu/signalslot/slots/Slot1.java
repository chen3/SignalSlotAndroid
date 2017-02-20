package cn.qiditu.signalslot.slots;

import android.support.annotation.Nullable;

import cn.qiditu.signalslot.Slot;

/**
 * A slot with 1 generic argument.
 *
 * @param <T> The type of the argument.
 */
public interface Slot1<T> extends Slot {

    void accept(@Nullable final T t);

}
