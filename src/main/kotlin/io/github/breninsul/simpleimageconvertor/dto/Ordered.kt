package io.github.breninsul.simpleimageconvertor.dto

/**
 * The `Ordered` interface represents an object that has a defined order. It provides a method to retrieve the order of the object.
 */
interface Ordered {
    fun getOrder():Int

    /**
     * The `OrderedComparator` class is a comparator implementation that compares objects based on their order.
     * It implements the `Comparator` interface and can be used to sort a collection of objects that implement the `Ordered` interface.
     * The comparison is done by calling the `getOrder()` method on the objects and comparing the order values.
     *
     * The `compare()` method compares two objects and returns an integer value:
     * - If `o1` is an instance of `Ordered` and `o2` is also an instance of `Ordered`,
     *   the method compares their order values and returns a negative, zero, or positive value depending on the comparison result.
     * - If `o1` is an instance of `Ordered` and `o2` is not, the method returns -1.
     * - If `o2` is an instance of `Ordered` and `o1` is not, the method returns 1.
     * - If neither `o1` nor `o2` is an instance of `Ordered`, the method returns 0.
     */
    object OrderedComparator : Comparator<Any> {
        override fun compare(o1: Any?, o2: Any?): Int {
            return if (o1 is Ordered) {
                if (o2 is Ordered) {
                    o1.getOrder().compareTo(o2.getOrder())
                } else {
                    -1
                }
            } else {
                if (o2 is Ordered) {
                    1
                } else {
                    0
                }
            }
        }
    }
}