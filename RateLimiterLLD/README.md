# Rate Limiter LLD
Low level Design checks


How would you scale this system to include a credit-based model, where unused requests are carried over as credits?
How would you implement and manage this system in a multithreaded environment?


## Multi Threaded requirements

Each rate limiter instance (for a single user) maintains mutable shared state:

`TokenCreditRateLimiter.java`
```

private double refreshTokensPerSecond;
private int currentTokenCount;
private int currentCredits;
```

We introduced this, to make sure only one thread modifies the internal state at a time, and changes are visible to other threads once the method exits.

```
private final Object lock = new Object();

@Override
public boolean check() {
    synchronized (lock) {
        refill();
        ...
    }
}

```