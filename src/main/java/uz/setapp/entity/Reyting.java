package uz.setapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.setapp.entity.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reyting extends BaseEntity {

    private String name;
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
    private Long ball11;
    private Long middle;
    private Long foiz;
    private Long hisob;
    private Long allBall;
    @ManyToOne
    private Xodimlar xodimlar;
}
