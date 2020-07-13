package model

class Assembly(
    override var name: String,
    override var characterList: MutableList<Character> = ArrayList(),
    var allowedRoles: MutableList<String>,
    var master: Character? = null
): Faction(characterList = characterList, name = name) {

    override fun addCharacter(character: Character): Boolean {
        if(this.allowedRoles.contains(character.job)) {
            this.characterList.add(character)
            if(this.characterList.size === 1) {
                this.master = character
            }
            return true
        }
        return false
    }

    override fun removeCharacter(character: Character) {
        super.removeCharacter(character)
        if(character === this.master) {
            this.masterLeaves()
        }
    }

    fun masterLeaves() {
        this.master = characterList.random()
    }

    fun updateName(name: String, character: Character) {
        if(character === this.master) {
            this.name = name
        }
    }

}
