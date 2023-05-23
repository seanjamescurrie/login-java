package coe.bananagrams.controller;

import coe.bananagrams.dto.user.CreateUserDto;
import coe.bananagrams.dto.user.UserDto;
import coe.bananagrams.exception.ExceptionHandler;
import coe.bananagrams.mapper.Mapper;
import coe.bananagrams.service.UserService;
import coe.bananagrams.util.ResourceUtility;
import coe.bananagrams.viewModel.user.CreateUserViewModel;
import coe.bananagrams.viewModel.user.UserViewModel;
import com.flextrade.jfixture.FixtureAnnotations;
import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.annotations.Fixture;
import com.flextrade.jfixture.utility.SpecimenType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    private static final String CREATE_USER_VALID_JSON = ResourceUtility.generateStringFromResource("requestJson/CreateUser_Valid.json");

    private static final String UPDATE_USER_VALID_JSON = ResourceUtility.generateStringFromResource("requestJson/UpdateUser_Valid.json");

    @Mock
    private UserService userServiceMock;
    @Mock
    private Mapper mapperMock;

    @Fixture
    private UserDto userDtoFixture;
    @Fixture
    private List<UserDto> userDtoListFixture;
    @Fixture
    private CreateUserDto createUserDtoFixture;

    @Fixture
    private UserViewModel userViewModelFixture;
    @Fixture
    private List<UserViewModel> userViewModelListFixture;
    @Fixture
    private CreateUserViewModel createUserViewModelFixture;

    @BeforeEach
    public void setUp() {
        final JFixture jFixture = new JFixture();
        jFixture.customise().circularDependencyBehaviour().omitSpecimen();
        jFixture.customise().sameInstance(new SpecimenType<SortedSet<UserViewModel>>() {
        }, new TreeSet<>());
        FixtureAnnotations.initFixtures(this, jFixture);
        mockMvc = MockMvcBuilders.standaloneSetup(new UsersController(userServiceMock, mapperMock))
                .setControllerAdvice(new ExceptionHandler())
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    public void findAllUsers_WhenDataExists_returnsOk() throws Exception {
        when(mapperMock.map(userDtoListFixture, UserViewModel.class)).thenReturn(userViewModelListFixture);
        when(userServiceMock.findAll()).thenReturn(userDtoListFixture);
        mockMvc.perform(get("/users")).andExpect(status().isOk());
    }

    @Test
    public void findUserById_WhenUSerExists_ReturnsOk() throws Exception {
        when(mapperMock.map(userDtoFixture, UserViewModel.class)).thenReturn(userViewModelFixture);
        when(userServiceMock.findById(anyInt())).thenReturn(userDtoFixture);
        mockMvc.perform(get("/users/1")).andExpect(status().isOk());
    }

    @Test
    public void create_WhenValidDataPassed_ReturnsCreated() throws Exception {
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(CREATE_USER_VALID_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void update_WhenValidDataPassed_ReturnsCreated() throws Exception {
        mockMvc.perform(put("/users/1").contentType(MediaType.APPLICATION_JSON).content(UPDATE_USER_VALID_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void delete_WhenUserExists_ReturnsOk() throws Exception {
        mockMvc.perform(delete("/users/1")).andExpect(status().isOk());
    }
}
