package pl.isa.biblioteka.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private String category;
    private String borrowingDate;
    private String returnDate;
    private Integer counter;
}
