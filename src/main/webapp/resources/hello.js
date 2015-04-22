/**
 * Created by Attila on 2015-04-21.
 */
$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/rest/movie/byId/2402365"
    }).then(function(data) {
        console.log(data);
        $('.movie-id').append(data.movie.movieid)
        $('.movie-title').append(data.movie.title);

        var table = '';
        for(var i = 0; i < data.actors.length; i++) {
            $('#actors-table').append(
            '<tr>' +
                '<td>' + data.actors[i].role + '</td>' +
                '<td>' + data.actors[i].name + '</td>' +
                '<td>' + data.actors[i].links[0].href + '</td>' +
            '</tr>'
        );

        }
        /*
            table += '<tr><td>' + data.movie.actors[i].role + '</td><td>'
                + data.movie.actors[i].name + '</td><td>'
                + data.movie.actors[i].links[0].href + '</td></tr>';

        $('#actors-table').first().after(table);
        */
    });
});