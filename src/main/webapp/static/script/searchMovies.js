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

    console.log(pSize);
});

// if the slider changes, we update the slider
function sliderChanged(value) {
    $('#pSize_label').text(value);
    $('#pageNum_label').text('1');

    updateTable($('#mTitle_input').val());
};

function nextClicked() {
    var pageNumLabel = $('#pageNum_label');
    var pNum = parseInt(pageNumLabel.text());
    pNum = pNum + 1;
    console.log(pNum);
    pageNumLabel.text(pNum.toString());

    updateTable($('#mTitle_input').val());
}

function prevClicked() {
    var pageNumLabel = $('#pageNum_label');
    var pNum = parseInt(pageNumLabel.text());
    if(pNum > 1)
        pNum = pNum - 1;
    console.log(pNum);
    pageNumLabel.text(pNum.toString());

    updateTable($('#mTitle_input').val());
}

function titleChanged(value) {
    $('#pageNum_label').text('1');

    updateTable(value);
}

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
            var table = '';
            var movies = data.movies;
            for(var i = 0; i < movies.length; i++) {
                if (movies[i].tagline) {
                    $('#moviesTable').append(
                        '<tr>' +
                        '<td>' + movies[i].title + '</td>' +
                        '<td>' + movies[i].tagline.taglinetext + '</td>' +
                        '<td>' + movies[i].movieid + '</td>' +
                        '<td>' + movies[i].links[0].href + '</td>' +
                        '</tr>'
                    );

                } else {
                    $('#moviesTable').append(
                        '<tr>' +
                        '<td>' + movies[i].title + '</td>' +
                        '<td>' + "" + '</td>' +
                        '<td>' + movies[i].movieid + '</td>' +
                        '<td>' + movies[i].links[0].href + '</td>' +
                        '</tr>'
                    );
                }
            }

            $('#moviesTable').first().after(table);
        });
    }
};