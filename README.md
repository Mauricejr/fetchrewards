# fetchrewards
Checkout repository <br>
Required Scala and sbt   <br>
Run sbt to compile and pull dependencies  <br>
Post json to endpoint: http://localhost:9000/api/email  <br>
Expected json format:
```
{
    "emails": [
        "test.email@gmail.com",
        "test.email+spam@gmail.com",
        "testemail@gmail.com",
        "testemail@gmail.com"
    ]
}
```
