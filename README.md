# Restaurant Search

### Requirements

* Java 17

If you don't have the necessary Java version, you can install it with SDKMAN

1. Install SDKMAN
    ```bash
    curl -s "https://get.sdkman.io" | bash
    ```

2. Install Java with SDKMAN [env command](https://sdkman.io/usage#env)
    ```bash
    sdk env install
    ```

### How to run

``` shell
./gradlew bootRun
```

### Search API

Once the server from the previous command is up and running, the below Search API will be available to use:

#### URL (POST)

```
http://localhost:8080/search
```

#### Body (json)

```json
{
  "restaurantName": "Table",
  "customerRating": "2",
  "distance": "10",
  "price": "200",
  "cuisine": "Mexican"
}
```
