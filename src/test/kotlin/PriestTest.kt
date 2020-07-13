import model.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

private const val FULL_LIFE = 100

class PriestTest {
    private val priest = Priest("priest")
    private val warrior = Warrior("warrior")
    private val faction = Faction("faction")
    private val assembly = Assembly("assembly", ArrayList(), mutableListOf("priest", "warrior"))

    @Test
    fun healHimselfOK(){
        priest.health = 95
        priest.heal(priest)
        println(priest.health)

        Assertions.assertEquals(FULL_LIFE, priest.health) {
            "Error, priest did not heal himself"
        }
    }

    @Test
    fun healSomeoneNotInFactionNOK(){
        warrior.health = 95

        val formerHealth = warrior.health

        priest.joinFaction(faction)

        priest.heal(warrior)

        Assertions.assertEquals(formerHealth, warrior.health) {
            "Error, Priest healed someone without faction"
        }
    }

    @Test
    fun healSomeoneInFactionOK(){
        priest.joinFaction(faction)
        warrior.joinFaction(faction)
        warrior.health = 95

        priest.heal(warrior)

        Assertions.assertEquals(FULL_LIFE, warrior.health) {
            "Error, warrior of the same faction has not been healed by priest"
        }
    }

    @Test
    fun healSomeoneOverTheTopNOK(){
        priest.heal(warrior)

        Assertions.assertEquals(FULL_LIFE, warrior.health) {
            "Error, Priest healed someone over the top"
        }
    }

    @Test
    fun healSomeoneInFriendFactionOK(){
        val friendFaction = Faction("friendFaction")

        priest.joinFaction(faction)
        warrior.joinFaction(friendFaction)
        faction.addFriend(friendFaction)
        warrior.health = 95

        priest.heal(warrior)

        Assertions.assertEquals(100, warrior.health) {
            "Error, priest don't want to heal someone from friend faction"
        }
    }

    @Test
    fun healSomeoneinSameAssemblyOK() {
        priest.joinAssembly(assembly)
        warrior.joinAssembly(assembly)

        val warriorHealth = 95
        warrior.health = warriorHealth

        priest.heal(warrior)

        Assertions.assertEquals(FULL_LIFE, warrior.health) {
            "Error, Priest did not healed someone from same assembly"
        }
    }

    @Test
    fun healSomeoneFromFriendAssemblyOK() {
        val friendAssembly = Assembly("friendAssembly", ArrayList(), mutableListOf("warrior"))

        priest.joinAssembly(assembly)
        warrior.joinAssembly(friendAssembly)
        assembly.addFriend(friendAssembly)

        warrior.health = 95
        priest.heal(warrior)

        Assertions.assertEquals(FULL_LIFE, warrior.health) {
            "Priest did not healed someone from same assembly"
        }
    }

    @Test
    fun healSomeoneNotInFriendAssemblyNOK() {
        val friendAssembly = Assembly("friendAssembly", ArrayList(), mutableListOf("warrior"))

        priest.joinAssembly(assembly)
        warrior.joinAssembly(friendAssembly)

        val warriorHealth = 95
        warrior.health = warriorHealth
        priest.heal(warrior)

        Assertions.assertEquals(warriorHealth, warrior.health) {
            "Priest healed someone not in friend assemblies"
        }
    }

}