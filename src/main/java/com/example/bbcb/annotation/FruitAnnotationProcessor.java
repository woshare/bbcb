package com.example.bbcb.annotation;







import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.google.auto.service.AutoService;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

@AutoService(Processor.class)
@SupportedAnnotationTypes("com.example.bbcb.annotation.Fruit")
@Slf4j
public class FruitAnnotationProcessor extends AbstractProcessor {
    private Types typeUtils;
    private Elements elementsUtils;
    private Filer filer;
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment env){
        super.init(env);
        elementsUtils=env.getElementUtils();
        filer=env.getFiler();
        typeUtils=env.getTypeUtils();
        messager=env.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotation, RoundEnvironment env){
//        Map<String, AnnotatedClass> classMap=new HashMap<>();
//        Set<? extends Element> elementSet=env.getElementsAnnotatedWith(fruit.class);

//        for(Element e:elementSet){
//            if(e.getKind()!= ElementKind.FIELD||e.getKind()!=ElementKind.METHOD||e.getKind()!=ElementKind.CLASS){
//                log.error("e:{}",e);
//                return true;
//            }
//
//            ExecutableElement element=(ExecutableElement)e;
//            AnnotatedMethod annotatedMethod = new AnnotatedMethod(element);
//
//            String className=annotatedMethod.getSimpleClassName();
//            AnnotatedClass annotatedClass=classMap.get(className);
//            if(annotatedClass==null){
//                PackageElement pkg=elementsUtils.getPackageElement(element);
//                annotatedClass=new AnnotatedClass(pkg.getQualifiedName().toString(),element.getAnnotation(fruit.class).value());
//                annotatedClass.addMethod(annotatedMethod);
//                classMap.put(className,annotatedClass);
//            }else{
//                annotatedClass.addMethod(annotatedMethod);
//            }
//        }
//        for(AnnotatedClass annotatedClass:classMap.values()){
//            annotatedClass.generateCode(elementsUtils,filer);
//        }
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes(){
        Set<String> types=new TreeSet<>();
        types.add(Fruit.class.getCanonicalName());
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion(){
        return SourceVersion.latestSupported();
    }

}
