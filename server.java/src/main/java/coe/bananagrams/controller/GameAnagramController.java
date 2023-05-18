package coe.bananagrams.controller;

import coe.bananagrams.dto.NewGameAnagramDto;
import coe.bananagrams.dto.game.CreateGameAnagramDto;
import coe.bananagrams.dto.gameUserGameAnagram.UpdateGameUserGameAnagramDto;
import coe.bananagrams.entity.GameAnagram;
import coe.bananagrams.mapper.Mapper;
import coe.bananagrams.service.GameAnagramService;
import coe.bananagrams.viewModel.game.CreateGameAnagramViewModel;
import coe.bananagrams.viewModel.game.GameDetailViewModel;
import coe.bananagrams.viewModel.game.GameViewModel;
import coe.bananagrams.viewModel.game.UpdateGameViewModel;
import coe.bananagrams.viewModel.gameUserGameAnagram.GameUserGameAnagramResponseViewModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/games/anagrams/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class GameAnagramController {

    private final GameAnagramService gameAnagramService;
    private final Mapper mapper;

    @GetMapping()
    public ResponseEntity<List<GameViewModel>> findAll() {
        List<GameViewModel> gameViewList = mapper.map(gameAnagramService.findAll(), GameViewModel.class);
        return ResponseEntity.ok(gameViewList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDetailViewModel> getById(@PathVariable int id) {
        GameDetailViewModel gameViewModel = mapper.map(gameAnagramService.findById(id), GameDetailViewModel.class);
        return ResponseEntity.ok(gameViewModel);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@Valid @RequestBody CreateGameAnagramViewModel createGameAnagramViewModel) {
        NewGameAnagramDto newGameAnagram = gameAnagramService.create(mapper.map(createGameAnagramViewModel, CreateGameAnagramDto.class));
        return new ResponseEntity(newGameAnagram.getGameId(), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateGameUserGameAnagramAttempt(@PathVariable int id, @PathVariable int anagramId, @Valid @RequestBody UpdateGameViewModel gameDetails) {
        UpdateGameUserGameAnagramDto updateGame = mapper.map(gameDetails, UpdateGameUserGameAnagramDto.class);

        // var loggedInUser = await _authorize.GetLoggedInAccount();

        Boolean isSolved = gameAnagramService.updateGameAnagramForUser(id, anagramId, 1, updateGame);
        GameUserGameAnagramResponseViewModel update = new GameUserGameAnagramResponseViewModel(gameDetails.attempts, isSolved, anagramId, id, 1);

        // await _gameHub.Clients.Group(id.ToString()).SendAsync("SendUpdate", update);
        // await _gameHub.Clients.All.SendAsync("SendUpdate");

        return ResponseEntity.ok(isSolved.toString());
    }

}
