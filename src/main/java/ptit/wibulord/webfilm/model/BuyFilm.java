package ptit.wibulord.webfilm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MUAPHIM")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BuyFilm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MP")
    private int buyID;
    @ManyToOne
    @JoinColumn(name = "ID_ND")
    private User user;
    @ManyToOne
    @JoinColumn(name = "ID_PHIM")
    private Film film;
    @Column(name = "GIA")
    private int price;
}
