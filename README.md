# movie data query Spring Boot API

## Constraints and Improvements

* **Response Time** - API response time was not extensively tested due to development environment and budget constraint. The response time can be further improved by using better performance database instance, caching and in-memory data, improve network with cloudfront or distribution close to API consumer, rate limiting and API policy to prevent abuse usage.
* **Auth** - 
* **Auth** - 
* **Auth** - Cognito provides JSON Web Tokens (JWT) and along with AppSync fine-grained authorization on what data types users can access.

## AWS Services
* **EKS** - Using EKS AWS managed autoscaling EC2 node group and autoscaling Kubernetes deployment to create zero downtime solution
* **RDS** - Due to budget constraint RDS with MySQL is chosen to permanently store the structured relational movie data, RDS can be configured with backup instance to prevent data loss
* **S3**  - S3 is used to store the movie updates uploaded by provides
<img width="450" alt="movie-api-diagram" src="https://user-images.githubusercontent.com/18232987/207712924-6cd5f5b0-f511-455d-a379-7d0d0262f7d8.png">

## Deployment

### Stack
* **API** - Spring Boot REST API to demonstrate movie data handling using CRUD functions. The application is contianerised with Docker and can be deployed to Kubernetes.
* **Data** - Data is stored in MySQL Database
* **Auth** - Cogni

### Project Structure

    .
    ├── movie                    # Movie API
    │   ├── src                  # Spring Boot Java files
    │   ├── Dockerfile           # Dockerfile
    │   ├── deployment.yaml      # deploy yaml file for kubernetes
    │   └── pom.xml              # pom file for maven build 
    └── aws
        ├── ecr                  # files to set up AWS ECR
        ├── eks                  # files to set up and configure EKS (VPC, clusters, nodes, IAM roles, policy, etc)
        ├── rds                  # movie data file and database query file
        └── s3                   # files to set up AWS S3 bucket
    

### Deployment Command
```ssh
mvn package
docker build -t movie .
docker tag movie {ecr_repo}/{image_name}:{tag}
docker push {ecr_repo}/{image_name}:{tag}
kubectl apply -f deployment.yaml
kubectl apply -f service.yaml
kubectl port-forward deployment/movie 8080:8080
```

## Test API
```shell
# GET /movie/count
# return the number of entry in database
curl http://localhost:8080/movie/count

# GET /movie/{id}
# query id
# return movie data (title, year, cast, genres) of the id
curl http://localhost:8080/movie/17362

# PUT /movie{id}
# link id
# input data json format movie data (title, year, cast, genres)
# update the movie of the id to the provided data
curl -X PUT http://localhost:8080/movie/17744 -H "Content-Type: application/json" -d "{\"title\":\"Test Movie put\",\"year\":2022,\"cast\":\"Ken\",\"genres\":\"action\"}"

# POST /movie
# input data json format movie data (title, year, cast, genres)
# add new row to db with the provided data
curl -X POST http://localhost:8080/movie -H "Content-Type: application/json" -d "{\"title\":\"Test Movie\",\"year\":2022,\"cast\":\"John\",\"genres\":\"action\"}"

# DELETE /movie/{id}
# query by id
# remove the movie data of the provided id
curl -X DELETE http://localhost:8080/movie/17746

# GET /movie/title?title={title}
# query by movie title
# return all movies in the db contianing the provided title
curl -X GET http://localhost:8080/movie/title?title=movie

# GET /movie/year?year={year}
# query by movie year
# return all movies in the db with the provided year
curl -X GET http://localhost:8080/movie/year?year=1900

# GET /movie/cast?cast={cast}
# query by movie cast
# return all movies in the db contianing the provided cast
curl -X GET http://localhost:8080/movie/cast?cast=John

# GET /movie/genres?genres={genre}
# query by movie genre
# return all movies in the db contianing the provided genre
curl -X GET http://localhost:8080/movie/genres?genres=action

# POST /movie/upload
# -F fileName={fileNameUploadToS3} -F file=@"{filePathJSON}"
# workflow:
# json file of the movie data will be uploaded to S3 and the movie data also insert to database
curl -X POST http://localhost:8080/movie/upload -F fileName="s3data" -F file=@"C:\Users\kench\Downloads\movieraw\movie\src\main\java\com\example\main\s3data.json"
```
