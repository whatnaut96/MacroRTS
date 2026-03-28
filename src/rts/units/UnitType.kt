package rts.units

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
class UnitType {
    val id: Int = 0
    val name: String = ""
    val cost: Int = 1
    val hp: Int = 1
    val minDamage: Int = 1
    val maxDamage: Int = 1
    val attackRange: Int = 1
    val produceTime: Int = 10
    val moveTime: Int = 10
    val attackTime: Int = 10
    val harvestTime: Int = 10
    val returnTime: Int = 10
    val harvestAmount: Int = 1
    val sightRadius: Int = 4
    val isResource: Boolean = false
    val isStockpile: Boolean = false
    val canMove: Boolean = true
    val canHarvest: Boolean = false
    val canAttack: Boolean = true

    var produceList: MutableList<String> = mutableListOf()
    var producedByList: MutableList<String> = mutableListOf()

    @Transient
    var producesResolved: MutableList<UnitType> = mutableListOf()
    @Transient
    var producedByResolved: MutableList<UnitType> = mutableListOf()

    override fun hashCode() = name.hashCode()

    override fun equals(other: Any?): Boolean {
        if (other !is UnitType) return false
        return name == other.name
    }

    fun addProduces(ut: UnitType) {
        producesResolved.add(ut)
        produceList.add(ut.name)
        ut.producedByResolved.add(this)
        ut.producedByList.add(this.name)
    }

    fun resolve(utt: UnitTypeTable) {
        producesResolved = produceList.mapNotNull { utt.getUnitType(it) }.toMutableList()
        producedByResolved = producedByList.mapNotNull { utt.getUnitType(it) }.toMutableList()
    }

}


