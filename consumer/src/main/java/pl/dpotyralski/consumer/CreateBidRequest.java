package pl.dpotyralski.consumer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateBidRequest {

    private Integer amount;
    private String company;
    private Long walletId;

}
