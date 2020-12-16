package by.matusevich.controller;

import by.matusevich.ApplicationConfiguration;
import by.matusevich.pojo.Wallet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.PathVariable;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@WebAppConfiguration
public class TransactionListControllerTest extends ControllerTest {

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void transactionList() throws Exception {
        assertEquals("transactions",
                mockMvc.perform(get("/wallets/3/transactions.html"))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getModelAndView()
                        .getViewName()
        );

    }
}