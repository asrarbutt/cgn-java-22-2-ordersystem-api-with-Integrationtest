package de.neuefische.cgnjava222.ordersystem.shop.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
public class TestIntegrationOrderService {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addOrderApiTest() throws Exception {
        //Given
        mockMvc
                //when
                .perform(post("/api/orders/1").contentType(MediaType.APPLICATION_JSON).content("""
                        [1,2]""")).andExpect(status().isOk()).andExpect(content().string(""));


    }



}
