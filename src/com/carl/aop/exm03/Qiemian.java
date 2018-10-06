package com.carl.aop.exm03;

import org.aopalliance.aop.Advice;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 各种Advice,
 *  BeforeAdvice--->MethodBeforeAdvice
 *  2. around Advice的实现需要实现MethodInterceptor接口，该
 *  接口定义了一个接收MethodInvacation类型的参数的invoke方法。
 *  通过MethodInvocation对象可以获取到目标方法、方法参数等信息，
 *  然后还可以通过调用其proceed方法来调用对应的目标方法，所以我们
 *  可以根据需要来判断是否需要调用目标方法。invoke方法的返回值将作
 *  为目标方法的调用者接收到的返回值，所以我们也可以在invoke方法中
 *  根据需要判断需要给目标方法调用者返回什么样的结果。
 *
 *  3. AfterReturning Advice将在目标方法正常返回时触发，
 *  对应的是AfterReturningAdvice接口，其定义如下，第一
 *  个参数是目标方法的返回值。
 *
 *  4.afterThrowing Advice对应的Advice接口子类是ThrowsAdvice，
 *  该接口也是一个空接口，也是用于标记作用的，但是不同于BeforeAdvice
 *  定义了可供用户实现的包含方法定义的子接口MethodBeforeAdvice，
 *  ThrowingAdvice没有这样的子接口。这是因为用户可能需要同时对多种
 *  异常进行处理，如果把接口方法定义好了，那用户只能在方法体中判断当
 *  前捕获的异常类型了。没有方法定义时用户就可以在实现类中定义很多的异
 *  常处理方法了，但是这些方法也不是随便定义的，它们必须满足以下形式。
 *  其中的方法名必须为afterThrowing，方法参数只有最后一个
 *  subclassOfThrowable是必须的。
 *
 *  5. 对于After Advice类型的Advice没有特定的接口供我们实现，
 *  如果需要自己实现一个Advice可以达到after Advice那样的效果，
 *  即无论切入点方法是否抛出异常都执行某些逻辑时，可以使用
 *  MethodInterceptor代替，在方法实现中使用try…finally形式即可。
 *
 * Created by user on 2018/9/15.
 */
public class Qiemian implements MethodBeforeAdvice {


    @Override
    public void before(Method method, Object[] objects, @Nullable Object o) throws Throwable {
        System.out.println("洗手");
    }
}
