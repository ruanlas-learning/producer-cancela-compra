package com.example.producercancelacompra;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class CancelaCompraInput implements Serializable {

    private Integer productId;
    private Integer userId;
}
