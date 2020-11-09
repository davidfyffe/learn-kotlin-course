package app.content.eeDays

import Markdown
import react.RBuilder
import app.annotatedCode
import app.divider
import markdown
import app.runnableCode

val returnTypesMonad: RBuilder.() -> Unit = {

//    Markdown {
//        attrs.source = """
//                ![alt text](lambda.png "Lambda")
//                # FP Style
//                """.trimIndent().trimMargin()
//    }


    annotatedCode(readOnly=false,
            annotation = """
        ##  
        
        Functional Programming, does not work with exceptions. The premise that the output of function a is the input to function b, breaks down with exceptions. 
        
        #### No Exceptions
        
        Exceptions have two main problems:

        Exceptions moving us away from simple reasoning about what a function might be doing, and making it possible to write confusing exception-based spaghetti code. 
        Throwing exceptions should be used only for error handling, not for control flow. In functional programming, avoid throwing exceptions altogether, except under extreme circumstances where we cannot recover.
       
        The error-handling strategy we use is completely type-safe, and we get full assistance from the type-checker in forcing us to deal with errors, with a minimum of syntactic noise.
            """,

            code = """
                              
                fun iMightThrow(a :Int, b: Int): Int {
                    return a / b
                }
                fun tryIt(){
                    iMightThrow(3, 3).let(::println)
                    iMightThrow(0, 3).let(::println)
                    iMightThrow(3, 0).let(::println)
                }
                tryIt()
                
                """.trimIndent().trimMargin()
    )

    annotatedCode(readOnly=false,
            annotation = """
            Try that again but handle the exception
                        """,

            code = """
          fun iMightThrow(a :Int, b: Int): Int {
                return try { a / b } catch (e : Exception) { println("An error occurred: ${'$'}{e.message}"); -99999}
            }
        
            fun tryIt(){
                iMightThrow(3, 3).let(::println)
                iMightThrow(0, 3).let(::println)
                iMightThrow(3, 0).let(::println)
            }
            tryIt()
                """.trimIndent().trimMargin()
    )
    markdown("""
         Thats a little better but now we need to check for -99999 every time we use this function. 
        
    """.trimIndent())
    annotatedCode(readOnly=false,
            annotation = """
           What about this? 
           Catch the error. But throw a known Exception type  
                        """,

            code = """
                  class BadSum(message : String) : Exception(message)

        fun iMightThrow(a :Int, b: Int): Int {
            return try { a / b } catch (e : Exception) { throw BadSum("A bad divisor")}
        }

        fun tryIt(){
            try {
                iMightThrow(3, 0).let(::println)
            } catch (e : BadSum) { println("An error occurred: ${'$'}{e.message}") }
        }
        tryIt()
        // What happens if someone uses my funciton out of context. 
        // iMightThrow(3, 0).let(::println)
                """.trimIndent().trimMargin()
    )

    divider()

    markdown("""
         # The _other_ way, in FP style.    
         

         ### Sealed Classes
         A sealed class is simply a class that can only be extended by classes defined within it.

        > Sealed classes are also known as algebraic data types
        
         """.trimIndent())

    runnableCode("""
        sealed class Either {
            data class Success(val result: Int): Either()
            data class Failure(val error: Exception): Either()
        }

        // The compiler now knows that an Either can only ever be either a Success or an Failure
        // This makes the when expression work nicely with the sealed type
        fun main(){
            fun iMightThrow(a :Int, b: Int): Either {
                return try { Either.Success(a / b) } catch (e : Exception) { Either.Failure(e) }
            }
        
            when(val division = iMightThrow(3,0)) {
                is Either.Success -> println(division.result)
                is Either.Failure -> println(division.error.message)
            }
        }
    """.trimIndent(), inMain = false)


    runnableCode("""
        sealed class Either {
            data class Success(val result: Int): Either()
            data class Failure(val error: Exception): Either()
        }
        
        typealias Success = Either.Success
        typealias Failure = Either.Failure
        // The compiler now knows that an Either can only ever be either a Success or an Failure
        // This makes the when expression work nicely with the sealed type
        fun main(){
            fun iMightThrow(a :Int, b: Int): Either {
                return try { Success(a / b) } catch (e : Exception) { Failure(e) }
            }

            val listOfDivisions = listOf(
                iMightThrow(3, 34),
                iMightThrow(4, 3),
                iMightThrow(14, 3),
                iMightThrow(14, 63),
                iMightThrow(37, 0)
            )
        
            listOfDivisions.forEachIndexed { index, it ->
                when(it){ // Compiler will only let you do all scenarios
                    is Success -> println("${'$'}index Good division with result: ${'$'}{it.result}")
                    is Failure -> println("${'$'}index Bad Event with error ${'$'}{it.error.message}")
                }
            }
        }
    """.trimIndent(), inMain = false)

    markdown("""
        
        For a full FP toolkit for Kotlin, see the Arrow library.
        https://arrow-kt.io/
         
         
         """.trimIndent())

    markdown("""
        ### Summary
        Don't use exceptions to control program flow. It will bite you at some stage.
        
        Functions that throw exceptions are not Referentially Transparent. You cannot 100% guarantee the function result if it will throw an exception. 
        
        Sealed classes offer a type safe alternative to handling error scenarios. 
    """.trimIndent())
}