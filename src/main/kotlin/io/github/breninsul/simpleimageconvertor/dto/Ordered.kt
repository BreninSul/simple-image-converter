/*
 * MIT License
 * Copyright (c) 2024 BreninSul
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.github.breninsul.simpleimageconvertor.dto

/**
 * The `Ordered` interface represents an object that has a defined order.
 * It provides a method to retrieve the order of the object.
 */
interface Ordered {
    fun getOrder(): Int

    /**
     * The `OrderedComparator` class is a comparator implementation that
     * compares objects based on their order. It implements the `Comparator`
     * interface and can be used to sort a collection of objects that implement
     * the `Ordered` interface. The comparison is done by calling the
     * `getOrder()` method on the objects and comparing the order values.
     *
     * The `compare()` method compares two objects and returns an integer
     * value:
     * - If `o1` is an instance of `Ordered` and `o2` is also an instance of
     *   `Ordered`, the method compares their order values and returns a
     *   negative, zero, or positive value depending on the comparison result.
     * - If `o1` is an instance of `Ordered` and `o2` is not, the method
     *   returns -1.
     * - If `o2` is an instance of `Ordered` and `o1` is not, the method
     *   returns 1.
     * - If neither `o1` nor `o2` is an instance of `Ordered`, the method
     *   returns 0.
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