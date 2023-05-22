package coe.bananagrams.viewModel.gameAnagram;

import coe.bananagrams.viewModel.gameUserGameAnagram.GameUserGameAnagramViewModel;

import java.util.Date;
import java.util.List;

public class GameAnagramViewModel {
    private int id;
    private String anagramWord;
    private Date dateCreated;
    private int order;
    private int gameId;
    private int wordId;
    private GameUserGameAnagramViewModel[] gameUserGameAnagrams;
}
