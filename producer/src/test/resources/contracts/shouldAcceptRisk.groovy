package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        description("Should return that risk is OK")
        method POST()
        urlPath("/risks")
        body(
                """
        {
          "amount": 10,
          "company": "IBM"
        }
        """)
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 200
        body("""{"status": "OK"}""")
        headers {
            contentType(applicationJson())
        }
    }
}
