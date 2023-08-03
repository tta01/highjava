package kr.or.ddit.basic;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationTest {
	public static void main(String[] args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {

		System.out.println("static 변수값 : " + PrintAnnotation.id);

		Method[] methodArr = Service.class.getDeclaredMethods();

		for (Method m : methodArr) {
			Annotation[] annos = m.getDeclaredAnnotations();

			for (Annotation anno : annos) {
				if (anno.annotationType().getSimpleName().equals("PrintAnnotation")) {
					PrintAnnotation printAnno = (PrintAnnotation) anno;
					// count값 만큼
					for (int i = 0; i < printAnno.count(); i++) {
						System.out.print(printAnno.value());
					}
				}
			}
			System.out.println(); // 줄 바꿈 처리

//			m.invoke(new Service());

			Class<?> clazz = Service.class;

			Service service = (Service) clazz.newInstance();

			m.invoke(service);
		}
	}
}
