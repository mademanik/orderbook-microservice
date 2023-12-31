package com.orderbookservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookAuthor implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long bookId;
}
