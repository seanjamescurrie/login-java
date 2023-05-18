package coe.bananagrams.service;

import coe.bananagrams.dto.game.GameDto;
import coe.bananagrams.entity.Game;
import coe.bananagrams.mapper.Mapper;
import coe.bananagrams.repository.GameRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GameAnagramServiceTest {

    @Mock
    private GameRepository gameRepositoryMock;
    @Mock
    private Mapper mapperMock;

    @Fixture
    private List<Game> gameListFixture;
    @Fixture
    private List<GameDto> gameDtoListFixture;
    @Fixture
    private Game gameFixture;
    @Fixture
    private GameDto gameDtoFixture;

    @InjectMocks
    private GameAnagramService classUnderTest;

    @BeforeEach
    public void setup() {
        final JFixture jFixture = new JFixture();
        jFixture.customise().circularDependencyBehaviour().omitSpecimen();
        FixtureAnnotations.initFixtures(this, jFixture);
    }

    @Test
    public void findAll_Games_Returns_GameList() {

        // Arrange
        when(gameRepositoryMock.findAll()).thenReturn(gameListFixture);
        when(mapperMock.map(gameListFixture, GameDto.class)).thenReturn(gameDtoListFixture);

        // Act
        List<GameDto> gameDtoList =  classUnderTest.findAll();

        // Assert
        assertThat(gameDtoList).usingRecursiveComparison().isEqualTo(gameDtoListFixture);
        verify(gameRepositoryMock, times(1)).findAll();

    }

    @Test
    public void findById_WhenDataExists_ReturnsGame() {
        // Arrange
        when(gameRepositoryMock.findById(1)).thenReturn(gameFixture);
        when(mapperMock.map(gameFixture, GameDto.class)).thenReturn(gameDtoFixture);

        // Act
        GameDto gameDto =  classUnderTest.findById(1);

        // Assert
        assertThat(gameDto).usingRecursiveComparison().isEqualTo(gameDtoFixture);
        verify(gameRepositoryMock, times(1)).findById(1);
    }
}
