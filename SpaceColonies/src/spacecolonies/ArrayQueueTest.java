// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Mansour Najah (mansourn)
package spacecolonies;

import queue.EmptyQueueException;

/**
 * Tests for the ArrayQueue class.
 *
 * @author <Mansour Najah> <mansourn>
 * @version <04.13.2020>
 */
public class ArrayQueueTest extends student.TestCase {

    private ArrayQueue<Person> queue;

    private Skills skillsZero;

    private Person personZero;
    private Person personNormal;
    private Person personMax;

    /**
     * Instantiates our ArrayQueue test object as well as several
     * skills and person
     * objects for testing.
     */
    public void setUp() {
        queue = new ArrayQueue<Person>();

        Skills skillsNormal;
        Skills skillsMax;

        skillsZero = new Skills(0, 0, 0);
        skillsNormal = new Skills(2, 5, 3);
        skillsMax = new Skills(5, 5, 5);

        personZero = new Person("Zero", skillsZero, "Planet 1");
        personNormal = new Person("Normal", skillsNormal, "Planet 2");
        personMax = new Person("Max", skillsMax, "Planet 3");
    }

    /**
     * Tests to see if the getLength() method is working as intended.
     */
    public void testGetLength() {
        assertEquals(11, queue.getLength());
    }

    /**
     * Tests to see if getLength() and Engqueue() function together.
     */
    public void testGetLengthAndEnqueue() {
        // Filling a queue with 10 person objects.

        for (int i = 0; i < 10; i++) {
            queue.enqueue(personZero);
        }

        assertEquals(11, queue.getLength());
        assertEquals(10, queue.getSize());

        // enqueue should detect that the array will be over-filled
        // and double
        // the capacity.

        queue.enqueue(personZero);

        assertEquals(21, queue.getLength());
        assertEquals(11, queue.getSize());
    }

    /**
     * Tests to see if getSize() and isEmpty() function together.
     */
    public void testGetSizeAndIsEmpty() {
        assertEquals(0, queue.getSize());

        assertTrue(queue.isEmpty());

        queue.enqueue(personZero);

        assertEquals(1, queue.getSize());

        assertFalse(queue.isEmpty());
    }

    /**
     * Tests the enqueue() method.
     */
    public void testEnqueue() {
        queue.enqueue(personZero);

        assertEquals(1, queue.getSize());
    }

    /**
     * Tests the enqueue() method with multiple calls.
     */
    public void testEnqueue2() {
        queue.enqueue(personZero);
        queue.enqueue(personNormal);
        queue.enqueue(personMax);

        assertEquals(3, queue.getSize());
    }

    /**
     * Tests the dequeue() method.
     */
    public void testDequeue() {
        queue.enqueue(personZero);
        queue.enqueue(personNormal);
        queue.enqueue(personMax);
        queue.enqueue(new Person("Pete", skillsZero, "Planet 1"));

        Person person = queue.dequeue();

        assertEquals(3, queue.getSize());

        assertTrue(person.equals(personZero));
    }

    /**
     * Tests the getFront() method.
     */
    public void testGetFront() {
        // Testing the queue is emtpy case.

        Exception exception = null;

        try {
            queue.getFront();
            fail("getFront() is not throwing an exception when it should");
        }

        catch (Exception e) {
            exception = e;
        }

        assertTrue("getFront() is throwing the wrong type of exceptions",
                exception instanceof EmptyQueueException);

        queue.enqueue(personZero);

        assertEquals(queue.getFront(), personZero);

        queue.enqueue(personNormal);

        assertEquals(queue.getFront(), personZero);

        queue.dequeue();

        assertEquals(queue.getFront(), personNormal);
    }

    /**
     * Tests the clear() method.
     */
    public void testClear() {
        for (int i = 0; i < 10; i++) {
            queue.enqueue(personZero);
        }

        assertEquals(11, queue.getLength());
        assertEquals(10, queue.getSize());

        queue.clear();

        assertEquals(0, queue.getSize());

        Exception exception = null;

        try {
            queue.getFront();
            fail("getFront() is not throwing an exception when it should");
        }

        catch (Exception e) {
            exception = e;
        }

        assertTrue("getFront() is throwing the wrong type of exceptions",
                exception instanceof EmptyQueueException);
    }

    /**
     * Tests the toArray() method.
     */
    public void testToArray() {
        Exception exception = null;

        try {
            queue.toArray();
            fail("toArray() is not throwing an exception when it should");
        }

        catch (Exception e) {
            exception = e;
        }

        assertTrue("toArray() is throwing the wrong type of exceptions",
                exception instanceof EmptyQueueException);

        Person[] personArray = { personMax, personZero };

        queue.enqueue(personMax);
        queue.enqueue(personZero);

        assertTrue(java.util.Arrays.equals(personArray, queue.toArray()));
    }

    /**
     * Tests the toString() method.
     */
    public void testToString() {
        String toStr = "[Zero A:0 M:0 T:0 Wants: Planet 1, " +
            "Max A:5 M:5 T:5 Wants: Planet 3]";

        queue.enqueue(personZero);
        queue.enqueue(personMax);

        assertEquals(toStr, queue.toString());
    }

    /**
     * Tests the equals() method.
     */
    public void testEquals() {
        assertTrue(queue.equals(queue));

        ArrayQueue<Person> queue2 = new ArrayQueue<Person>();

        queue2.enqueue(personZero);
        queue.enqueue(personZero);

        assertTrue(queue.equals(queue2));

        queue2.enqueue(personNormal);
        queue.enqueue(personNormal);

        assertTrue(queue.equals(queue2));

        queue2.enqueue(personMax);
        queue.enqueue(personNormal);

        assertFalse(queue.equals(queue2));

        queue2.dequeue();
        queue.dequeue();

        assertFalse(queue.equals(queue2));
    }

}
