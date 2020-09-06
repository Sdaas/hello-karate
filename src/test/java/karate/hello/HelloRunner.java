package karate.hello;

import com.intuit.karate.junit5.Karate;

class HelloRunner {

    @Karate.Test
    Karate testAll() {
        return Karate.run().relativeTo(getClass());
    }

}