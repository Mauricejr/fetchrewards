# fetchrewards
Checkout repository <br>
Requires scala and sbt 
Run sbt to compile and pull dependencies 
Post json to endpoint: http://localhost:9000/api/email
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
