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

package io.github.breninsul.simpleimageconvertor.dto.writer

/**
 * AnimationToStaticSettings is a class that represents the settings for
 * converting an animation to a static image.
 *
 * @property useFrame Represents the frame number which should be converted
 *    as a static image. The value Long.MAX_VALUE is used to represent the
 *    last frame, while 0 is used to represent the first frame.
 * @property strategy Represents the strategy used for converting an
 *    animation to a static image. It is an enumeration type, StrategyEnum,
 *    with three possible values: FIRST_FRAME, LAST_FRAME, and
 *    SPECIFIC_FRAME.
 */
open class AnimationToStaticSettings(
    /**
     * Represents the frame number which should be converted as static image.
     * Use Long.MAX_VALUE for last, 0 for first
     */
    open val useFrame: Long = Long.MAX_VALUE,
    /**
     * Represents the strategy used for converting an animation to a static
     * image.
     */
    open val strategy: StrategyEnum = StrategyEnum.FIRST_FRAME
) : WriterSettings {
    enum class StrategyEnum {
        FIRST_FRAME,
        MIDDLE_FRAME,
        LAST_FRAME,
        SPECIFIC_FRAME,
    }
}