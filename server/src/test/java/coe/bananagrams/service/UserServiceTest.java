package coe.bananagrams.service;

import coe.bananagrams.dto.game.GameDto;
import coe.bananagrams.dto.user.CreateUserDto;
import coe.bananagrams.dto.user.UpdateUserDto;
import coe.bananagrams.dto.user.UserDto;
import coe.bananagrams.entity.User;
import coe.bananagrams.mapper.Mapper;
import coe.bananagrams.repository.UserRepository;
import com.flextrade.jfixture.FixtureAnnotations;
import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.annotations.Fixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private Mapper mapperMock;

    @Fixture
    private List<User> userListFixture;
    @Fixture
    private List<UserDto> userDtoListFixture;

    @Fixture
    private User userFixture;
    @Fixture
    private UserDto userDtoFixture;
    @Fixture
    private CreateUserDto createUserDtoFixture;
    @Fixture
    private UpdateUserDto updateUserDtoFixture;

    @InjectMocks
    private UserService classUnderTest;

    @BeforeEach
    public void setup() {
        final JFixture jFixture = new JFixture();
        jFixture.customise().circularDependencyBehaviour().omitSpecimen();
        FixtureAnnotations.initFixtures(this, jFixture);
    }

    @Test
    public void findAllUsers_WhenDataExists_ReturnsList() {
        // Arrange
        when(userRepositoryMock.findAll()).thenReturn(userListFixture);
        when(mapperMock.map(userListFixture, UserDto.class)).thenReturn(userDtoListFixture);

        // Act
        List<UserDto> userDtoList = classUnderTest.findAll();

        // Assert
        assertThat(userDtoList).usingRecursiveComparison().isEqualTo(userDtoListFixture);
        verify(userRepositoryMock, times(1)).findAll();

    }

    @Test
    public void findById_WhenDataExists_ReturnsGame() {
        // Arrange
        when(userRepositoryMock.findById(1)).thenReturn(userFixture);
        when(mapperMock.map(userFixture, UserDto.class)).thenReturn(userDtoFixture);

        // Act
        UserDto userDto =  classUnderTest.findById(1);

        // Assert
        assertThat(userDto).usingRecursiveComparison().isEqualTo(userDtoFixture);
        verify(userRepositoryMock, times(1)).findById(1);
    }

    @Test
    public void create_WhenDataPassed_CreatesUser() {
        // Arrange
        when(userRepositoryMock.save(userFixture)).thenReturn(userFixture);
        when(mapperMock.map(createUserDtoFixture, User.class)).thenReturn(userFixture);

        // Act
        classUnderTest.create(createUserDtoFixture);

        // Assert
        verify(userRepositoryMock, times(1)).save(userFixture);
    }

    @Test
    public void update_WhenDataPassed_UpdatesUser() {
        // Arrange
        when(userRepositoryMock.save(userFixture)).thenReturn(userFixture);
        // when(mapperMock.map(updateUserDtoFixture, userFixture)).thenReturn(userFixture);

        // Act
        classUnderTest.update(1, updateUserDtoFixture);

        // Assert
        verify(userRepositoryMock, times(1)).save(userFixture);
    }

    @Test
    public void delete_WhenDataExists_DeletesUser() {
        // Arrange/Act
        classUnderTest.delete(anyInt());

        // Assert
        verify(userRepositoryMock, times(1)).deleteById(anyInt());
    }


}
