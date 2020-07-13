import model.Faction
import model.Warrior
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class FactionTest {

    val faction: Faction = Faction("faction")
    val warrior: Warrior = Warrior("warrior")
    val friend: Faction = Faction("friend")

    @Test
    fun addCharacterOK(){
        warrior.joinFaction(faction)

        Assertions.assertTrue(faction.characterList.size > 0) {
            "Warrior is not added"
        }
    }
    @Test
    fun removeCharacterOK(){
        warrior.joinFaction(faction)
        warrior.leaveFaction(faction)

        Assertions.assertEquals(0, faction.characterList.size) {
            "Warrior is not removed"
        }
    }

    @Test
    fun addFriendListOK(){
        faction.addFriend(friend)

        Assertions.assertTrue(faction.friendsList.size > 0) {
            "Friend faction is not added"
        }
    }

    @Test
    fun removeFriendFromListOK(){
        faction.addFriend(friend)
        faction.removeFriend(friend)

        Assertions.assertEquals(0, faction.friendsList.size) {
            "Friend is not removed from friendList"
        }
    }
}