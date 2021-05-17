// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Mansour Najah (mansourn)
package spacecolonies;

import queue.EmptyQueueException;
import queue.QueueInterface;

/**
 * The ArrayQueue class.
 *
 * @author Mansour Najah (mansourn)
 * @version 04.13.2020
 * @param <T> is a generic type.
 */
public class ArrayQueue<T> implements QueueInterface<T> {

    private T[] queue;

    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_CAPACITY = 100;

    private int enqueueIndex;
    private int dequeueIndex;

    private int currentSize;

    /**
     * Creates a new ArrayQueue object.
     */
    public ArrayQueue() {
        @SuppressWarnings("unchecked")
        T[] tempQueue = (T[]) new Object[DEFAULT_CAPACITY + 1];
        queue = tempQueue;

        dequeueIndex = 0;
        enqueueIndex = DEFAULT_CAPACITY;

        currentSize = 0;
    }

    /**
     * Makes the queue empty of objects.
     */
    @Override
    public void clear() {
        @SuppressWarnings("unchecked")
        T[] tempQueue = (T[])new Object[DEFAULT_CAPACITY + 1];
        queue = tempQueue;

        dequeueIndex = 0;
        enqueueIndex = DEFAULT_CAPACITY;

        currentSize = 0;
    }

    /**
     * Returns and removes the first element of the queue.
     *
     * @return data is is the element at the front.
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        else {
            T front = queue[dequeueIndex];

            queue[dequeueIndex] = null;
            dequeueIndex = (dequeueIndex + 1) % queue.length;

            currentSize--;
            return front;
        }
    }

    /**
     * Puts a value into the back of the queue.
     * If it's full, then it should double
     * its size.
     *
     * @param itemToBeQueued the value to be put into the queue.
     */
    @Override
    public void enqueue(T itemToBeQueued) {
        ensureCapacity();

        enqueueIndex = incrementIndex(enqueueIndex);

        queue[enqueueIndex] = itemToBeQueued;

        currentSize++;
    }

    /**
     * Returns the first element in the queue.
     *
     * @return the element in front
     * @throws EmptyQueueException if the queue is empty
     */
    @Override
    public T getFront() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }

        else {
            return queue[dequeueIndex];
        }
    }

    /**
     * Checks to see if the queue is empty.
     *
     * @return true if it's empty
     */
    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    /**
     * Gets the queue's length.
     *
     * @return the length of the queue.
     */
    public int getLength() {
        return queue.length;
    }

    /**
     * Get's the amount of objects stored in the queue.
     *
     * @return the amount of objects stored a size
     */
    public int getSize() {
        return currentSize;
    }

    /**
     * Checks to see if the queue is full.
     *
     * @return true if it's full
     */
    private boolean isFull() {
        return dequeueIndex == ((enqueueIndex + 2) % queue.length);
    }

    /**
     * Makes certain that there is enough space
     */
    private void ensureCapacity() {
        if (isFull()) {
            if (currentSize == MAX_CAPACITY) {
                throw new IllegalStateException();
            }
            else {
                T[] oldQueue = queue;

                int oldcurrentSize = currentSize;
                int newcurrentSize = 2 * oldcurrentSize + 1;

                if (newcurrentSize > MAX_CAPACITY) {
                    newcurrentSize = MAX_CAPACITY + 1;
                }

                @SuppressWarnings("unchecked")
                T[] tempQueue = (T[]) new Object[newcurrentSize];
                queue = tempQueue;

                for (int index = 0; index < oldcurrentSize; index++) {
                    queue[index] = oldQueue[dequeueIndex];
                    dequeueIndex = (dequeueIndex + 1) % oldcurrentSize;
                }

                dequeueIndex = 0;
                enqueueIndex = oldcurrentSize - 1;
            }
        }
    }

    /**
     * Increments index.
     *
     * @param index being incremented
     * @return the newly incremented index
     */
    private int incrementIndex(int index) {
        return ((index + 1) % queue.length);
    }

    /**
     * The toArray() representation of this class.
     *
     * @return an array representation
     */
    public Object[] toArray() {
        if (this.isEmpty()) {
            throw new EmptyQueueException("empty queue");
        }

        Object[] array = new Object[currentSize];

        int dQIndex = dequeueIndex;

        for (int i = 0; i < currentSize; i++) {
            array[i] = queue[dQIndex];
            dQIndex = incrementIndex(dQIndex);
        }

        return array;
    }

    /**
     * The toString() representation of this class.
     *
     * @return a string representation, something like: "[Jane Doe A:3 M:2 T:1
     *         Wants:Nars, No-Planet Jane Doe A:2 M:5 T:4]"
     */
    public String toString() {
        String s = "[";

        int dQIndex = dequeueIndex;

        for (int i = 0; i < currentSize; i++) {
            s += queue[dQIndex].toString();

            if (i < currentSize - 1) {
                s += ", ";
            }

            dQIndex = incrementIndex(dQIndex);
        }

        s += "]";

        return s;
    }

    /**
     * The equals() method to compare skills with another object.
     *
     * @param obj is the object to be compared to
     * @return true if it is compared to itself, or another object with the same
     *         exact elements, false otherwise.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }

        if (obj.getClass() == this.getClass()) {

            @SuppressWarnings("unchecked")
            ArrayQueue<T> otherQueue = (ArrayQueue<T>) obj;
            if (this.getSize() != otherQueue.getSize()) {
                return false;
            }

            for (int i = 0; i < getSize(); i++) {
                T myElement = queue[(dequeueIndex + i) % queue.length];
                T otherElement = otherQueue.queue[(otherQueue.dequeueIndex + i)
                                                  % otherQueue.queue.length];

                if (!myElement.equals(otherElement)) {
                    return false;
                }

            }

            return true;
        }

        return false;
    }

}
