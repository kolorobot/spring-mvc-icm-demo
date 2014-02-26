package com.github.kolorobot.icm.signup;

import com.github.kolorobot.icm.config.WebAppConfigurationAware;
import org.junit.Test;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;


public class SignupControllerTest extends WebAppConfigurationAware {
    @Test
    public void displaysSignupForm() throws Exception {
        mockMvc.perform(get("/signup"))
                .andExpect(model().attributeExists("signupForm"))
                .andExpect(content().string(
                        allOf(
                                containsString("<title>ICM</title>")
                        ))
                );
    }
}