# GCP Speedtest API
Exposes a REST API to publish speedtest logs.

## GCP Setup
1. Create PubSub topics
    1. `gcloud pubsub topic create speedtest`
    1. `gcloud pubsub topic create speedtest-trigger`
1. Create Datastore
    1. Goto [Datastore](https://console.cloud.google.com/datastore) in your project
    1. Click on create datastore and select Cloud Datastore 
1. Setup App engine: `gcloud app create`
1. Build and deploy: `gradle appengineDeploy`

## Automatic build
Follow this guide: https://cloud.google.com/cloud-build/docs/run-builds-on-github#installing_the_google_cloud_build_app

## Usage

### POST /speedtest
Submits the `Speedtest event` to the `speedtest` topic.

**Request payload** 
```
{
  user: <string>,
  device: <integer>,
  data: <string>,
  timestamp: <long>
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