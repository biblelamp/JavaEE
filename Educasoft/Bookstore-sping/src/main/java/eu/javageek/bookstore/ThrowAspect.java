package eu.javageek.bookstore;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ThrowAspect {

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	@AfterThrowing(pointcut = "execution(* eu.javageek.bookstore.controller.DataController.*(..))", throwing = "ex")
	public void throwIntercept(JoinPoint jp, Throwable ex) {

		StackTraceElement[] full = ex.getStackTrace();
		StackTraceElement[] brief = new StackTraceElement[3];
		for (int i = 0; i < brief.length - 1; i++) {
			brief[i] = full[i];
		}

		brief[brief.length - 1] = new StackTraceElement("... " + full.length + " more", "", "", -1);

		ex.setStackTrace(brief);
	}
}
