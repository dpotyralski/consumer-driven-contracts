package pl.dpotyralski.producer;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;

public class BaseTest {

    @Before
    public void setUp() throws Exception {
        RestAssuredMockMvc.standaloneSetup(new RiskController());
    }

    public void isProperAmount(Integer amount) {
        assert amount > 15;
    }

    public void isProperAmount() {
        assert 1 == 1;
    }
}
