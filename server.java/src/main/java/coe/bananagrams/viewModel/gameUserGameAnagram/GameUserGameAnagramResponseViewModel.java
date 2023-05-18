package coe.bananagrams.viewModel.gameUserGameAnagram;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameUserGameAnagramResponseViewModel {
    private int attempts;
    private Boolean isSolved;
    private int gameId;
    private int gameAnagramId;
    private int userId;
}
