package ptit.wibulord.webfilm.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity()
@Table(name="THELOAI")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TL")
    private int idCategory;

    @Column(name = "TEN_TL")
    private String categoryName;

    @ManyToMany(mappedBy = "categoryList")
    private Collection<Film> filmList;

}
