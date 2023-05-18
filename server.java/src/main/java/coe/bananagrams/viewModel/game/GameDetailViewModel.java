package coe.bananagrams.viewModel.game;

import coe.bananagrams.viewModel.gameAnagram.GameAnagramViewModel;
import coe.bananagrams.viewModel.gameAnagramGameType.GameAnagramTypeViewModel;
import coe.bananagrams.viewModel.user.UserViewModel;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameDetailViewModel {
    private int id;
    private Date dateCreated;
    private String title;
    private GameAnagramTypeViewModel gameAnagramType;
    private GameAnagramViewModel[] gameAnagrams;
    private UserViewModel[] gameUsers;
}
