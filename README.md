
# Java Core Services

Microservices in Java Springboot


## Run Locally

Need to add config file as below format 
```bash
spring :
  data:
    mongodb:
      uri : #mongodb_url
      database : #db_name
discord_webhook: #discord_webhook_url(for notification)
ratelimiter:
  instances:
    public:
      limitForPeriod: 2
      limitRefreshPeriod: 1m
      timeoutDuration: 1s

```

It will be run on port 8080
## List Of Microservices

|              |                 
| ----------------- | 
|  Cache Service |
|  Discord Notification Service
| Url Shortening Service 

