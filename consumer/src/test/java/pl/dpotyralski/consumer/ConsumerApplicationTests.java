package pl.dpotyralski.consumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureStubRunner(workOffline = true, ids = "com.example:producer:8081")
public class ConsumerApplicationTests extends AbstractTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BidingController bidingController;

    @Test
    public void should_create_bid_successfully() throws Exception {
        mockMvc.perform(post("/bids")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.write(new CreateBidRequest(10, "IBM", 1L)).getJson()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(json.write(new BidResponse(BidResponseStatus.BID_CREATED)).getJson()));
    }

    @Test
    public void should_reject_bid_because_of_risk() throws Exception {
        mockMvc.perform(post("/bids")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.write(new CreateBidRequest(15, "Microsoft", 1L)).getJson()))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(json.write(new BidResponse(BidResponseStatus.BID_REJECTED)).getJson()));
    }

}
