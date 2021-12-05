package bg.kmitev.ChatServer;

import bg.kmitev.ChatServer.model.TextMessageData;
import bg.kmitev.ChatServer.repository.MessageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ChatServerApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MessageRepository repository;

    @ParameterizedTest
    @ValueSource(strings = {"a", "Valid payload", "1234523424", "Valid 1234", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"})
    void testValidTextPayload(String payload) throws Exception {
        TextMessageData textMessageData = new TextMessageData();
        textMessageData.setPayload(payload);
        this.mockMvc.perform(post("/messages/send_text")
                        .content(objectMapper.writeValueAsString(textMessageData))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(content().string(is("")));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"})
    void testInvalidTextPayload(String payload) throws Exception {
        TextMessageData textMessageData = new TextMessageData();
        textMessageData.setPayload(payload);
        this.mockMvc.perform(post("/messages/send_text")
                        .content(objectMapper.writeValueAsString(textMessageData))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isPreconditionFailed())
                .andExpect(content().string(is("")));
    }

    @ParameterizedTest
    @ValueSource(strings = {"ab", "ababrhdvbd"})
    void testValidEmotionPayload(String payload) throws Exception {
        TextMessageData textMessageData = new TextMessageData();
        textMessageData.setPayload(payload);
        this.mockMvc.perform(post("/messages/send_emotion")
                        .content(objectMapper.writeValueAsString(textMessageData))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(content().string(is("")));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "a", "12", "ab2bbd", "ababababaaa"})
    void testInvalidEmotionPayload(String payload) throws Exception {
        TextMessageData textMessageData = new TextMessageData();
        textMessageData.setPayload(payload);
        this.mockMvc.perform(post("/messages/send_emotion")
                        .content(objectMapper.writeValueAsString(textMessageData))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isPreconditionFailed())
                .andExpect(content().string(is("")));
    }

}
