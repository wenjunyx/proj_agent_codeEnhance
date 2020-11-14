package com

import com.jun.proj_agent.codeEnhance.annotaions.HookClass
import com.jun.proj_agent.codeEnhance.annotaions.HookFunc

@HookClass(DemoApplication.class)
class DemoApplication_inject {
    @HookFunc(name = "main",before = " @injectCode@ ",callArgs = "\$1",paramTypes = [java.lang.String[].class ])
    def main_before(String[] args){
        putVar()
    }

    static putVar(){
        def _injectProjDir=new File("G:\\code\\demo-inject")

        System.getProperties().put("_injectProjDir",_injectProjDir);

        def injectGSDir=new File(_injectProjDir,"src\\main\\groovy")

        System.getProperties().put("_injectGSDir",injectGSDir);
    }

}
