package ptit.wibulord.webfilm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity()
@Table(name="CHUCDANH")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CD")
    private int idRole;
    @Column(name = "TEN_CD")
    private String roleName;
    @OneToMany(mappedBy="role", fetch=FetchType.EAGER)
    private Collection<Account> accounts;

}
