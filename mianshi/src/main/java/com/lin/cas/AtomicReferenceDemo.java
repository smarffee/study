package com.lin.cas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {

    public static void main(String[] args) {

        User z3 = new User("z3", 22);
        User li4 = new User("li4", 25);

        AtomicReference<User> atomicReference = new AtomicReference<>();

        atomicReference.set(z3);

        System.out.println(atomicReference.compareAndSet(z3, li4) + "  current user is " + atomicReference.get());
        System.out.println(atomicReference.compareAndSet(z3, li4) + "  current user is " + atomicReference.get());

    }

}

@Setter
@Getter
@ToString
@AllArgsConstructor
class User {
    String userName;
    int age;
}
