package pl.dpotyralski.producer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class RiskCheckRequest {

    private int amount;
    private String company;

}
