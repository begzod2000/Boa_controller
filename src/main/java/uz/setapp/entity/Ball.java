package uz.setapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.setapp.entity.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ball extends BaseEntity {
    @ManyToOne
    private Xodimlar xodimlar;
    private Long ball1;
    private Long ball2;
    private Long ball3;
    private Long ball4;
    private Long ball5;
    private Long ball6;
    private Long ball7;
    private Long ball8;
    private Long ball9;
    private Long ball10;
}

