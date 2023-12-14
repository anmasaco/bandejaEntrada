package com.bandeja.entrada.entities;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BandejaEntrada {

    private String fecharadicacion;
    private BigDecimal numeroExpediente;
    private String numeroRadicado;

}
