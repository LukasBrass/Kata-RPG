import model.Entity
import model.Warrior
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class EntityTest {
    val warrior: Warrior = Warrior("entity")
    val entity: Entity = Entity(10, false)

    @Test
    fun testTakeDamageOK() {
        val formerHealth = entity.health

        while (entity.health === 10) {
            warrior.attack(entity)
        }

        Assertions.assertEquals(formerHealth >= entity.health, true) {
            "Test Not OK"
        }
    }

    @Test
    fun testIsDeadOK(){
        while (entity.health > 0) {
            warrior.attack(entity)
        }

        Assertions.assertTrue(entity.isDead) {
            "Error, Entity is not dead"
        }

        val formerHealth = entity.health

        warrior.attack(entity)

        Assertions.assertEquals(formerHealth, entity.health) {
            "Warrior punched a dead man"
        }
    }
}