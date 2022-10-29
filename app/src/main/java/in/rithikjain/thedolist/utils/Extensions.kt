package `in`.rithikjain.thedolist.utils

import androidx.glance.GlanceModifier

fun GlanceModifier.conditional(
    condition: Boolean,
    modifier: GlanceModifier.() -> GlanceModifier,
): GlanceModifier {
    return if (condition) {
        then(modifier(GlanceModifier))
    } else {
        this
    }
}