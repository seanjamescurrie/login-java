package coe.bananagrams.viewModel.game;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateGameViewModel {
    public int attempts;
    public String attempt;
}
