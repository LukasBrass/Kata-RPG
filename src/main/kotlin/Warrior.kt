package model

class Warrior(override val name: String) :
    Character(name, factionList = ArrayList(), assembly = null, job = "warrior") {

    fun attack(foe: Entity) {
        val damage: Int = (0..9).random()
        if(foe === this) {
            foe.isAttacked(damage)
        } else if(!this.characterIsFriend(foe)) {
            foe.isAttacked(damage)
        }
    }

    fun heal() {
        this.receiveHeal(1)
    }
}