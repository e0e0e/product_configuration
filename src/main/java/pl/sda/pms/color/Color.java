package pl.sda.pms.color;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import pl.sda.pms.OrderFeature.OrderFeature;

@Entity
@Audited
@EntityListeners(AuditingEntityListener.class)
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String Ral;
    private String Hex;
    private String name;
    private String type;

    @OneToOne
    @JsonIgnore
    private OrderFeature orderFeature;

    public Color() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return this.Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getRal() {
        return this.Ral;
    }

    public void setRal(String Ral) {
        this.Ral = Ral;
    }

    public String getHex() {
        return this.Hex;
    }

    public void setHex(String Hex) {
        this.Hex = Hex;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrderFeature getOrderFeature() {
        return this.orderFeature;
    }

    public void setOrderFeature(OrderFeature orderFeature) {
        this.orderFeature = orderFeature;
    }


}