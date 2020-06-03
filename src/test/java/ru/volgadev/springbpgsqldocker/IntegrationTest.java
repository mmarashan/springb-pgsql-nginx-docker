package ru.volgadev.springbpgsqldocker;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = App.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnDefaultMessage() throws Exception {
        mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello world")));
    }

    @Test
    void shouldSaveReading() throws Exception {
        long sensorId = 0;
        long time = System.currentTimeMillis();
        mockMvc.perform(post("/api/save").contentType("application/json")
                .content("[{\"objectId\": 0, \"sensorId\": "+sensorId+", \"time\": "+time+", \"value\": -40.0}]"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void shouldSaveReadingAndReadInHistory() throws Exception {
        long sensorId = 0;
        long time = System.currentTimeMillis();
        mockMvc.perform(post("/api/save").contentType("application/json")
                .content("[{\"objectId\": 0, \"sensorId\": "+sensorId+", \"time\": "+time+", \"value\": -40.0}]"))
                .andDo(print()).andExpect(status().isOk());

        mockMvc.perform(get("/api/history")
                .param("id", String.valueOf(sensorId))
                .param("from", String.valueOf(time-1))
                .param("to", String.valueOf(time+1)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(String.valueOf(sensorId))))
                .andExpect(content().string(containsString(String.valueOf(time))));
    }
}