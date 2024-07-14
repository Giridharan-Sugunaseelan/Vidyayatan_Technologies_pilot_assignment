package com.vidyayatantechnologies.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private Integer book_id;

    private String bookTitle;

    private String author;

    private Boolean available;

}
