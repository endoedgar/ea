### Dockerfile for exercise24
This dockerfile uses mansante/hornetq docker image to run the exercise.
My version just adds a new queue called testQueue to the container so you can run the exercise.

## Use this to run
docker build .
docker run -d -p 5445:5445 -p 1098:1098 -p 1099:1099 IMAGE_ID

You need to expose those ports to be able to run the exercise.
