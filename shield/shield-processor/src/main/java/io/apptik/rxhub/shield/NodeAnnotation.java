package io.apptik.rxhub.shield;


import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

public class NodeAnnotation {

    final String nodeTag;
    final TypeName returnType;
    final TypeName paramType;

    public NodeAnnotation(String nodeTag, TypeName returnType, TypeName paramType) {
        this.nodeTag = nodeTag;
        this.returnType = returnType;
        this.paramType = paramType;
    }

    public ClassName getRawClassName() {
        if (returnType instanceof ParameterizedTypeName) {
            return ((ParameterizedTypeName) returnType).rawType;
        }
        if(returnType instanceof ClassName) {
            return (ClassName) returnType;
        } else {
            //we got typename instead of a classname
            return null;
        }
    }

    public boolean isInputNode() {
        return returnType.equals(TypeName.VOID);
    }

    public boolean hasEnclosedClassName() {
        if (returnType instanceof ParameterizedTypeName) {
            return ((ParameterizedTypeName) returnType).typeArguments.get(0) instanceof ClassName;
        }
        return false;
    }

    public ClassName getEnclosedClassName() {
        if (returnType instanceof ParameterizedTypeName) {
            return (ClassName) ((ParameterizedTypeName) returnType).typeArguments.get(0);
        }
        return null;
    }
}
