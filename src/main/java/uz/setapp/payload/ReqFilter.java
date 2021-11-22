package uz.setapp.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqFilter {

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date orderDate;

    private Long xodimId;

}
