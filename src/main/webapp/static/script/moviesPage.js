/**
 * Created by Attila on 2015-05-03.
 */
// if the slider changes, we update the slider
function sliderChanged(value) {
    $('#pSize_label').text(value);
    $('#pageNum_label').text('1');

    titleChanged($('#mTitle_input').val());
};

// event handler for the click on next button
function nextClicked() {
    var pageNumLabel = $('#pageNum_label');
    var pNum = parseInt(pageNumLabel.text());
    pNum = pNum + 1;
    console.log(pNum);
    pageNumLabel.text(pNum.toString());

    initMoviesTable();
    if(!enterPressed) {
        $('#movie_list_div').hide(50);
        searchMoviesFast($('#mTitle_input').val());
        $('#movie_list_div').show(50);
    } else {
        $('#movie_list_div').hide(300);
        searchMoviesMore($('#mTitle_input').val());
        $('#movie_list_div').show(300);
    }
}

// event handler for the click on previous button
function prevClicked() {
    var pageNumLabel = $('#pageNum_label');
    var pNum = parseInt(pageNumLabel.text());
    if(pNum > 1)
        pNum = pNum - 1;
    console.log(pNum);
    pageNumLabel.text(pNum.toString());

    initMoviesTable();
    if(!enterPressed) {
        $('#movie_list_div').hide(50);
        searchMoviesFast($('#mTitle_input').val());
        $('#movie_list_div').show(50);
    } else {
        $('#movie_list_div').hide(300);
        searchMoviesMore($('#mTitle_input').val());
        $('#movie_list_div').show(300);
    }
}

// if user clicks on the movie details button
// this function shows every information about
// the movie
function loadMovieDetails(event) {
    console.log('button pressed');

    $('#mustsee_div').hide('fast');
    $('#haveseen_div').hide('fast');
    $('#actors_div').hide('fast');

    var url = event.target.name;
    // http://localhost:8090/rest/movieTime/movie/byId/2122594
    var startIndex = url.indexOf('/rest');
    url = url.substring(startIndex);
    url = url + '?access_token=' + Cookies.get('accessToken');
    console.log(url);

    var $loading = $('#loadingDiv');
    $loading.show();
    $.ajax({
        url: url,
        success: function(data) {
            $loading.hide();
            console.log(data);

            // this function fills the table containing the actors
            // #actors_table
            loadActorsTable(data.actors);

            // this function fills the div containing
            // every other data about the movie
            loadMovieDetailsDiv(data);

            // creating the "Show Plot" button
            startIndex = url.indexOf('/byId/');
            var movId = url.substring(startIndex + 6);
            var plotBtn = document.createElement('input');
            plotBtn.setAttribute('type', 'button');
            plotBtn.setAttribute('class', 'btn btn-default');
            plotBtn.setAttribute('value', 'Plot');
            plotBtn.setAttribute('name', movId);
            $(plotBtn).click(movieShowPlotBtnClicked);
            $('#movie_plot_btn_div').empty();
            $('#movie_plot_btn_div').append(plotBtn);
            firstPlotClick = true;

            $('#movies_div').show('fast');
            $('#movie_details_div').show();
            $('#movie_details_hideable').show('fast');
            $('#movie_plot_p').hide();
        },
        error: function(data) {
            $('#loadingDiv').hide();
            ajaxFailedHandler(data, loadMovieDetails, logOut, event);
        }
    });
}


// this function fills the div containing
// every other data about the movie
function loadMovieDetailsDiv(movie) {

    $('#movie_title_h2').empty().text(movie.movie.title);

    var dirsP = $('#directors_p');
    dirsP.empty();
    for (var i = 0; i < movie.directors.length; i++) {
        dirsP.append(movie.directors[i]['name'] + ';   ');
    }

    var writsP = $('#writers_p');
    writsP.empty();
    for (var i = 0; i < movie.writers.length; i++) {
        writsP.append(movie.writers[i]['name'] + ';   ');
    }

    var prodsP = $('#producers_p');
    prodsP.empty();
    for (var i = 0; i < movie.producers.length; i++) {
        prodsP.append(movie.producers[i]['name'] + ';   ');
    }

    var editsP = $('#editors_p');
    editsP.empty();
    for (var i = 0; i < movie.editors.length; i++) {
        editsP.append(movie.editors[i]['name'] + ';   ');
    }

    var genresP = $('#genres_p');
    genresP.empty();
    for (var i = 0; i < movie.genres.length; i++) {
        genresP.append(movie.genres[i]['genre'] + ';   ');
    }

    var langP = $('#languages_p');
    langP.empty();
    for (var i = 0; i < movie.languages.length; i++) {
        langP.append(movie.languages[i]['language'] + ';   ');
    }

    var locP = $('#locations_p');
    locP.empty();
    for (var i = 0; i < movie.locations.length; i++) {
        if(!movie.locations[i].addition) {
            locP.append('<b>' + movie.locations[i].location + '</b>' + ';   ');
        } else {
            locP.append('<b>' + movie.locations[i].location + '</b> <i>' + movie.locations[i].addition + '</i>;   ');
        }
    }
}


