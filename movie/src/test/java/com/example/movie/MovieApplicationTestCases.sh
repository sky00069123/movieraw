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
curl -X POST http://localhost:8080/movie -H "Content-Type: application/json" -d "{\"title\":\"Test Movie\",\"year\":2022,\"cast\":\"Ken\",\"genres\":\"action\"}"

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
curl -X GET http://localhost:8080/movie/cast?cast=Ken

# GET /movie/genres?genres={genre}
# query by movie genre
# return all movies in the db contianing the provided genre
curl -X GET http://localhost:8080/movie/genres?genres=action

# POST /movie/upload
# -F fileName={fileNameUploadToS3} -F file=@"{filePathJSON}"
# workflow:
# json file of the movie data will be uploaded to S3 and the movie data also insert to database
curl -X POST http://localhost:8080/movie/upload -F fileName="s3data" -F file=@"C:\Users\kench\Downloads\movieraw\movie\src\main\java\com\example\main\s3data.json"
