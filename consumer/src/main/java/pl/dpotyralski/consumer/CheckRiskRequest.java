package pl.dpotyralski.consumer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class CheckRiskRequest {

    private Integer amount;
    private String company;

}
