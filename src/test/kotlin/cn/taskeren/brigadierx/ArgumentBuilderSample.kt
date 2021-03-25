package cn.taskeren.brigadierx

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.IntegerArgumentType
import com.mojang.brigadier.arguments.StringArgumentType
import kotlin.system.exitProcess

import com.mojang.brigadier.builder.LiteralArgumentBuilder.literal
import com.mojang.brigadier.builder.RequiredArgumentBuilder.argument

fun main() {

    val d = CommandDispatcher<Any>()

    d.register("apple") {
        executes {
            println("apple")

            0
        }

        literal("banana") {
            executex {
                println("apple banana")
            }
        }

        literal("orange") {
            executex {
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
            executex {
                println("!!apple ${it.getArgument("string", String::class.java)}")
            }

            argument("int", IntegerArgumentType.integer(0, 10)) {
                executex {
                    println(
                        "@@apple ${StringArgumentType.getString(it, "string")} ${IntegerArgumentType.getInteger(it, "int")}"
                    )
                }
            }

            literal("decade") {
                executex {
                    println("**apple ${StringArgumentType.getString(it, "string")} decade!")
                }
            }
        }
    }

    while(true) {
        val l = readLine()
        if(l == "q") {
            exitProcess(0)
        } else {
            val result = runCatching {
                d.execute(l, "x-console")
            }
            if(result.isFailure) {
                result.exceptionOrNull()?.printStackTrace()
            } else {
                println(result.getOrNull())
            }
        }
    }

}