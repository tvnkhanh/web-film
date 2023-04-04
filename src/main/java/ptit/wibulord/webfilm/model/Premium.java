package ptit.wibulord.webfilm.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name="GOIHOIVIEN")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Premium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_GOI")
    private int idPackage;
    @Column(name = "SONGAY")
    private int nofDay;
    @Column(name = "GIA")
    private long price;
    @OneToMany(mappedBy = "premium", fetch = FetchType.EAGER)
    private Collection<DetailPurchase> detailPurchases;


}
