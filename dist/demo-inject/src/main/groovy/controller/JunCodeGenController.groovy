package controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("junCodeGen")
public class JunCodeGenController {

    @GetMapping("genHookGroovyCode")
    def genHookGroovyCode(@RequestParam("clsName") String clsName){
        Class.forName("com.jun.proj_agent.codeEnhance.utils.CodeGenUtil").getDeclaredMethod("buildHookGroovyCodeUseGroovy",String.class).invoke(null,clsName)
    }
}
