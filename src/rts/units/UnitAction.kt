package rts.units

import kotlin.random.Random
import kotlinx.serialization.Serializable
import rts.ResourceUsage


enum class ActionType { NONE, MOVE, HARVEST, RETURN, PRODUCE, ATTACK_LOCATION }

enum class Direction { NONE, UP, DOWN, LEFT, RIGHT }

@Serializable
data class UnitAction (
    var actionType: ActionType = ActionType.NONE,
    val parameter: Int = 0,
    val x: Int = 0,
    val y: Int = 0,
    val unitTypeName: String? = null,
    val duration: Int = 0,
){

    @Transient
    var unitType: UnitType? = null

    companion object{
        @Transient
        val r = Random.Default
    }

    val direction: Direction get() = Direction.entries.getOrElse(parameter + 1) { Direction.NONE }

    override fun equals(other: Any?): Boolean {
        if (other !is UnitAction) return false
        if (other.actionType != actionType) return false

        return when (actionType) {
            ActionType.NONE, ActionType.MOVE, ActionType.HARVEST, ActionType.RETURN ->
                other.parameter == parameter
            ActionType.ATTACK_LOCATION ->
                other.x == x && other.y == y
            ActionType.PRODUCE ->
                other.parameter == parameter && other.unitType == unitType
        }
    }

    override fun hashCode(): Int {
        var hash = this.actionType.ordinal
        hash = 19 * hash + this.parameter
        hash = 19 * hash + this.x;
        hash = 19 * hash + this.y;
        hash = 19 * hash + (unitType.hashCode() ?: 0)
        return hash
    }

    override fun toString(): String {
        val body = when (actionType) {
            ActionType.ATTACK_LOCATION -> "$x,$y"
            ActionType.NONE -> "$parameter"
            else -> buildString {
                if (direction != Direction.NONE) {
                    append(direction.name.lowercase())
                }
                if (direction != Direction.NONE) {
                    append(",")
                }
                append(unitType?.name)
            }
        }
        return "${actionType.name.lowercase()}($body)"
    }

}
