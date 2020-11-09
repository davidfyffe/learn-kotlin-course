package app.content.eeDays

import Markdown
import react.RBuilder
import app.annotatedCode
import app.divider
import markdown

val functionalProgramming: RBuilder.() -> Unit = {

    Markdown {
        attrs.source = """
                ![alt text](lambda.png "Lambda")
                # FP Style
                """.trimIndent().trimMargin()
    }


    annotatedCode(readOnly=true,
            annotation = """
        ##  
        Functional programming is a programming paradigm where programs are constructed by applying and composing functions. 
        """,
            code="""
             fun getFileFromS3(fileName: String): String =
              with(getS3Client()) {      
                getBucketname()
                .createGetObjectRequest(fileName)
                .getObject()
                .convertInputStreamToString()
            }
            """.trimIndent().trimMargin()
    )

    annotatedCode(readOnly=false,
            annotation = """  
        ### OO Style
            """,

            code = """

            class DoTheThing() {
                var num1 : Int = 0
                var num2 : Int = 0
                var result : Int = 0

                fun calculate() {
                    result = num1 + num2
                }
            }

            fun doTheCalculation() {
                val c1 = DoTheThing()
                c1.num1 = 1
                c1.num2 = 2
                c1.calculate()
                println(c1.result)
            }
            doTheCalculation()
                """.trimIndent().trimMargin()
    )
    annotatedCode(readOnly=false,
            annotation = """
        ### Functional Style
            """,
            code = """


                fun calculate(a : Int, b : Int) : Int = a + b
             calculate(1,2).run(::println)   
        """.trimIndent().trimMargin()
    )

    divider()

    markdown("""So really whats the difference?""")
//    Markdown {
//        attrs.source = """***""".trimIndent().trimMargin()
//    }

    markdown("""
         # Comments on the difference of the styles above. 
        ### What is a pure function?
        A Pure Function, is a function that depends _only_ on its supplied parameters and its internal algorithm to produce its output. 
        It does not interact with the 'outside world' and does not modify any state of the sytem outside of itself. 
        
        Pure functions by definition, will not interact with any IO. So no databases, no files, no network interaction. 
        
        ### Immutability
        Immutability is another bedrock of FP. 
        Functional code is characterised by one thing; the absence of side effects. 
        If variables are immutable then they can't change after instantiation and variables cannot be reassigned. 
        
            """.trimIndent())


    annotatedCode(readOnly=true,
            annotation = """
        #### Pure functions are easy to reason about.
            """,
            code = """
            fun calculate()
        """.trimIndent().trimMargin()
    )
    annotatedCode(readOnly=true,
            annotation = """
        #### Compared with
            """,
            code = """
            fun calculate(a:Int, b:Int) : Int
        """.trimIndent().trimMargin()
    )
    annotatedCode(readOnly=true,
            annotation = """
        #### What about

        Where might the salary information come from?
        Could there be some IO?
        What state changes?
            """,
            code = """
            fun addSalarys()
        """.trimIndent().trimMargin()
    )
    annotatedCode(readOnly=true,
            annotation = """
        #### What about a pure version
            """,
            code = """
            fun addSalarys(people : List<Employee>) : Double
        """.trimIndent().trimMargin()
    )

    annotatedCode(readOnly=true,
            annotation = """
        # Other advantages of pure functions and immutablility.
        #### Testing. 
        This one is obvious enough.
        
        take a test like this:
                """,
            code = """
            @Test
            fun someTest() {
                setupSomeClass(C1)
                setupTheOtherClass(C2(getAllTheTestingState()))
                doSomeHiddenThingMyCodeRelaysOn(C1, C2)
                val result = nowFinallyTestMyMethod()
                assertEquals("abc", result)
            }
            """.trimIndent()
    )

    annotatedCode(readOnly=true,
            annotation = """
                in Functional Programming, you _can't_ do that!
                """,
            code = """
                @Test
                fun someTest() {
                    assertEquals("abc", nowFinallyTestMyMethod(x, y, z))
                }
            """.trimIndent()
    )


    annotatedCode(readOnly=true,
            annotation = """
        #### Debug
        Easier to debug because functions are clearer, and the purpose well defined.
        There will be no mutable variables, and no hidden state. 
        And to replicate a bug, all you need to know is the inputs to the function that caused it. Nothing else about the state of the system matters. 
        
        #### Easier to combine
                """,
            code = """
            val x = doThis(a).thenThis(b)
                             .andThenThis(c)
                             .doThisToo(d)
                             .andFinallyThis(e)
            """.trimIndent()
    )

    markdown("""
        #### Thread Safety.
        
        Because there are no mutable variables, you can't possibly have a race condition. 
        
        #### Which leads to easy parallelization
        An FP program is ready for concurrency because you don't need to worry about deadlocks or race conditions. 
        No piece of data is modified twice by the same thread, let alone two different threads.
        You can't have concurrent update problems if you don't update. :)
         
        #### Compiler Optimization & Referential Transparency
            
    """.trimIndent())
    annotatedCode(readOnly=true,
            annotation = """
        What do I mean by Referential Transparency?
        
        Checkout the following code.
         
        Because h and e are immutable, you can replace h + e with z anywhere you like, and the program functions exactly the same. 
        Infact in this example you can replace (h+e).length with 13, anywhere. And the program is unchanged. Think what that means to a developer, and what optimizations a compiler can do with it. 
            """,
            code = """
            val h = "Hello " 
            val e = "EE Days"
            val z = h + e
            println(z)
            
            val length1 = z.length
            // is the same as
            val length2 = 6 + 7
            // which is the same as
            val length3 = 13
        """.trimIndent().trimMargin()
    )



}