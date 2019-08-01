package entity

import org.jetbrains.exposed.sql.*

object Employees: Table(){
    val id = integer("id").primaryKey()
    val name = varchar("name", 50)
    val age = integer("age")
    val company = (integer("company") references Companies.id)
}