package rts

import rts.units.Unit
import rts.units.UnitAction

data class UnitActionAssignment(
    val unit: Unit,
    var action: UnitAction,
    val time: Int,
) {
    override fun toString(): String {
        return "$unit assigned action $action at time $time"
    }
}