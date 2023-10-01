package cn.taskeren.sample.brigadier

import cn.taskeren.brigadierx.executesX
import cn.taskeren.brigadierx.invoke
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType

fun main() {
	TestNew().testCase1()
}

internal class TestNew {

	fun testCase1() {
		val dispatcher = CommandDispatcher<Any>()

		dispatcher {
			"help" {
				"what" {

					"what"(StringArgumentType.greedyString()) {
						executesX {
							val what = StringArgumentType.getString(it, "what")
							println("Help What? $what")
						}
					}

					executesX {
						println("Idk what to help!")
					}
				}

				executesX {
					println("Help!")
				}
			}
		}

		dispatcher.execute(readlnOrNull(), object {})
	}

}