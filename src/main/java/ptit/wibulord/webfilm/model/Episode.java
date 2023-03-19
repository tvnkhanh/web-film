package ptit.wibulord.webfilm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity()
@Table(name="TAP")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TAP")
    private int epID;
    @Column(name = "LUOTXEM")
    private long view;
    @Column(name = "NGAYDANG")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datePosted;

    @ManyToOne
    @JoinColumn(name="ID_PHIM")
    private Film film;
}
