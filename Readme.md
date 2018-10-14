# GCP Speedtest API
Exposes a REST API to publish speedtest logs.

## GCP Setup
1. Create PubSub topic: `gcloud pubsub topic create speedtest`
1. Setup App engine: `gcloud app create`
1. Build and deploy: `gradle appengineDeploy`

## Usage

POST /speedtest
```
{
  user: <string>,
  device: <integer>,
  data: <string>,
  timestamp: <long>
}
```
