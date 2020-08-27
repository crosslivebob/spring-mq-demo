package spi;

import spi.model.Developer;

public class DevDeveloper implements Developer {
    @Override
    public void sayHi() {
        System.out.println("Dev Hi!!!");
    }
}
