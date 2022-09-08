package cn.taskeren.sample.brigadier

import cn.taskeren.brigadierx.*
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.IntegerArgumentType
import com.mojang.brigadier.arguments.StringArgumentType
import org.junit.jupiter.api.Test

object ArgumentBuilderSample {

    @Test
    fun testBrigadierX() {

        val d = CommandDispatcher<Any>()

        d.register("apple") {
            executes {
                println("apple")

                0
            }

            literal("banana") {
                executesX {
                    println("apple banana")
                }
            }

            literal("orange") {
                executesX {
                    println("apple orange")
                }

                literal("berry") {
                    executes {
                        println("apple orange berry")
                        0
                    }
                }
            }

            argument("string", StringArgumentType.string()) {
                executesX {
                    println("!!apple ${it.getArgument("string", String::class.java)}")
                }

                argument("int", IntegerArgumentType.integer(0, 10)) {
                    executesX {
                        println(
                            "@@apple ${StringArgumentType.getString(it, "string")} ${IntegerArgumentType.getInteger(it, "int")}"
                        )
                    }
                }

                literal("decade") {
                    executesX {
                        println("**apple ${StringArgumentType.getString(it, "string")} decade!")
                        @Suppress("UNUSED_VARIABLE") val a = null
                    }
                }
            }
        }

        // DO THE TEST
        d.execute("apple", "APPLE")
        d.execute("apple orange", "ORANGE")
        d.execute("apple orange berry", "BERRY")
        d.execute("apple something", "SOMETHING")
        d.execute("apple something 5", "SOMETHING WITH NUMBER")
        d.execute("apple something decade", "DECADE SOMETHING")
    }

}