// this function fills the table containing the actors
// when the user wants to know more about a movie
function loadActorsTable(actors) {
    var table = document.getElementById('movie_actorTable');

    $(table).empty().append(
        "<tr> " +
        "<th>Name</th> " +
        "<th>Role</th> " +
        "<th>Sex</th> " +
        "<th>actorId</th> " +
        "<th>link</th> " +
        "</tr>");

    for(var i = 0; i < actors.length; i++) {
        var actor = actors[i];
        var row = table.insertRow(-1);
        var cell_actorname = row.insertCell(-1);
        var cell_role = row.insertCell(-1);
        var cell_sex = row.insertCell(-1);
        var cell_actorid = row.insertCell(-1);
        var cell_btn = row.insertCell(-1);

        $(cell_actorname).text(actor['name']);
        $(cell_role).text(actor.role);
        $(cell_sex).text(actor.sex);
        $(cell_actorid).text(actor.actorId);

        var detailsBtn = document.createElement('input');
        detailsBtn.setAttribute('type', 'button');
        detailsBtn.setAttribute('class', 'btn btn-default');
        detailsBtn.setAttribute('value', 'Show details');
        detailsBtn.setAttribute('name', actor.links[0].href);
        $(detailsBtn).click(loadActorDetails);
        cell_btn.appendChild(detailsBtn);
    }

}



// this function called when the user wants to read the plot of the movie.
// so the user clicks on the "Plot" button.
var firstPlotClick = true;
function movieShowPlotBtnClicked(event) {
    var plotPar = $('#movie_plot_p');
    if (firstPlotClick) {
        plotPar.empty();
        var movieId = event.target.name;
        var url = '/rest/movieTime/plot/byMovieId/' + movieId;
        plotPar.hide();
        $.ajax({
            url: url,
            success: function(data) {
                if(data) {
                    plotPar.text(data.plotText);
                    firstPlotClick = false;
                    plotPar.show(500);
                }
            },
            error: function(data) {
                ajaxFailedHandler(data, movieShowPlotBtnClicked, logOut, event);
            }
        });

    } else {
        plotPar.toggle(500);
    }
}


// this is the event handler of the "Details" button
function toggleMovieDetails() {
    $('#movie_details_hideable').toggle(400);
}

// this global variable contains the information about
// if the enter is pressed. its needed for paging.
var enterPressed = false;

// this function is called when the user presses enter
// in the search text of moves section
$('#mTitle_input').keypress(function (e){
    if(e.which == 13 || e.keyCode == 13) {
        enterPressed = true;
        $('#pageNum_label').text('1');

        var mTitle = $('#mTitle_input').text();
        console.log(mTitle);
    }

});

// this function initializes the movies table
// in the movies section
function initMoviesTable() {
    $('#movie_details_hideable').hide('fast');

    $('#moviesTable').empty().append(
        "<tr> " +
        "<th>Title</th> " +
        "<th>Tagline</th> " +
        "<th>movieId</th> " +
        "<th>link</th> " +
        "</tr>");
}

// when the user presses enter, ot taps on the button
// this function is called in order to search for movies
// with more results
function titleEnterPressed() {
    enterPressed = true;
    $('#pageNum_label').text('1');
    var value = $('#mTitle_input').val();

    $('#movie_list_div').hide(50);
    // if the input is not empty
    if(value) {
        initMoviesTable();
        searchMoviesMore(value);
        $('#movie_list_div').show(50);

    } else {
        $('#movie_list_div').hide(50);
        $('#next_btn').prop('disabled', true);
        $('#prev_btn').prop('disabled', true);
    }
}

