package karate.person;

import com.intuit.karate.junit5.Karate;

class PersonRunner {

    @Karate.Test
    Karate testAll() {
        return Karate.run().relativeTo(getClass());
    }
}