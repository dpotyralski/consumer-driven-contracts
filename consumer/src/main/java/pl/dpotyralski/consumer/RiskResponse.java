package pl.dpotyralski.consumer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RiskResponse {
    private RiskResponseStatus status;
}