// event handler for the title input
function titleChanged(value) {
    enterPressed = false;
    $('#pageNum_label').text('1');

    $('#movie_list_div').hide(50);
    // if the input is not empty
    if(value) {
        initMoviesTable();
        searchMoviesFast(value);
        $('#movie_list_div').show(50);

    } else {
        $('#movie_list_div').hide(50);
        $('#next_btn').prop('disabled', true);
        $('#prev_btn').prop('disabled', true);
    }
}

// this function performs a fast search
// in movies. its called after each keypress
function searchMoviesFast(mTitle) {
    // creating the url: title
    var url = "/rest/movieTime/movie/byTitle/" + mTitle + '/';
    // creating the url: page number
    var pNum = $('#pageNum_label').text();
    url = url + pNum + '/';
    // creating the url: page size
    var pSize = $('#pageSize_i').val();
    url = url + pSize;
    url = url + '?access_token=' + Cookies.get('accessToken');

    console.log(url);

    $.ajax({
        url: url,
        success: function(data) {
            console.log(data);
            if(data.movies.length != 0) {
                updateMovieListTable(data);
                $('#moviesTable').show();
            } else {
                $('#moviesTable').hide(50);
                $('#next_btn').prop('disabled', true);
                $('#prev_btn').prop('disabled', true);
            }
        },
        error: function(data) {
            ajaxFailedHandler(data, searchMoviesFast, logOut, mTitle);
        }
    });
}

// this function searches for movies with
// much more results.
function searchMoviesMore(mTitle) {
    // creating the url: title
    var url = "/rest/movieTime/movie/byPartTitle/" + mTitle + '/';
    // creating the url: page number
    var pNum = $('#pageNum_label').text();
    url = url + pNum + '/';
    // creating the url: page size
    var pSize = $('#pageSize_i').val();
    url = url + pSize;
    url = url + '?access_token=' + Cookies.get('accessToken');

    console.log(url);
    var $loading = $('#loadingDiv');
    $loading.show();
    $.ajax({
        url: url,
        success: function(data) {
            if(data.movies.length != 0) {
                updateMovieListTable(data);
                $('#moviesTable').show();
            } else {
                $('#moviesTable').hide(50);
                $('#next_btn').prop('disabled', true);
                $('#prev_btn').prop('disabled', true);
            }
            $loading.hide();
        },
        error: function(data) {
            $loading.hide();
            ajaxFailedHandler(data, searchMoviesMore, logOut, mTitle);
        }
    });
}


// if the input text changes, update the table of movies
function updateMovieListTable(data) {
    var nextBtn = $('#next_btn');
    var prevBtn = $('#prev_btn');
    var pageNumLabel = $('#pageNum_label');
    nextBtn.prop('disabled', true);
    prevBtn.prop('disabled', true);

    // setting the previous and next buttons
    if(data.links.length == 0) {
        nextBtn.prop('disabled', true);
        prevBtn.prop('disabled', true);
        pageNumLabel.text('1');

    } else if(data.links.length == 1) {
        if (data.links[0].rel == "nextPage") {
            nextBtn.prop('disabled', false);
            prevBtn.prop('disabled', true);
            pageNumLabel.text('1');

        } else {
            nextBtn.prop('disabled', true);
            prevBtn.prop('disabled', false);
        }

    } else {
        nextBtn.prop('disabled', false);
        prevBtn.prop('disabled', false);
    }


    // uploading the table with data
    var table = document.getElementById('moviesTable');
    var movies = data.movies;
    for(var i = 0; i < movies.length; i++) {
        var link1 = movies[i].links[0].href;

        var movie = movies[i];
        var row = table.insertRow(-1);
        var cell_title = row.insertCell(-1);
        var cell_tagline = row.insertCell(-1);
        var cell_movieid = row.insertCell(-1);
        var cell_btn = row.insertCell(-1);

        $(cell_title).text(movie.title);
        if (movies[i].tagline) {
            $(cell_tagline).text(movie.tagline.taglineText);

        } else {
            $(cell_tagline).text('');
        }
        $(cell_movieid).text(movie.movieId);

        var detailsBtn = document.createElement('input');
        detailsBtn.setAttribute('type', 'button');
        detailsBtn.setAttribute('class', 'btn btn-default');
        detailsBtn.setAttribute('value', 'Show details');
        detailsBtn.setAttribute('name', link1);
        $(detailsBtn).click(loadMovieDetails);
        cell_btn.appendChild(detailsBtn);

    }

};