# CREATE queue


```bash
docker exec -it rabbitmq bash

rabbitmq-plugins enable rabbitmq_consistent_hash_exchange
```

Create new queue with type x-consistent-hash