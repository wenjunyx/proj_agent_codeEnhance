package org.springframework.context

import com.jun.proj_agent.codeEnhance.annotaions.HookClass
import com.jun.proj_agent.codeEnhance.annotaions.HookFunc
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider
import org.springframework.context.annotation.ScannedGenericBeanDefinition
import org.springframework.core.io.FileSystemResource
import org.springframework.core.type.classreading.MetadataReader

@HookClass(ClassPathScanningCandidateComponentProvider.class)
class ClassPathScanningCandidateComponentProvider_inject {

    @HookFunc(name = "scanCandidateComponents",after = " @injectCode@  ",callArgs="\$0,\$1,\$_",paramTypes = [String.class])
    def scanCandidateComponents_after(ClassPathScanningCandidateComponentProvider _this,String basePackage,Set<BeanDefinition> candidates){
        if("com.demo".equals(basePackage) && candidates.size()>0){
            candidates.add(regSpringClass("controller.JunCodeGenController",true,_this))
        }
    }

    def regSpringClass(String clsName,boolean isFirst,ClassPathScanningCandidateComponentProvider _this){

        def (File clsDir,File clsFile,File clsPath)=getGbc(clsName)

        if(isFirst){
            URLClassLoader classLoader = (URLClassLoader) Thread.currentThread().getContextClassLoader();
            classLoader.addURL(clsDir.toURI().toURL())
        }

        def resource=new FileSystemResource(clsPath)

        MetadataReader metadataReader = _this.getMetadataReaderFactory().getMetadataReader(resource);

        ScannedGenericBeanDefinition sbd = new ScannedGenericBeanDefinition(metadataReader);
        sbd.setResource(resource);
        sbd.setSource(resource);

        return sbd
    }


    def getGbc(String clsName){

        File injectGSDir=System.getProperties().get("_injectGSDir")

        def clsa=new Class[2]
        clsa[0]=String.class
        clsa[1]=String.class

        def ret=Class.forName("com.jun.proj_agent.codeEnhance.utils_groovy.GroovyUtil").getDeclaredMethod("getGroovyCompileClass",clsa).invoke(null,injectGSDir.getCanonicalPath(),clsName)
        return ret
    }
}
