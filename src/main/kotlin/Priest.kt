package model

class Priest(override val name: String) :
    Character(name, factionList = ArrayList(), assembly = null, job = "priest") {


    fun heal(ally: Character) {
        val healthPoints: Int = (5..10).random()
        if (ally === this) {
            ally.receiveHeal(healthPoints)
        } else if (this.characterIsFriend(ally)) {
            ally.receiveHeal(healthPoints)
        }
    }
}