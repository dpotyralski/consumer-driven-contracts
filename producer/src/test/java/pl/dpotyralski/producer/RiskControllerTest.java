package pl.dpotyralski.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class RiskControllerTest {

    private JacksonTester<RiskCheckRequest> requestJson;
    private JacksonTester<RiskResponse> responseJson;

    @Autowired
    MockMvc mockMvc;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void should_create_tweet() throws Exception {
        String content = requestJson.write(new RiskCheckRequest(4, "IBM")).getJson();

        mockMvc.perform(post("/risks")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(content().json(responseJson.write(new RiskResponse(Status.OK)).getJson()))
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_validation_error() throws Exception {
        String content = requestJson.write(new RiskCheckRequest(15, "Microsoft")).getJson();

        mockMvc.perform(post("/risks")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(responseJson.write(new RiskResponse(Status.NOT_OK)).getJson()))
                .andExpect(status().isOk());
    }


}