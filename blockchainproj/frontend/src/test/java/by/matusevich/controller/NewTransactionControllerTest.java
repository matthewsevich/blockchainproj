package by.matusevich.controller;

import by.matusevich.ApplicationConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@WebAppConfiguration
public class NewTransactionControllerTest extends ControllerTest {

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void showNewTransaction() throws Exception {
        assertEquals("new-transaction",
                mockMvc.perform(get("/wallets/3/new-transaction.html"))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getModelAndView()
                        .getViewName()
        );
    }
}