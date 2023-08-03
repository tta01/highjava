package kr.or.ddit.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 클래스에서 선언된 메서드의 메타정보 가져오기
 */
public class T03MethodMetadataTest {
	public static void main(String[] args) throws ClassNotFoundException {

		// Class 오브젝트 생성하기
		Class<?> klass = Class.forName("kr.or.ddit.reflection.SampleVO");

		// 클래스에 선언된 모든 메서드의 메타데이터 정보 가져오기 - Declared
		Method[] merhodArr = klass.getDeclaredMethods();

		for (Method m : merhodArr) {
			System.out.println("메서드 명 : " + m.getName());
			System.out.println("메서드 리턴타입 : " + m.getReturnType());

			// 해당 메서드의 접근제어자 정보 가져오기
			int modFlag = m.getModifiers();
			System.out.println("메서드 접근제어자 : " + Modifier.toString(modFlag));

			// 해당 메서드의 파라미터 타입 가져오기
			Class<?>[] paramArr = m.getParameterTypes();
			System.out.println("메서드 파라미터 타입 : ");
			for (Class<?> clazz : paramArr) {
				System.out.println(clazz.getName() + " | ");
			}
			System.out.println();

			// 해당 메서드에서 던지는 예외타입 가져오기
			Class<?>[] exTypeArr = m.getExceptionTypes();
			System.out.println("메서드에서 던지는 예외타입 목록 : ");
			for (Class<?> clazz : exTypeArr) {
				System.out.println(clazz.getName() + " | ");
			}
			System.out.println();

			// 해당 메서드에 존재하는 애너테이션 타입정보 가져오기
			Annotation[] annos = m.getDeclaredAnnotations();
			System.out.println("애너테이션 타입 정보");
			for (Annotation anno : annos) {
				System.out.println(anno.annotationType().getName() + " | ");
			}
			System.out.println();
			System.out.println("----------------------------------------");
		}
	}
}
