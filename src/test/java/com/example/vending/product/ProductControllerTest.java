package com.example.vending.product;

import com.example.vending.controller.ProductController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testHello() throws Exception {
        String hello = "Hello World";

        mvc.perform(get("/hello")) // '/hello' url 호출
                .andExpect((status().isOk())) // HTTP 상태가 200인지 테스트
                .andExpect(content().string(hello)); //response body에 해당 string이 있는지 테스트
               // .andDo(print());
    }

}
