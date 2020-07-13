package model

open class Faction(open val name: String, open var characterList: MutableList<Character> = ArrayList(), open var friendsList: MutableList<Faction> = ArrayList()){

    fun isInGroup(character: Character): Boolean {
        return characterList.contains(character)
    }

    open fun addCharacter(character: Character): Boolean {
        this.characterList.add(character)
        return true
    }

    open fun removeCharacter(character: Character) {
        this.characterList.remove(character)
    }

    fun isInFriendGroup(character: Character): Boolean {
        var result: Boolean = false;
        this.friendsList.forEach {
            if(it.isInGroup(character)) {
                result = true
            }
        }
        return result
    }

    fun addFriend(faction: Faction) {
        if(!friendsList.contains(faction)){
            friendsList.add(faction)
            faction.addFriend(this)
        }
    }

    fun removeFriend(faction: Faction) {
        if (friendsList.contains(faction)) {
            friendsList.remove(faction)
            faction.removeFriend(this)
        }
    }

}