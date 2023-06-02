package ptit.wibulord.webfilm.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name="CT_MUA")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DetailPurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MUA")
    private int detailPurchaseID;
    @ManyToOne
    @JoinColumn(name = "ID_ND")
    private User user;
    @ManyToOne
    @JoinColumn(name = "ID_GOI")
    private Premium premium;
    @Column(name = "NGAYMUA")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datePurchase;
    @Column(name = "SODIEM")
    private int point;
    @Column(name = "GIA")
    private double price;
}
