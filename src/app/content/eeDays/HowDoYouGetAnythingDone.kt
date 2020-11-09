package app.content.eeDays

import Markdown
import react.RBuilder
import app.annotatedCode
import app.divider
import markdown

val howDoYouGetAnythingDone: RBuilder.() -> Unit = {

    markdown("""
        # This is grand. But seriously?

        - The golden rule of no IO .. Just break it.
        
        - Keep IO code seperate to the main code body.
        
        - Name the functions well. 
        
        - Consider coroutine or seperate threads.
        
        - To change an objects state after instantiation, you copy it.
        
        - It's the real world, we tend to tread a line somewhere between FP and OO. 
         
        """.trimIndent())

}