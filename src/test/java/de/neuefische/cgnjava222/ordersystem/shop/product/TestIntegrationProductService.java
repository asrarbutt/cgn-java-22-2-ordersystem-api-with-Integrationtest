package de.neuefische.cgnjava222.ordersystem.shop.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class TestIntegrationProductService {

    @Autowired
    private MockMvc mockMvc;

   @Test //import von Junit package
    void addProductApiTest() throws Exception {

       mockMvc.perform(get("/api/products/1"))
               .andExpect(status().isOk())
               .andExpect(content().json("""
{
    "id": 1,
    "name": "Apfel"
}
"""));
    }


    @Test
    void listProductsApiTest() throws Exception {
       mockMvc.perform(get("/api/products"))
               .andExpect(status().is(200))
               .andExpect(content().json("""
[
    {
        "id": 4,
        "name": "Mandarine"
    },
    {
        "id": 1,
        "name": "Apfel"
    },
    {
        "id": 2,
        "name": "Banane"
    },
    {
        "id": 3,
        "name": "Zitrone"
    }
]

"""));
    }

}
