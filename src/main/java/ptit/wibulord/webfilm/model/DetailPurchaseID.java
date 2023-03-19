package ptit.wibulord.webfilm.model;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class DetailPurchaseID implements java.io.Serializable{
    private int idPackage;
    private String username;

    public int getIdPackage() {
        return idPackage;
    }

    public void setIdPackage(int idPackage) {
        this.idPackage = idPackage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailPurchaseID that = (DetailPurchaseID) o;
        return idPackage == that.idPackage && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPackage, username);
    }
}
