package coe.bananagrams.viewModel.game;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateGameAnagramViewModel {
    public int gameAnagramTypeId;
    public int[] playerIds;
    public String title;
    private int totalAnagrams;
}
