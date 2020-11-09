package app.content.eeDays

import app.annotatedCode
import app.divider
import app.readOnlyCode
import app.runnableCode
import markdown
import react.RBuilder
import react.dom.code

val parameters: RBuilder.() -> Unit = {
    markdown("# Functions As Parameters")
//    annotatedCode(
//            annotation = """
//                Declaring Parameters follows this format
//
//                > fun myFunction(name: Type, name2: Type)
//            """,
//            code = """
//                //Example functions
//                fun foo(bar: String){}
//                fun foo2(bar: String, baz: Int){}
//            """, readOnly = true
//    )
//    runnableCode("""
//        // Declare a function that adds two numbers together named add
//        TODO()
//        println(add(5,10))
//    """.trimIndent(), tryCode = true)
//    annotatedCode(
//            annotation = """
//                ## Default Values
//
//                Any parameters can be assigned default values. If not passed then the default value will be assigned.
//
//            """,
//            code = """
//                fun log(message: String = "Hello, World!") = println(message)
//
//                log() // message will be defaulted
//                log("Something else")
//            """
//    )
//    annotatedCode(
//            annotation = """
//                ## Named Parameters
//
//                When calling functions you can add more context by supplying the name of the parameter you want to set.
//
//                This also allows you to reorder parameters for readability.
//
//                > Note: Unnamed parameters cannot proceed named parameters
//            """,
//            code = """
//                fun log(message: String, severity: String = "INFO") = println("${'$'}severity: ${'$'}message")
//
//                log("Hello") // standard call
//                log(message = "Hello")
//                log("World", severity = "WARN") // Unnamed parameters can come first if they are ordered correctly
//                log(message = "World", "WARN") // Cannot supply unnamed parameter after named
//                log(severity = "ERROR", message = "Ahhhh!") // You can reorder named parameters
//            """
//    )
    annotatedCode(
            annotation = """
                ## When the Last Parameter is a Function

                A special feature of kotlin appears when a function has a final parameter that is itself a function.

                When this case occurs you can invoke the function in a new way shown below
            """,
            code = """
                 // last parameter (modified) is a function
                fun doSomethingTo(string: String, modified: (String) -> String): String {
                    // All this does is pass the string to the modifyFunction and return the result
                    return modified(string)
                }

                // This is a function being stored as a variable
                val myModifyFunction: (String) -> String = { string -> "${'$'}string from the other side" }
                
                // Normal invocation passing function as a variable
                val modifiedString = doSomethingTo("Hello", myModifyFunction)
                println(modifiedString)

                // This has the same result, note that the function is like the body of the line
                val modifiedString2 = doSomethingTo("Hello"){ string -> "${'$'}string from the other side" }
                println(modifiedString2)
                
                // This is also equivalent
                val modifiedString3 = doSomethingTo("Hello", { string -> "${'$'}string from the other side" })
                println(modifiedString3)
            """
    )
//    annotatedCode(
//            annotation = """
//                ## Vararg
//
//                You can specify that a parameter represents mutliple values using the **vararg** keyword.
//
//                > A vararg parameter does not have to be the last parameter
//                >
//                > However you may only use a single one in any function
//
//                Vararg parameters appear as Array<T> where T is the type of the parameter (Int in the example below)
//                > When the type is a primitive type then a special Array of that type will be used. In this example its an IntArray
//            """,
//            code = """
//                fun abc(a: String, vararg b: Int){
//                |    print(a)
//                |    b.forEach { print(it) }
//                }
//                abc("ABC", 1, 2, 3)
//            """
//    )

//    runnableCode("""
//        // Declare a function named add that takes any amount of integers using vararg and adds them all together
//        // Hint: arrays have a sum function
//        TODO()
//        println(add(5,10, 9, 2, 4))
//        println(add(5, 9, 2))
//        println(add(5,10, 9, 2, 4,4,5,6))
//        println(add(*(0..100).toList().toIntArray()))
//    """.trimIndent(), tryCode = true)
//    annotatedCode(
//            annotation = """
//                ### The Spread Operator
//
//                When a function requires a vararg parameter you can also supply an array of the correct type.
//
//                To make it comply to the vararg parameter you need the spread operator *
//            """,
//            code = """
//                fun abc(a: String, vararg b: Int){
//                |    print(a)
//                |    b.forEach { print(it) }
//                }
//                val integerValues = listOf(1,2,3).toIntArray() // Coverts to IntArray
//                abc("ABC", *integerValues) // Spreads into varargs parameter
//            """
//    )
}
val functionTypes: RBuilder.() -> Unit = {
    markdown("## Function Types")
    annotatedCode(
            annotation = """
                In Kotlin functions are first class citizens. This means there is a defined type for any given function and its value can be stored in a variable.

                You can access a reference to a function using the **::** operator
            """,
            code = """
                fun myFunction() { println(1+1) }
                val refToMyFunction = ::myFunction
                
                refToMyFunction()
            """, readOnly = false
    )
    annotatedCode(
            annotation = """
                Another way of getting a reference to a function is to create it as a **lambda**

                ### Lambda Functions

                A Lambda function is just a function but it is defined without the fun keyword as follows.
            """,
            code = """
                fun myFunction(){ }
                val myLambda = { } // This is equivalent to the above line

                // This is a function that takes two arguments, a and b and returns a string (a + b)
                fun functionWithArgs(a: String, b: Int) = a + b
                val lambdaWithArgs = { a: String, b: Int -> a + b } // This is equivalent to the above line
            """, readOnly = true
    )
    annotatedCode(
            annotation = """
                Functions themselves also have types.
            """,
            code = """
                val myLambda = { }
                val myLambda: () -> Unit = { } // The type of this lambda is () -> Unit

                // Here the type is (String) -> Int
                val myLambdaWithArgs: (String) -> Int = { a: String -> a.toInt() }
            """, readOnly = true
    )
    annotatedCode(
            annotation = """
                This means they can be passed as arguments
            """,
            code = """
                // This function expects a function as its first parameter (runThis)
                fun higherOrderFunc(runThis: () -> String) = "A " + runThis()

                // We can pass it a lambda expression, or a reference to a function, or an anonymous function
                // Note: The braces have moved because its the last argument. See 'When the Last Parameter is a Function' above
                println(higherOrderFunc { "B" })
            """
    )
//    annotatedCode(
//            annotation = """
//                What does the lambda version of that look like ??
//            """,
//            code = """
//               // Here is the original again
//               fun higherOrderFunc(runThis: () -> String) = "A " + runThis()
//
//               // In lambda form it looks like this
//               val higherOrderLambda = { runThis: () -> String -> "A " + runThis() }
//
//               // What is the Type of those functions ?
//
//               val higherOrderLambda2: (() -> String) -> String = { runThis: () -> String -> "A " + runThis() }
//
//               println(higherOrderFunc { "B" })
//               println(higherOrderLambda { "C" })
//               println(higherOrderLambda2 { "D" })
//            """
//    )
    annotatedCode(
            annotation = """
                ### _it_
                If a lambda expression only takes a single argument then you can simply use the **it** keyword without specifying the parameter.
            """,
            code = """
                val logWithoutIt: (String) -> Unit = { message -> println(message) }
                val log         : (String) -> Unit = { println(it) } // This is equivalent to the above line
            """, readOnly = true
    )
    annotatedCode(
            annotation = """
                ### _
                When a lambda expression does not need a parameter it can replace its name with an underscore
            """,
            code = """
                val log: (String, String) -> Unit = { message, _ -> println(message) }
            """, readOnly = true
    )
}
val kotlinFunctions: RBuilder.() -> Unit = {

    markdown("""
            # Functions
            Functions in Kotlin are first class citizens and can be declared anywhere, including outside of any classes.

            They can be declared using the **_fun_** keyword as follows:
        """.trimIndent())

//    annotatedCode(
//            annotation = """
//                # Functions
//                Functions in Kotlin are first class citizens and can be declared anywhere, including outside of any classes.
//
//                They can be declared using the **fun** keyword as follows:
//            """,
//            code = """
//                fun log(log: String) = println(log)
//                log("Hello, World!")
//            """
//    )
    annotatedCode(
            annotation = """
                Functions can have implicit return types when using the = form.

                The following are equivalent
            """,
            code = """
                fun doSomething() = "Foo Bar"
                fun doSomething2(): String = "Foo Bar"
                fun doSomething3(): String { return "Foo Bar" }
            """, readOnly = true
    )
    annotatedCode(
            annotation = """
                Calling functions looks like this:
            """,
            code = """
                fun text() = "Foo Bar"
                println( text() )
            """
    )

    divider()
    functionTypes()
    divider()
    parameters()

    markdown("""
        
        Thats probably enough for now, and only scratching the surface of the FP support Kotlin provides. Infix functions, extension functions, varying scope, receiver types etc etc. 
        
         # Summary
         Kotlin has various ways to declare a function. Because functions can be assigned to a val, and have a type, they can be passed around and passed as parameters to other functions. 
         There is also the special case, where if the last parameter is a function type, when invoking and passing the function in as a paramter, you can do it outside of the brackets. 
            """.trimIndent())
}





////    divider()
////    returnTypes()
////    divider()
////    memberFunctions()
////    divider()
////    genericFunctions()
////    divider()
////    functionScopes()
////    divider()
////    functionTypes()
////    divider()
////    currying()
////    divider()
////    extensionFunctions()
////    divider()
////    receiverTypes()
////    divider()
////    stdFunctions()
////    divider()
////    infix()
////    infixFuncDecomp()
////    divider()
////    operators()
////    divider()
////    inline()
////    divider()
////    tailRecursion()
//}



