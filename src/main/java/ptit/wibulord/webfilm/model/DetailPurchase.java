package ptit.wibulord.webfilm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name="CT_MUA")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetailPurchase {
    @EmbeddedId
    private DetailPurchaseID detailPurchaseID;
    @ManyToOne
    @MapsId("username")
    @JoinColumn(name = "TEN_TK")
    private Account account;
    @ManyToOne
    @MapsId("idPackage")
    @JoinColumn(name = "ID_GOI")
    private Premium premium;
    @Column(name = "NGAYMUA")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datePurchase;
    @Column(name = "SONGAY")
    private int nofDay;
}
