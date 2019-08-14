import entity.Companies
import entity.Employees
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun main(){
    // println("Hello")
    Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver") // <1>
    transaction{    // <2>
        addLogger(StdOutSqlLogger)  // <3>
        SchemaUtils.create(Companies, Employees)    // <4>

        //Insert
        Companies.insert {
            //it[id]=EntityID<Int>(1, Companies)
            it[name] = "Apple"
            it[city] = "San Francisco"
        }
        Companies.insert {
            //it[id]=2
            it[name] = "Amazon"
            it[city] = "Seattle"
        }

        Employees.insert { it[id]=1; it[name]="Bob"; it[age]=30; it[company]=EntityID<Int>(1, Companies) }
        Employees.insert { it[id]=2; it[name]="Andrew"; it[age]=32; it[company]=EntityID<Int>(2, Companies) }
        Employees.insert { it[id]=3; it[name]="Sam"; it[age]=35; it[company]=EntityID<Int>(1, Companies) }
        Employees.insert { it[id]=4; it[name]="Alice"; it[age]=32; it[company]=EntityID<Int>(2, Companies) }
        Employees.insert { it[id]=5; it[name]="David"; it[age]=29; it[company]=EntityID<Int>(2, Companies) }

        // Select
        Employees.selectAll().forEach { println("$it") }
        /* Employees.select {Employees.id greater 3}
            .forEach { println(it[Employees.name]) } */
        /* Employees.slice(Employees.name).select {Employees.name like "S%"}
            .forEach { println(it) } */

        // Update
        /* Employees.update({Employees.id eq 5}){
            it[name] = "Dave"
        } */

        // Inner join
        /* (Employees innerJoin Companies).select {
            Companies.id eq Employees.company
        }.forEach { println("$it") } */

        // Group By
        /* (Employees innerJoin Companies).slice(Companies.name, Employees.id.count()).select {
            Companies.id eq Employees.company
        }.groupBy(Companies.id).forEach { println("$it") } */


        //Delete
        /* Employees.deleteWhere { Employees.name eq "Sam" }

        //Drop tables
        SchemaUtils.drop (Companies, Employees) */
    }
}