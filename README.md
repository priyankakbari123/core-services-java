
# Java Core Services

Microservices in Java Springboot


## Run Locally

#### Need to add config file as below format 
```bash
spring :
    data:
        mongodb:
        uri : #mongodb_url
        database : #db_name
    cache:
        type: redis
        host: localhost #hostUrl
        port: 6379 #port
        redis:
            time-to-live: 60000
discord_webhook: #discord_webhook_url(for notification)
ratelimiter:
  instances:
    public:
      limitForPeriod: 2
      limitRefreshPeriod: 1m
      timeoutDuration: 1s

```

It will be run on port 8080

#### Need To Install and Run Redis Server
```bash
for windows: https://github.com/tporadowski/redis/releases
download the zip and run the server.exe
need to add connection urls into config(app.yml)
```
## List Of Microservices

|              |                 
| ----------------- | 
|  Cache Service(Redis & ConcurrentMapCache) |
|  Discord Notification Service
| Url Shortening Service 

