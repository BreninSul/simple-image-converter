package io.github.breninsul.simpleimageconvertor.dto

interface Ordered {
    fun getOrder():Int

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