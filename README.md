# FilmStatistics

### Для получение средней оценки которую ставил user за определнное время необходима отправить GET запрос с параметрами, например:  https://moviestatistics.herokuapp.com/review/avgRating/1/?from=2012-01-02&to=2021-02-10
### Для получение максимальной оценки которую ставил user за определнное время необходима отправить GET запрос с параметрами, например:   https://moviestatistics.herokuapp.com/review/maxRating/1/?from=2012-01-02&to=2021-02-10
### Для получение минимальной оценки которую ставил user за определнное время  необходима отправить GET запрос с параметрами, например:  https://moviestatistics.herokuapp.com/review/minRating/1/?from=2012-01-02&to=2021-02-10

### Для получения отсортированго списка фильмов по возрастанию рейтинга необходимо отправить GET запрос и указать номер страницы, например https://moviestatistics.herokuapp.com/film/sort/0
### Для получения отсортированго списка фильмов по убыванию рейтинга необходимо отправить GET запрос и указать номер страницы, например https://moviestatistics.herokuapp.com/film/sortDec/0
