package com.capgemini.jstk.springAplication.User;

import com.capgemini.jstk.springAplication.Aplication.SpringAplication;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringAplication.class)
public class UserAPITest {

    private MockMvc mockMvc;

    private View view;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private UserService userService = Mockito.mock(UserService.class);


    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserAPI(userService)).setSingleView(view).build();
    }

    @Test
    public void shouldReturnAllUsers() throws Exception {

        //given
        List<UserDTO> users = new ArrayList<>();

        UserDTO user1 = new UserDTO.UserDTOBuilder()
                .idUser(1L)
                .firstName("Arek")
                .surName("Nowak")
                .eMail("blabla@wp.pl")
                .password("qwerqwer11")
                .lifeMotto("Carpe diem")
                .myGames(new HashSet<>())
                .previousGames(new ArrayList<>())
                .availabilityTime(new ArrayList<>())
                .build();

        users.add(user1);

        //when
        when(userService.getAllUsers()).thenReturn(users);
        ResultActions resultActions = mockMvc.perform(get("/getUsers"));

        // then
        Mockito.verify(userService, times(1)).getAllUsers();
        resultActions.andDo(print())//
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].firstName", is(user1.getFirstName())))
                .andExpect(jsonPath("$[0].surName", is(user1.getSurName())))
                .andExpect(jsonPath("$[0].email", is(user1.getEMail())));
    }

    @Test
    public void shouldReturnUserByID() throws Exception {

        //given
        List<UserDTO> users = new ArrayList<>();

        UserDTO user2 = new UserDTO.UserDTOBuilder()
                .idUser(0L)
                .firstName("Marcin")
                .surName("Kowalski")
                .eMail("kawakawa@wp.pl")
                .password("123456789")
                .lifeMotto(":)")
                .myGames(new HashSet<>())
                .previousGames(new ArrayList<>())
                .availabilityTime(new ArrayList<>())
                .build();

        users.add(user2);

        //when
        when(userService.getUserById(0L)).thenReturn(user2);
        String json = objectMapper.writeValueAsString(user2);

        //then
        mockMvc.perform(get("/getUser/0")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Marcin")))
                .andExpect(jsonPath("$.surName", is("Kowalski")));
    }

    @Test
    public void shouldfindUsersByParameters() throws Exception {

        //given
        List<UserDTO> users = new ArrayList<>();

        UserDTO user = new UserDTO.UserDTOBuilder()
                .idUser(1L)
                .firstName("Arek")
                .surName("Nowak")
                .eMail("blabla@wp.pl")
                .password("qwerqwer11")
                .lifeMotto("Carpe diem")
                .myGames(new HashSet<>())
                .previousGames(new ArrayList<>())
                .availabilityTime(new ArrayList<>())
                .build();

        users.add(user);

        //when
        when(userService.findUserByParameters(user)).thenReturn(users);
        String json = objectMapper.writeValueAsString(user);

        //then
        mockMvc.perform(post("/filterUser")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("Arek")))
                .andExpect(jsonPath("$[0].surName", is("Nowak")));
    }

    @Test
    public void shouldAddUser() throws Exception {

        //given
        UserDTO user = new UserDTO.UserDTOBuilder()
                .idUser(0L)
                .firstName("Arek")
                .surName("Nowak")
                .eMail("blabla@wp.pl")
                .password("qwerqwer11")
                .lifeMotto("Carpe diem")
                .myGames(new HashSet<>())
                .previousGames(new ArrayList<>())
                .availabilityTime(new ArrayList<>())
                .build();


        //when
        when(userService.addUserProfile(user)).thenReturn(user);
        String json = objectMapper.writeValueAsString(user);

        //then
        mockMvc.perform(post("/addUserProfile")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUser", is(0)))
                .andExpect(jsonPath("$.firstName", is("Arek")));
    }
}