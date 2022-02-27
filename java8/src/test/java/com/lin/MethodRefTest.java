package com.lin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.*;

/**
 * 一、方法引用：若 Lambda 体中的功能，已经有方法提供了实现，可以使用方法引用
 * 			  （可以将方法引用理解为 Lambda 表达式的另外一种表现形式）
 *
 * 1. 对象的引用::实例方法名
 *
 * 2. 类名::静态方法名
 *
 * 3. 类名::实例方法名
 *
 * 注意：
 * 	 ①方法引用所引用的方法的参数列表与返回值类型，需要与函数式接口中抽象方法的参数列表和返回值类型保持一致！
 * 	 ②若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： ClassName::MethodName
 *
 * 二、构造器引用 :
 * 构造器的参数列表，需要与函数式接口中参数列表保持一致！
 *
 * 1. 类名::new
 *
 * 三、数组引用
 *
 * 	类型[]::new;
 */
public class MethodRefTest {

    //数组引用
    @Test
    public void test6() {
        Function<Integer, String[]> function1 = (x) -> new String[x];
        String[] strArr1 = function1.apply(7);
        System.out.println(strArr1.length);

        Function<Integer, String[]> function2 = String[]::new;
        String[] strArr2 = function2.apply(8);
        System.out.println(strArr2.length);
    }

    //构造器引用
    @Test
    public void test5() {
        Supplier<Employee> employeeSupplier1 = () -> new Employee();
        Employee employee1 = employeeSupplier1.get();
        System.out.println(employee1);

        Supplier<Employee> employeeSupplier2 = Employee::new;
        Employee employee2 = employeeSupplier2.get();
        System.out.println(employee2);

        System.out.println(employee1 == employee2);

        Function<String, Employee> function1 = Employee::new;
        Employee employee3 = function1.apply("lisi");
        System.out.println(employee3);

        BiFunction<String, Integer, Employee> function2 = Employee::new;
        Employee employee4 = function2.apply("wangwu", 20);
        System.out.println(employee4);
    }

    //类名::实例方法名
    @Test
    public void test4() {
        BiPredicate<String, String> biPredicate1 = (x, y) -> x.equals(y);
        System.out.println(biPredicate1.test("a", "a"));

        BiPredicate<String, String> biPredicate2 = String::equals;
        System.out.println(biPredicate2.test("a", "a"));
    }

    //类::静态方法名
    @Test
    public void test3() {
        Comparator<Integer> comparator1 = (x, y) -> Integer.compare(x, y);
        System.out.println(comparator1.compare(1, 2));

        Comparator<Integer> comparator2 = Integer::compare;
        System.out.println(comparator2.compare(1, 2));
    }

    //对象::实例方法名
    @Test
    public void test1() {
        Consumer<String> consumer = (x) -> System.out.println(x);
        consumer.accept("sdf");

        Consumer<String> consumer1 = System.out::println;
        consumer1.accept("sdf");
    }

    @Test
    public void test2() {
        Employee employee = new Employee();
        Supplier<String> supplier1 = () -> employee.getName();
        System.out.println(supplier1.get());

        Supplier<Integer> supplier2 = employee::getAge;
        System.out.println(supplier2.get());
    }

}

@Setter
@Getter
@ToString
class Employee {
    private String name = "zhangsan";
    private int age = 10;

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
