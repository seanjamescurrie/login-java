package coe.bananagrams.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewGameAnagramDto {

    private int id;
    private String anagramWord;
    private Date dateCreated;
    private int order;
    private int gameId;
    private int gameAnagramTypeId;
    private int wordId;
}
