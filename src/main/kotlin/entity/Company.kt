package entity

import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.*

object Companies: IntIdTable(){
    val name = varchar("name", 50)
    val city = varchar("city", 25)
}