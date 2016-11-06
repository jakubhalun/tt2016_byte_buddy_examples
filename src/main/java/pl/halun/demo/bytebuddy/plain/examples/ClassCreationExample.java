package pl.halun.demo.bytebuddy.plain.examples;

import java.io.Serializable;
import java.lang.reflect.Modifier;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;

public class ClassCreationExample {

	private Class<? extends Object> createNewClass() throws Exception {
		
		return new ByteBuddy()
			.subclass(Object.class)
			.name("pl.halun.example.BbGeneratedClass")
			.implement(Runnable.class)
			.implement(Serializable.class)
			.serialVersionUid(1L)
			.defineField("name", String.class, Modifier.PRIVATE + Modifier.FINAL)
			
			.defineConstructor(Modifier.PUBLIC).withParameter(String.class)
				.intercept(
						MethodCall.invoke(Object.class.getConstructor())
							.onSuper()
                            .andThen(
                            		FieldAccessor.ofField("name")
                            			.setsArgumentAt(0)
                                    )
				)

			.defineMethod("run", void.class, Modifier.PUBLIC)
				.intercept(MethodDelegation.to(RunInterceptor.class))
			.defineMethod("toString", String.class, Modifier.PUBLIC)
				.intercept(FieldAccessor.ofField("name"))

			.make().load(getClass().getClassLoader()).getLoaded();
	}

	public static void main(String[] args) throws Exception {
		ClassCreationExample example = new ClassCreationExample();
		Runnable runnable = (Runnable)example.createNewClass()
				.getDeclaredConstructor(String.class)
				.newInstance("object of new class");
		new Thread(runnable).start();
	}

}


