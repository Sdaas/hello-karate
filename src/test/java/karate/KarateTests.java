package karate;

import com.daasworld.hellokarate.HelloKarateApplication;
import com.intuit.karate.junit5.Karate;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {HelloKarateApplication.class})
class KarateTests {

    @Karate.Test
    Karate dummyTest() {
        return Karate.run("classpath:karate/dummy.feature");
    }

    @Karate.Test
    Karate hello1Test() {
        return Karate.run("classpath:karate/hello/hello1.feature");
    }

    @Karate.Test
    Karate hello2Test() {
        return Karate.run("classpath:karate/hello/hello2.feature");
    }

    @Karate.Test
    Karate createPersonTest() {
        return Karate.run("classpath:karate/person/create.feature");
    }

    @Karate.Test
    Karate getPersonTest() {
        return Karate.run("classpath:karate/person/get.feature");
    }

}