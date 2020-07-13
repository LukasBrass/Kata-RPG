import model.Assembly
import model.Faction
import model.Priest
import model.Warrior
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

private const val FULL_LIFE = 100
class WarriorTest {
    private val warrior = Warrior("warrior")
    private val priest = Priest("priest")
    private val faction = Faction("faction")
    private val friendFaction = Faction("friend")
    val assembly = Assembly("assembly", ArrayList(), mutableListOf("warrior", "priest"))

    @Test
    fun hurtSomeoneNotInFactionOK(){
        warrior.joinFaction(faction)
        priest.joinFaction(friendFaction)

        val formerHealth = priest.health

        while (priest.health === FULL_LIFE) {
            warrior.attack(priest)
        }

        Assertions.assertTrue(priest.health < formerHealth) {
            "Warrior didn't attack someone from foreign Faction"
        }
    }

    @Test
    fun hurtHimselfOK(){
        while(warrior.health === FULL_LIFE) {
            warrior.attack(warrior)
        }

        Assertions.assertTrue(FULL_LIFE > warrior.health) {
            "Warrior couldn't attack himself"
        }
    }

    @Test
    fun healHimselfOK() {
        warrior.health = 99
        warrior.heal()
        Assertions.assertEquals(FULL_LIFE, warrior.health) {
            "Warrior didn't heal himself"
        }
    }

    @Test
    fun healHimselfOverTheTopNOK() {
        warrior.heal()
        Assertions.assertEquals(FULL_LIFE, warrior.health) {
            "Warrior healed himself over the top"
        }
    }

    @Test
    fun hurtSomeoneInFactionNOK(){
        warrior.joinFaction(faction)
        priest.joinFaction(faction)

        warrior.attack(priest)

        Assertions.assertEquals(FULL_LIFE, priest.health) {
            "Warrior attacked someone from same Faction"
        }
    }
    @Test
    fun hurtSomeoneInFriendFactionNOK(){
        warrior.joinFaction(faction)
        priest.joinFaction(friendFaction)
        faction.addFriend(friendFaction)

        warrior.attack(priest)

        Assertions.assertEquals(FULL_LIFE, priest.health) {
            "Warrior hurt someone from a friend faction"
        }

    }

    @Test
    fun hurtSomeoneInSameAssemblyNOK(){
        warrior.joinAssembly(assembly)
        priest.joinAssembly(assembly)

        warrior.attack(priest)

        Assertions.assertEquals(FULL_LIFE, priest.health) {
            "Warrior hurt someone from same assembly"
        }
    }
    @Test
    fun hurtSomeoneInFriendAssemblyNOK(){
        val friendAssembly = Assembly("friendAssembly", ArrayList(), mutableListOf("priest"))
        warrior.joinAssembly(assembly)
        assembly.addFriend(friendAssembly)
        priest.joinAssembly(friendAssembly)

        warrior.attack(priest)

        Assertions.assertEquals(FULL_LIFE, priest.health) {
            "Warrior hurt someone from friend assemblies"
        }
    }
    @Test
    fun hurtSomeoneNotInSameAssembly(){
        val friendAssembly = Assembly("friendAssembly", ArrayList(), mutableListOf("priest"))
        warrior.joinAssembly(assembly)
        priest.joinAssembly(friendAssembly)

        while (priest.health === FULL_LIFE) {
            warrior.attack(priest)
        }

        Assertions.assertTrue(FULL_LIFE > priest.health) {
            "Warrior hurt someone from friend assemblies"
        }
    }

}