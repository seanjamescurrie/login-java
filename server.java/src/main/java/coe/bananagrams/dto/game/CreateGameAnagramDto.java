package coe.bananagrams.dto.game;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateGameAnagramDto {


    private int gameAnagramTypeId;
    private int[] playerIds;
    private String title;
    private int totalAnagrams;
    // private GameAnagram dailyAnagram;
}
