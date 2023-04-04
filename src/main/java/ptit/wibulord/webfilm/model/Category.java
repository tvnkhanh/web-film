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

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "CT_THELOAI",
            joinColumns = {@JoinColumn(name = "ID_TL")},
            inverseJoinColumns = {@JoinColumn(name = "ID_PHIM")})
    private Collection<Film> filmList;
}
