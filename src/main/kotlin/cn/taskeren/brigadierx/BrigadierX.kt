@file:Suppress("unused", "DeprecatedCallableAddReplaceWith")

package cn.taskeren.brigadierx

import com.mojang.brigadier.Command
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.builder.ArgumentBuilder
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.RequiredArgumentBuilder
import com.mojang.brigadier.context.CommandContext

@Deprecated("Removal", ReplaceWith("LiteralArgumentBuilder.literal<S>(name)"), DeprecationLevel.WARNING)
fun <S> newLiteralArgBuilder(name: String): LiteralArgumentBuilder<S> = LiteralArgumentBuilder.literal(name)

@Deprecated("Removal",level = DeprecationLevel.WARNING)
fun <S> newLiteralArgBuilder(name: String, func: LiteralArgumentBuilder<S>.() -> Unit): LiteralArgumentBuilder<S> =
	LiteralArgumentBuilder.literal<S>(name).apply { func.invoke(this) }

/**
 * Register a command.
 *
 * @param name The name of the command.
 * @param func The function to build the command.
 */
fun <S> CommandDispatcher<S>.register(name: String, func: LiteralArgumentBuilder<S>.() -> Unit): CommandDispatcher<S> {
	val rootCommand = LiteralArgumentBuilder.literal<S>(name)
	func.invoke(rootCommand)
	this.register(rootCommand)
	return this
}

/**
 * Register a command.
 *
 * @param name The name of the command.
 * @param func The function to build the command.
 */
fun <S> CommandDispatcher<S>.literal(
	name: String,
	func: (LiteralArgumentBuilder<S>.() -> Unit)? = null
): CommandDispatcher<S> {
	val rootCommand = LiteralArgumentBuilder.literal<S>(name)
	func?.invoke(rootCommand)
	this.register(rootCommand)
	return this
}

/**
 * Register a subcommand.
 *
 * @param name The name of the subcommand.
 * @param func The function to build the subcommand.
 */
fun <S> LiteralArgumentBuilder<S>.literal(
	name: String,
	func: (LiteralArgumentBuilder<S>.() -> Unit)? = null
): LiteralArgumentBuilder<S> {
	val subcommand = LiteralArgumentBuilder.literal<S>(name)
	func?.invoke(subcommand)
	this.then(subcommand)
	return subcommand
}

/**
 * Register a subcommand.
 *
 * @param name The name of the subcommand.
 * @param func The function to build the subcommand.
 */
fun <S, T> RequiredArgumentBuilder<S, T>.literal(
	name: String,
	func: (LiteralArgumentBuilder<S>.() -> Unit)? = null
): LiteralArgumentBuilder<S> {
	val subcommand = LiteralArgumentBuilder.literal<S>(name)
	func?.invoke(subcommand)
	this.then(subcommand)
	return subcommand
}

/**
 * Register a subcommand.
 *
 * @param name The name of the subcommand.
 * @param func The function to build the subcommand.
 */
fun <S, T> LiteralArgumentBuilder<S>.argument(
	name: String,
	type: ArgumentType<T>,
	func: (RequiredArgumentBuilder<S, T>.() -> Unit)? = null
): RequiredArgumentBuilder<S, T> {
	val rab = RequiredArgumentBuilder.argument<S, T>(name, type)
	func?.invoke(rab)
	this.then(rab)
	return rab
}

/**
 * Register a subcommand.
 *
 * @param name The name of the subcommand.
 * @param func The function to build the subcommand.
 */
fun <S, T1, T2> RequiredArgumentBuilder<S, T1>.argument(
	name: String,
	type: ArgumentType<T2>,
	func: (RequiredArgumentBuilder<S, T2>.() -> Unit)? = null
): RequiredArgumentBuilder<S, T2> {
	val rab = RequiredArgumentBuilder.argument<S, T2>(name, type)
	func?.invoke(rab)
	this.then(rab)
	return rab
}

/**
 * Register the execute function.
 *
 * @param func The function to execute.
 */
fun <S, T : ArgumentBuilder<S, T>, R> ArgumentBuilder<S, T>.executesX(func: (ctx: CommandContext<S>) -> R) {
	this.executes {
		func.invoke(it)
		Command.SINGLE_SUCCESS
	}
}