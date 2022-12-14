# movie data query Spring Boot API

## Assumptions

* **Auth** - Cognito provides JSON Web Tokens (JWT) and along with AppSync fine-grained authorization on what data types users can access.

## Constraint

## AWS Services
- EKS AWS managed autoscaling EC2 node group
- RDS MySQL Database
- S3
<img width="397" alt="movie-api-diagram" src="https://user-images.githubusercontent.com/18232987/207681627-fe9266e3-cf61-412b-8b29-36507e630f9e.png">

## Deployment

### Stack
* **API** - Spring Boot REST API to demonstrate movie data handling using CRUD functions. The application is contianerised with Docker and can be deployed to Kubernetes.
* **Data** - Data is stored in MySQL Database
* **Auth** - Cogni

### Project Structure

### Deployment Command

'''sh
mvn package
java -jar target/movie-0.0.1-SNAPSHOT.jar
docker build -t movie .
docker tag movie {ecr_repo}/{image_name}:{tag}
docker push {ecr_repo}/{image_name}:{tag}
kubectl apply -f deployment.yaml
kubectl apply -f service.yaml
kubectl port-forward deployment/movie 8080:8080
'''

