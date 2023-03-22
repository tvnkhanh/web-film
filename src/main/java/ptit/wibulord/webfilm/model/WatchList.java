package ptit.wibulord.webfilm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity()
@Table(name="DSTHEODOI")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WatchList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DSTD")
    private int idWatchList;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ND")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CT_THEODOI",
                joinColumns = {@JoinColumn(name = "ID_DSTD")},
                inverseJoinColumns = {@JoinColumn(name = "ID_PHIM")})
    private Collection<Film> films;

    public WatchList(User user) {
        this.user = user;
    }
}
