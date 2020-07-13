import model.Assembly
import model.Priest
import model.Warrior
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class AssemblyTest {
    val warrior: Warrior = Warrior("warrior")
    val priest: Priest = Priest("priest")
    val warrior2: Warrior = Warrior("warrior2")
    val assembly: Assembly = Assembly("assembly", allowedRoles = mutableListOf("warrior"))

    @Test
    fun addCharacterWithAllowedRoleOK(){
        warrior.joinAssembly(assembly)

        Assertions.assertTrue(assembly.characterList.contains(warrior)) {
            "Warrior is not added in assembly"
        }
    }

    @Test
    fun checkFirstCharacterIsMasterOK() {
        warrior.joinAssembly(assembly)
        Assertions.assertEquals(warrior, assembly.master) {
            "First character is not assembly master"
        }
    }

    @Test
    fun addCharacterWithUnauthorizedRoleNOK(){
        priest.joinAssembly(assembly)

        Assertions.assertTrue(!assembly.characterList.contains(priest)) {
            "unauthorized role has been added to assembly"
        }
    }

    @Test
    fun changeNameAsMasterOK(){
        warrior.joinAssembly(assembly)

        assembly.updateName("assembly2", warrior)

        Assertions.assertEquals("assembly2", assembly.name) {
            "Name has not been changed"
        }
    }

    @Test
    fun changeNameAsNotMasterNOK() {
        warrior.joinAssembly(assembly)
        warrior2.joinAssembly(assembly)

        val formerAssemblyName = assembly.name

        assembly.updateName("assembly2", warrior2)
        Assertions.assertEquals(formerAssemblyName, assembly.name) {
            "Name has been changed by a non-master member"
        }
    }

    @Test
    fun getNewMasterOK(){
        warrior.joinAssembly(assembly)
        warrior2.joinAssembly(assembly)

        warrior.leaveAssembly()

        Assertions.assertEquals(warrior2, assembly.master) {
            "Master is weird"
        }
    }
}