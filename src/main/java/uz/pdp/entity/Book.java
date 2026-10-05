package uz.pdp.entity;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Book {

    private int id;

    private String title;

    private int pages;

    private String author;

}
