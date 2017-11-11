import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        description("Should return that risk is NOT_OK")
        method POST()
        urlPath("/risks")
        body(
                amount: $(regex('(1[5-9]|[2-9]\\d)')),
                company: "Microsoft"
        )
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 200
        body("""{"status": "NOT_OK"}""")
        headers {
            contentType(applicationJson())
        }
    }
}
