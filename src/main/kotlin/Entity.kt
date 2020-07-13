package model

open class Entity(var health : Int, var isDead: Boolean = false) {

    open fun isAttacked(damage: Int) {
        if(!this.isDead) {
            this.health -= damage

            if(this.health <= 0) {
                this.isDead = true
            }
        }
    }
}