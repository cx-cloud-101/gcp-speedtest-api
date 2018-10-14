# GCP Speedtest API
Exposes a REST API to publish speedtest logs.

## GCP Setup
1. Create PubSub topic: `gcloud pubsub topic create speedtest`
1. Setup App engine: `gcloud app create`
1. Build and deploy: `gradle appengineDeploy`

## Automatic build
Follow this guide: https://cloud.google.com/cloud-build/docs/run-builds-on-github#installing_the_google_cloud_build_app

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
