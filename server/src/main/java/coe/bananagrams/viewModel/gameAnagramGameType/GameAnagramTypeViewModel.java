package coe.bananagrams.viewModel.gameAnagramGameType;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameAnagramTypeViewModel {
    private int id;
    private int maxAttempts;
    private int timeAllowed;
    private String title;
}
