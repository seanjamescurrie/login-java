package coe.bananagrams.service;

import coe.bananagrams.dto.NewGameAnagramDto;
import coe.bananagrams.dto.game.CreateGameAnagramDto;
import coe.bananagrams.dto.game.GameDto;
import coe.bananagrams.entity.*;
import coe.bananagrams.mapper.Mapper;
import coe.bananagrams.repository.GameRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GameAnagramService {

    private final GameRepository gameRepository;

    private final Mapper mapper;
    private final EntityManager entityManager;

    public List<GameDto> findAll() {
        List<GameDto> gameDtoList = mapper.map(gameRepository.findAll(), GameDto.class);
        return gameDtoList;
    }

    public GameDto findById(int id) {
        GameDto gameDto = mapper.map(gameRepository.findById(id), GameDto.class);
        return gameDto;
    }

//    public async Task<GameDto> GetDaily(int userId)
//    {
//        var games = await _mapper.ProjectTo<GameDto>(_database
//            .Get<Game>()
//            .Where(new GameByDateSpec(DateTime.UtcNow).And(new GameByTypeSpec(1))))
//            .ToListAsync();
//
//        if (games != null && games.Any())
//        {
//            if (games.Any(x => x.GameUsers.Any(x => x.UserId == userId)))
//            {
//                return games.SingleOrDefault(x => x.GameUsers.Any(x => x.UserId == userId));
//            }
//            else
//            {
//                var game = games.FirstOrDefault();
//                var newDailyGame = new CreateGameDto
//                {
//                    GameAnagramTypeId = 1,
//                            PlayerIds = new[] { userId },
//                    Title = $"{DateTime.UtcNow.ToShortDateString()}",
//                        DailyAnagram = _mapper.Map<GameAnagram>(game.GameAnagrams.FirstOrDefault())
//                };
//                await Create(newDailyGame);
//            }
//        }
//        else
//        {
//            var newDailyGame = new CreateGameDto
//            {
//                PlayerIds = new[] { userId },
//                GameAnagramTypeId = 1,
//                        Title = $"{DateTime.UtcNow.ToShortDateString()}",
//                    TotalAnagrams = 1
//            };
//            await Create(newDailyGame);
//        }
//
//        return await _mapper.ProjectTo<GameDto>(_database
//            .Get<Game>()
//            .Where(new GameByDateSpec(DateTime.UtcNow).And(new GameByTypeSpec(1))
//                    .And(new GameByUserIdSpec(userId))))
//            .SingleOrDefaultAsync();
//    }


    public NewGameAnagramDto create(CreateGameAnagramDto createGameAnagramDto) {

        Game game = new Game();
        game.setTitle(createGameAnagramDto.getTitle());
        game.setDateCreated(new Date());

        game.setGameAnagrams(buildGameAnagramSet(createGameAnagramDto));
        game.setUserSet(buildUserSet(createGameAnagramDto));

        Game newGame = gameRepository.save(game);

        return mapper.map(newGame, NewGameAnagramDto.class);
    }
//        Game newGame = mapper.map(game, Game.class);
//
//        Set<GameAnagram> set = Collections.emptySet();
//        newGame.setGameAnagrams(set);
//
//        Set<GameAnagram> anagrams = Collections.emptySet();
//
//        if (game.getDailyAnagram() != null) {
//            anagrams.add(game.getDailyAnagram());
//        }
//        else {
//         anagrams = createAnagrams(game);
//        }
//
//        // TODO: Complete
//        anagrams.forEach (anagram ->
//        {
//            anagram.getGameUserGameAnagrams() = newGame.getUserSet(x -> new GameUserGameAnagram(x, anagram)).toList();
//
//         newGame.getGameAnagrams().Add(anagram);
//        }
//
//        _database.Add(newGame);
//        await _database.SaveChangesAsync();
//
//
//        return newGame.Id;
//        return 1;
// }

    private GameAnagram buildAnagram(CreateGameAnagramDto createGameAnagramDto, Integer order) {

        Word word = buildWord();
        String anagramedWord = word.getTitle();

        GameAnagram gameAnagram = GameAnagram.builder()
                .dateCreated(new Date())
                .order(order)
                .gameId(1)
                .gameAnagramTypeId(createGameAnagramDto.getGameAnagramTypeId())
                .word(word)
                .anagramWord(anagramedWord)
                .build();

        return gameAnagram;
    }

    private Word buildWord() {

        // Call API
        // Return Word
        return Word.builder()
                .title("Test Word")
                .description("A test word")
                .build();

    }

    private Set<GameAnagram> buildGameAnagramSet(CreateGameAnagramDto createGameAnagramDto) {

        Set<GameAnagram> gameAnagramSet = new HashSet<>();
        int order = 1;

        for (int i = 0 ; i< createGameAnagramDto.getTotalAnagrams(); i++) {
            gameAnagramSet.add(buildAnagram(createGameAnagramDto, order));
            order++;
        }

        if (createGameAnagramDto.)

        for (Integer playerId :  createGameAnagramDto.getPlayerIds()) {
            User user = entityManager.getReference(User.class, playerId);
            userSet.add(user);
        }

        return userSet;

    }

    private Set<User> buildUserSet(CreateGameAnagramDto game) {

        Set<User> userSet = new HashSet<>();

        for (Integer playerId :  game.getPlayerIds()) {
            User user = entityManager.getReference(User.class, playerId);
            userSet.add(user);
        }

        return userSet;
    }

//    public Boolean updateGameAnagramForUser(int id, int anagramId, int userId, UpdateGameUserGameAnagramDto gameUserGameAnagramDto) {
//        // TODO: Complete
//        return true;
//    }
//
//    // TODO: Complete
//    private List<GameAnagram> createAnagrams(CreateGameDto game)
//    {
//        GameAnagram[] anagrams = new GameAnagram[game.TotalAnagrams];
//
//        // TODO: implement tropical fruit API
//        // var fruits = await _tropicalFruitApiService.GetAll("a");
//
//        Random rnd = new Random();
//        List<String> fruitNames = Collections.emptyList();
//        for (var i = 1; i <= game.totalAnagrams; i++)
//        {
//            var position = rnd.Next(1, fruits.Count);
//            fruitNames.Add(fruits.ElementAt(position).Title.ToUpper());
//            fruits.Remove(fruits.ElementAt(position));
//        }
//
//        for (var i = 0; i < fruitNames.Count; i++)
//        {
//            var word = _mapper.Map<Word>(await _tropicalFruitApiService.Get(fruitNames[i]));
//
//            anagrams[i] = new GameAnagram
//            {
//                AnagramWord = word.Title.Scramble(),
//                        DateCreated = DateTime.UtcNow,
//                        GameAnagramTypeId = game.GameAnagramTypeId,
//                        Order = i + 1,
//                        Word = word
//            };
//        }
//
//        return anagrams.ToList();
//    }
}
