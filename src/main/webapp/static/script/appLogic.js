/**
 * Created by Attila on 2015-04-30.
 */
// when document is ready, set the slider's label
$(document).ready(function(){
    // init pageSize paragraph
    var pSize = $('#pageSize_i').val();
    $('#pSize_label').text(pSize);

    // hide buttons
    $('#next_btn').prop('disabled', true);
    $('#prev_btn').prop('disabled', true);

    // hide movie details table
    $('#movieDetails_table').hide();

    console.log(pSize);
});

// if the slider changes, we update the slider
function sliderChanged(value) {
    $('#pSize_label').text(value);
    $('#pageNum_label').text('1');

    updateTable($('#mTitle_input').val());
};

// event handler for the click on next button
function nextClicked() {
    var pageNumLabel = $('#pageNum_label');
    var pNum = parseInt(pageNumLabel.text());
    pNum = pNum + 1;
    console.log(pNum);
    pageNumLabel.text(pNum.toString());

    updateTable($('#mTitle_input').val());
}

// event handler for the click on previous button
function prevClicked() {
    var pageNumLabel = $('#pageNum_label');
    var pNum = parseInt(pageNumLabel.text());
    if(pNum > 1)
        pNum = pNum - 1;
    console.log(pNum);
    pageNumLabel.text(pNum.toString());

    updateTable($('#mTitle_input').val());
}

// event handler for the title input
function titleChanged(value) {
    $('#pageNum_label').text('1');

    updateTable(value);
}

// if user clicks on the move details button
function loadMovieDetails(href) {
    console.log(href);
    console.log('button pressed');
}

$(document).on('click', '.details_button', function(event) {
    console.log(event.target.name);
    console.log('button pressed');
});


// if the input text changes, update the table
function updateTable(value) {
    $('#moviesTable').empty().append(
        "<tr> " +
            "<th>Title</th> " +
            "<th>Tagline</th> " +
            "<th>movieId</th> " +
            "<th>link</th> " +
        "</tr>");

    var nextBtn = $('#next_btn');
    var prevBtn = $('#prev_btn');
    var pageNumLabel = $('#pageNum_label');
    nextBtn.prop('disabled', true);
    prevBtn.prop('disabled', true);

    var mTitle = value;
    if (mTitle) {
        // creating the url: title
        var url = "/rest/movie/byTitle/" + mTitle + '/';
        // creating the url: page number
        var pNum = pageNumLabel.text();
        url = url + pNum + '/';
        // creating the url: page size
        var pSize = $('#pageSize_i').val();
        url = url + pSize;

        console.log(url);
        $.ajax({
            url: url
        }).then(function(data) {
            // setting the previous and next buttons
            console.log(pageNumLabel);

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


            console.log(data);
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
                    $(cell_tagline).text(movie.tagline.taglinetext);

                } else {
                    $(cell_tagline).text('');
                }
                $(cell_movieid).text(movie.movieid);

                var detailsBtn = document.createElement('input');
                detailsBtn.setAttribute('type', 'button');
                detailsBtn.setAttribute('class', 'details_button');
                detailsBtn.setAttribute('value', 'Show details');
                detailsBtn.setAttribute('name', link1);
                cell_btn.appendChild(detailsBtn);
                //$(detailsBtn).click(loadMovieDetails(link1));

            }
        });
    }
};