package entity

import org.jetbrains.exposed.sql.*

object Companies: Table(){
    val id = integer("id").primaryKey()
    val name = varchar("name", 50)
    val city = varchar("city", 25)
}