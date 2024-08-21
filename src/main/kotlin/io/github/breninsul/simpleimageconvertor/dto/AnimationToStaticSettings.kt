package io.github.breninsul.simpleimageconvertor.dto

/**
 * AnimationToStaticSettings is a class that represents the settings for converting an animation to a static image.
 *
 * @property useFrame Represents the frame number which should be converted as a static image. The value Long.MAX_VALUE is used to represent the last frame, while 0 is used to represent
 *  the first frame.
 * @property strategy Represents the strategy used for converting an animation to a static image. It is an enumeration type, StrategyEnum, with three possible values: FIRST_FRAME,
 *  LAST_FRAME, and SPECIFIC_FRAME.
 */
open class AnimationToStaticSettings(
    /**
     * Represents the frame number which should be converted as static image. Use Long.MAX_VALUE for last, 0 for first
     */
    open val useFrame:Long=Long.MAX_VALUE,
    /**
     * Represents the strategy used for converting an animation to a static image.
     */
    open val strategy:StrategyEnum=StrategyEnum.FIRST_FRAME
):WriterSettings {
    enum class StrategyEnum {
        FIRST_FRAME,
        MIDDLE_FRAME,
        LAST_FRAME,
        SPECIFIC_FRAME,
    }
}