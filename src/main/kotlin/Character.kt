package model

abstract class Character(
    open val name: String,
    var factionList: MutableList<Faction>,
    var assembly: Assembly?,
    val job: String
) :
    Entity(health = 100, isDead = false) {

    fun receiveHeal(healthPoints: Int) {
        if (this.health + healthPoints > 100) {
            this.health = 100
        } else {
            this.health += healthPoints
        }
    }

    fun joinFaction(faction: Faction) {
        faction.characterList.add(this)
        this.factionList.add(faction)
    }

    fun leaveFaction(faction: Faction) {
        faction.removeCharacter(this)
        this.factionList.remove(faction)
    }

    fun characterIsFriend(someone: Entity): Boolean {
        var isFriend: Boolean = false
        if (someone is Character) {
            assembly?.let {
                if (it.isInGroup(someone) || it.isInFriendGroup(someone)) {
                    isFriend = true
                }
            }
            factionList.forEach {
                run {
                    if (it.isInGroup(someone) || it.isInFriendGroup(someone)) {
                        isFriend = true
                    }
                }
            }
        }

        return isFriend
    }

    fun joinAssembly(assembly: Assembly) {
        if (this.assembly === null) {
            if (assembly.addCharacter(this)) {
                this.assembly = assembly
            }
        }
    }

    fun leaveAssembly() {
        this.assembly?.removeCharacter(this)
        this.assembly = null
    }

}

