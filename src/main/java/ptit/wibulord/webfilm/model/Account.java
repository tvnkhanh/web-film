package ptit.wibulord.webfilm.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Collection;
import java.util.Date;

@Entity()
@Table(name="TAIKHOAN")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {
    @Id
    @Column(name = "TEN_TK")
    private String username;
    @Column(name = "MATKHAU")
    private String password;
    @Column(name = "NGAYTAO")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    @Column(name = "TRANGTHAI")
    private boolean status;
    @ManyToOne
    @JoinColumn(name="ID_CD")
    private Role role;
    @OneToOne(mappedBy = "account")
    private User user;


}
