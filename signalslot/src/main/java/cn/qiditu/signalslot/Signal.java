package cn.qiditu.signalslot;

import android.support.annotation.Nullable;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Signal {

    private WeakReference<Object> signalSender;

    protected Signal(@NonNull Object sender) {
        this(new WeakReference<>(sender));
    }

    protected Signal(@NonNull WeakReference<Object> sender) {
        signalSender = sender;
    }

    private static HashMap<Long, WeakReference<Object>> threadSender = new HashMap<>();
    @Nullable
    public Object getSender() {
        WeakReference<Object> sender = threadSender.get(Thread.currentThread().getId());
        if(sender == null) {
            return null;
        }
        else {
            return sender.get();
        }
    }

    /**
     * Indicates whether a signalslot is enabled/disabled.
     * @see #enable()
     * @see #disable()
     * @see #setEnabled(boolean)
     */
    private final AtomicBoolean enabled = new AtomicBoolean(true);

    /**
     * The queue of DIRECT connected slots.
     */
    private Vector<Data> direct = new Vector<>();


    /**
     * Enables the signalslot.
     * @see #disable()
     */
    @SuppressWarnings({"unused", "WeakerAccess"})
    public void enable() {
        enabled.set(true);
    }

    /**
     * Disables the signalslot. A disabled signalslot will not actuate its connected
     * slots.
     * @see #enable()
     */
    @SuppressWarnings({"unused", "WeakerAccess"})
    public void disable() {
        enabled.set(false);
    }

    @SuppressWarnings({"unused", "WeakerAccess"})
    public void setEnabled(boolean enable) {
        enabled.set(enable);
    }

    /**
     * Removes all connected slots. Clearing a signalslot is not an atomic
     * operation and may result in a non-empty slot queue if one of the
     * 'connect' methods is used concurrently.
     */
    @SuppressWarnings("WeakerAccess")
    public synchronized void clear() {
        direct.clear();
    }

    /**
     * @see #clear()
     */
    @SuppressWarnings("unused")
    public void disconnectAll() {
        clear();
    }

    protected abstract Slot getSlotEmit();

    @NonNull
    protected Slot connect(@NonNull final Slot slot) {
        return connect(slot, -1);
    }

    @NonNull
    protected Slot connect(@NonNull final Signal signal) {
        return connect(signal.getSlotEmit());
    }

    @NonNull
    protected Slot connect(@NonNull final Signal signal, int count) {
        return connect(signal.getSlotEmit(), count);
    }

    @NonNull
    protected synchronized Slot connect(@NonNull final Slot slot, final int count) {
        direct.add(new Data(slot, count));
        return slot;
    }

    protected boolean disconnect(@NonNull final Signal signal) {
        return disconnect(signal.getSlotEmit());
    }

    protected synchronized boolean disconnect(@NonNull final Slot slot) {
        boolean removed = false;
        Iterator<Data> each = direct.iterator();
        while (each.hasNext()) {
            if(each.next().slot == slot) {
                each.remove();
                removed = true;
            }
        }
        return removed;
    }

    /**
     * Emits this signalslot with the given arguments.
     *
     * @param args The arguments to use while emitting this signalslot.
     */
    protected void emit(final Object... args) {
        if (!enabled.get()) {
            return;
        }
        Data[] slots;
        final Long threadId = Thread.currentThread().getId();
        synchronized (this) {   //cache slots data
            int size = direct.size();
            slots = new Data[direct.size()];
            //noinspection SuspiciousSystemArraycopy
            System.arraycopy(direct.toArray(), 0, slots, 0, size);
        }

        for(int i = 0; i < slots.length; ++i) {
            Data data = slots[i];
            if (!direct.contains(data)) {
                continue;
            }
            boolean invokeSlot = false;
            synchronized (this) {
                int index = direct.indexOf(data);
                if (index < 0) {
                    continue;
                }
                int lastCount = direct.get(i).count;
                if(lastCount == 0) {
                    direct.remove(i);
                }
                else if(lastCount == 1) {
                    invokeSlot = true;
                    direct.remove(i);
                }
                else {
                    invokeSlot = true;
                    if(lastCount > 0) {
                        direct.get(i).count--;
                    }
                }
            }
            if(invokeSlot) {
                threadSender.put(threadId, signalSender);
                actuate(data.slot, args);
            }
        }
    }

    /**
     * A callback function used to actuate a single slot.
     *
     * The implementer of this function does not need to create any threads
     * but cast down the given slot and actuate it with the given arguments.
     *
     * This function should not have any side effects to this class.
     *
     * @param slot The slot to actuate.
     * @param args The arguments of the actuated slot.
     */
    protected abstract void actuate(@NonNull final Slot slot, @NonNull final Object[] args);

    @SuppressWarnings("WeakerAccess")
    protected static class Data {
        private final Slot slot;
        private int count;
        @SuppressWarnings("unused")
        public Data(@NonNull Slot slot) {
            this(slot, -1);
        }
        @SuppressWarnings("WeakerAccess")
        public Data(@NonNull Slot slot, int count) {
            this.slot = slot;
            this.count = count;
        }
    }

}
