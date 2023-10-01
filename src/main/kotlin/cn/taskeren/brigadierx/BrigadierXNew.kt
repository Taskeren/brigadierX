package cn.taskeren.brigadierx

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.RequiredArgumentBuilder

operator fun <S> CommandDispatcher<S>.invoke(block: CommandDispatcher<S>.() -> Unit) = block(this)

context(CommandDispatcher<S>)
operator fun <S> String.invoke(block: LiteralArgumentBuilder<S>.() -> Unit) = register(this, block)

context(CommandDispatcher<*>, LiteralArgumentBuilder<S>)
operator fun <S> String.invoke(block: LiteralArgumentBuilder<S>.() -> Unit) = literal(this, block)

context(CommandDispatcher<*>, LiteralArgumentBuilder<S>)
operator fun <S, T> String.invoke(type: ArgumentType<T>, block: RequiredArgumentBuilder<S, T>.() -> Unit) =
	argument(this, type, block)