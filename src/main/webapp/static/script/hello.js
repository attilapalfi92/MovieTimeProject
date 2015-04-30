/**
 * Created by Attila on 2015-04-21.
 */
$(document).ready(function() {
    var mId = $('#mId_input').val();
    var url = "http://localhost:8090/rest/movie/byId/" + mId;
    console.log(url);
    $.ajax({
        url: url
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

        $('#actors-table').first().after(table);
    });
});