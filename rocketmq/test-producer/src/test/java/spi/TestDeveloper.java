package spi;

import spi.model.Developer;

public class TestDeveloper  implements Developer {
    @Override
    public void sayHi() {
        System.out.println("Test Hi!!!");
    }
}
