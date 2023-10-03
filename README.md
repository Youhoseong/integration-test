# Integration-Test

## How to teardown?

### @Transactional
- Strong in parallel testing.
- BUT Interrupt transaction boundary of real application logic.
```shell
2023-10-03T21:36:06.502+09:00 DEBUG 27607 --- [-1 @coroutine#3] o.h.e.t.internal.TransactionImpl         : rolling back
```

