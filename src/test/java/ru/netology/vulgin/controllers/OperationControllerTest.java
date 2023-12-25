package ru.netology.vulgin.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.netology.vulgin.domain.operations.Currency;
import ru.netology.vulgin.domain.operations.Operation;
import ru.netology.vulgin.domain.operations.OperationCreditType;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OperationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    OperationsController operationsController;

    @Test
    @Order(1)
    public void testCheckOperationsByCustomerId() throws Exception {
        int customerId = 1;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/operations/{customerId}", customerId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operations").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operations[0].customerId").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operations[0].sum").value(2500))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operations[0].currency").value(Currency.RUB.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operations[0].merchant").value("OZON"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operations[0].operationCreditType").value(OperationCreditType.DEBIT.name()));
    }

    @Test
    @Order(2)
    public void testAddOperation() throws Exception {
        Operation operation = new Operation(OperationCreditType.CREDIT, 100, Currency.USD, "Amazon", 1);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/operations/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(operation))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sum").value(100.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency").value(Currency.USD.name()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.merchant").value("Amazon"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operationCreditType").value(OperationCreditType.CREDIT.name()));
    }

    @Test
    @Order(3)
    public void testDeleteOperation() throws Exception {
        int operationId = 0;
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/operations/delete/{operationId}", operationId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private String asJsonString(Object obj) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}