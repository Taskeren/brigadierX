@file:Suppress("unused")

package cn.taskeren.brigadierx

import com.mojang.brigadier.Command
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.builder.ArgumentBuilder
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.RequiredArgumentBuilder
import com.mojang.brigadier.context.CommandContext

/**
 * LiteralArgumentBuilder 构造方法语法糖
 */
fun <S> newLiteralArgBuilder(name: String): LiteralArgumentBuilder<S> = LiteralArgumentBuilder.literal<S>(name)

/**
 * LiteralArgumentBuilder 构造方法语法糖
 */
fun <S> newLiteralArgBuilder(name: String, func: LiteralArgumentBuilder<S>.() -> Unit): LiteralArgumentBuilder<S> = LiteralArgumentBuilder.literal<S>(name).apply { func.invoke(this) }

/**
 * 注册指令
 *
 * @param name 根命令名称
 * @param func 传入根命令，进行命令注册
 * @return Dispatcher
 */
fun <S> CommandDispatcher<S>.register(name: String, func: LiteralArgumentBuilder<S>.() -> Unit): CommandDispatcher<S> {
    val rootCommand = newLiteralArgBuilder<S>(name)
    func.invoke(rootCommand)
    this.register(rootCommand)
    return this
}

/* ==================================
 *
 *              literal
 *
 * ================================== */

fun <S> LiteralArgumentBuilder<S>.literal(name: String): LiteralArgumentBuilder<S> {
    val subcommand = newLiteralArgBuilder<S>(name)
    this.then(subcommand)
    return subcommand
}

fun <S> LiteralArgumentBuilder<S>.literal(name: String, func: LiteralArgumentBuilder<S>.() -> Unit): LiteralArgumentBuilder<S> {
    val subcommand = LiteralArgumentBuilder.literal<S>(name)
    func.invoke(subcommand)
    this.then(subcommand)
    return subcommand
}

fun <S, T> RequiredArgumentBuilder<S, T>.literal(name: String): LiteralArgumentBuilder<S> {
    val subcommand = LiteralArgumentBuilder.literal<S>(name)
    this.then(subcommand)
    return subcommand
}

fun <S, T> RequiredArgumentBuilder<S, T>.literal(name: String, func: LiteralArgumentBuilder<S>.() -> Unit): LiteralArgumentBuilder<S> {
    val subcommand = LiteralArgumentBuilder.literal<S>(name)
    func.invoke(subcommand)
    this.then(subcommand)
    return subcommand
}

/* ==================================
 *
 *             argument
 *
 * ================================== */

fun <S, T> LiteralArgumentBuilder<S>.argument(name: String, type: ArgumentType<T>): RequiredArgumentBuilder<S, T> {
    val rab = RequiredArgumentBuilder.argument<S, T>(name, type)
    this.then(rab)
    return rab
}

fun <S, T> LiteralArgumentBuilder<S>.argument(name: String, type: ArgumentType<T>, func: RequiredArgumentBuilder<S, T>.() -> Unit): RequiredArgumentBuilder<S, T> {
    val rab = RequiredArgumentBuilder.argument<S, T>(name, type)
    func.invoke(rab)
    this.then(rab)
    return rab
}

fun <S, T1, T2> RequiredArgumentBuilder<S, T1>.argument(name: String, type: ArgumentType<T2>): RequiredArgumentBuilder<S, T2> {
    val rab = RequiredArgumentBuilder.argument<S, T2>(name, type)
    this.then(rab)
    return rab
}

fun <S, T1, T2> RequiredArgumentBuilder<S, T1>.argument(name: String, type: ArgumentType<T2>, func: RequiredArgumentBuilder<S, T2>.() -> Unit): RequiredArgumentBuilder<S, T2> {
    val rab = RequiredArgumentBuilder.argument<S, T2>(name, type)
    func.invoke(rab)
    this.then(rab)
    return rab
}

/* ==================================
 *
 *        Modified: executes
 *             executex
 *
 * ================================== */

fun <S, T: ArgumentBuilder<S, T>> ArgumentBuilder<S, T>.executex(func: (CommandContext<S>) -> Any) {
    this.executes {
        func.invoke(it)
        Command.SINGLE_SUCCESS
    }
}