package uz.setapp.entity.enums;

import java.util.HashMap;
import java.util.Map;

public enum TransferType {
    ORDER(0),
    HA(1),
    TASTIQLASH_2(2),
    YOQ(4);
    private final int number;
    TransferType(int number) {
        this.number=number;
    }
    private static final Map<Integer,TransferType> map;
    static {
        map=new HashMap<Integer,TransferType>();
        for (TransferType v:TransferType.values()) {
            map.put(v.number, v);
        }
    }
    public static TransferType findByKey(int i){
        return map.get(i);
    }
}
