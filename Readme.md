# GCP Speedtest API
Exposes a REST API to publish speedtest logs.

## GCP Setup
1. Create PubSub topics
    1. `gcloud pubsub topic create speedtest`
    1. `gcloud pubsub topic create speedtest-trigger`
1. Create Datastore
    1. Goto [Datastore](https://console.cloud.google.com/datastore) in your project
    1. Click on create Datastore and select Datastore Mode 
1. Setup App engine: `gcloud app create`
1. Build and deploy: `./gradlew appengineDeploy`
1. Deploy cron: `gcloud app deploy cron.yaml`

## Usage

### POST /speedtest
Submits the `Speedtest event` to the `speedtest` topic.

**Request payload** 
```
{
   "user": "STRING",
   "device": "NUMBER",
   "timestamp": "NUMBER", // epoch time in ms
   "data": {
        "speeds": {
            "download": "NUMBER",
            "upload": "NUMBER"
        },
        "client": {
            "ip": "STRING",
            "lat": "NUMBER",
            "lon": "NUMBER",
            "isp": "STRING",
            "country": "STRING" // (ISO 3166-1_alpha2)
        },
        "server": {
            "host": "STRING",
            "lat": "NUMBER",
            "lon": "NUMBER",
            "country": "STRING", // (ISO 3166-1_alpha2)
            "distance": "NUMBER",
            "ping": "NUMBER",
            "id": "STRING"
        }
    }
}
```

### GET /trigger
Submits trigger messages to all registered users and devices to the `speedtest-trigger` topic.

PubSub message payload format
```
{
    "user": "<string>",
    "device" <number>
}
```

### POST /trigger/register
Registers a new device on the specified user (stored in datastore namespace `speedtest-trigger`).

**Request payload**
```
{
    "user": "<string>",
    "device" <number>
}
```

**Response payload**
```
{
    "user": "<string>",
    "devices" [<number]
}
```

### DELETE /trigger/register
Removes registration for a device on the specified user (stored in datastore namespace `speedtest-trigger`).

**Request payload**
```
{
    "user": "<string>",
    "device" <number>
}
```

**Response payload**
```
{
    "user": "<string>",
    "devices" [<number]
}
```
