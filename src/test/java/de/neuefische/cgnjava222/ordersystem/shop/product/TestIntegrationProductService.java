package de.neuefische.cgnjava222.ordersystem.shop.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;




import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class TestIntegrationProductService {

    @Autowired
    private MockMvc mockMvc;

   @Test
    void addProductApiTest(){

       mockMvc.perform(get("/api/products/1")).andExpect(status().isOk())
    }
}
