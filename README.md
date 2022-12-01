# spring-boot-auto-irrigation-system
This is a spring boot application for automated irrigation system. 

How to run the application. 

Fork the repository and clone the project to your favourite directory

Open the project directory using your favourite IDE. This is a maven project so you can import a maven project. 

Then run the application as a java application. 


# Endpoints. 

There are various endpoints in the project

1. Create plot endpoint. Here is the url to use

http://localhost:8081/plot/add

Please note that the port may change depending on what you configured in the application.properties file. 

Here is the payload for adding a plot. This is a POST Request

{
    "size" : 2400,
    "crop" : "maize",
    "location" : "embu"
}

Sample response with http status 200 Ok

{
    "id": 2,
    "size": 2400.0,
    "crop": "maize",
    "location": "embu",
    "details": null
}

2. Get All Plots Details

Make a Get Request to the following endpoint. 

http://localhost:8081/plot/all

Sample response:

[
    {
        "id": 1,
        "size": 3500.0,
        "crop": "maize",
        "location": "keiyo",
        "details": {
            "amount_of_water": 3000.0,
            "irrigation_start_time": "14:52:00",
            "irrigation_end_time": "12:00:00",
            "sensor_notified": null,
            "id": 3
        }
    },
    {
        "id": 2,
        "size": 2400.0,
        "crop": "maize",
        "location": "embu",
        "details": null
    }
]

3. Update Plot endpoint

Make a PUT request to this endpoint:

http://localhost:8081/plot/update/{id}

Payload:

{
        "size": 3500.0,
        "crop": "Beans",
        "location": "Nairobi"
    }
    
    
    Kindly ensure to use the correct plot id in the path variable. The plot id is indicated in the get all plots endpoint. 
    
 4. Configure Plot endpoint

Make a PUT request to the following endpoint

http://localhost:8081/plot/configure/{id}

sample payload

{
    "amount_of_water": 3000.0,
    "irrigation_start_time": "14:52:00",
    "irrigation_end_time": "16:00:00"
}

sample response

{
    "id": 1,
    "size": 3500.0,
    "crop": "Beans",
    "location": "Nairobi",
    "details": {
        "amount_of_water": 3000.0,
        "irrigation_start_time": "14:52:00",
        "irrigation_end_time": "16:00:00",
        "sensor_notified": null,
        "id": 3
    }
}


# Sensor Interface

There is a service for notifying the irrigation sensor. This service is in the PlotService.java file under the services package. 

The service is a scheduled job that runs after every 60 seconds. It fetches the configured plots from the database and checks the irrigation time for each. When the irrigation time is reached, the notifySensor method is called which invokes a HTTP POST request to the sensor interface. 

A dummy sensor interface is present in the SensorController.java file. This returns a dummy string saying the sensor has been notified. (Please note the purpose of this excercise was not to build the irrigation sensor interface.) 

The sensor uses a circuit breaker whose configurations are set in the application.properties file. When the sensor interface goes down, the circuit breaker calls the fallback method which is the alerts service. This sends a message indicating that the service was unable to communicate to the sensor.

# Unit Tests

There are various unit tests in the tests package for testing the correctness of the various single units of the application. 



