# BrigadierX

Brigadier 语法糖。

## 使用帮助

- 使用 `LiteralArgumentBuilder<Any>("foo") { /* 其他内容 */ }` 快速开始。
- 使用 `literal(name) { /* 其他内容 */ }` 来添加其他的文本指令。
- 使用 `argument(name, type）{ /* 其他内容 */ }` 来添加数据内容。
- 使用 `executes { /* 指令内容 */ }` 来注册指令操作。
- 使用 `executex { /* 指令内容 */ }` 来注册无返回指令操作。（永远返回 Command.SINGLE_SUCCESS = 1）
- 原 `ArgumentBuilder` 中内建的 `forward`, `fork`, `redirect` 等方法均可使用。（不会用，所以没优化）

下方两段代码为 BrigadierX 与原版 Brigadier 对比。

```kotlin
LiteralArgumentBuilder<Any>("foo") {
    executes {
        println("/foo")
        1 // 执行返回值
    }
    
    literal("bar") {
        executesX {
            println("/foo bar")
        }
        
        argument("str", StringArgumentType.string()) {
            executesX {
                println("/foo bar ${StringArgumentType.getString(it, "str")}")
            }
        }
    }
}
```

```kotlin
// import com.mojang.brigadier.builder.LiteralArgumentBuilder.literal
// import com.mojang.brigadier.builder.RequiredArgumentBuilder.argument

literal<Any>("foo").then(
    literal<Any>("bar").then(
        argument("str", StringArgumentType.string()).executes {
            println("/foo bar ${StringArgumentType.getString(it, "str")}")
            1
        }
    ).executes {
        println("/foo bar")
        1
    }
).executes {
    println("/foo")
    1
}
```