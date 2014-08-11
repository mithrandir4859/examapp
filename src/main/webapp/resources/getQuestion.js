var time;
var tickTack = false;

$(document).ready(function () {
    loadQuestion();
    $("#next").click(loadQuestion);
});

function loadQuestion() {
    var request = makeRequestForQuestion();
    request.done(function (answeredQuestion) {
        var question = answeredQuestion.question;
        if (question == undefined){
            redirect();
            return;
        } else{
            time = question.timeLimit - 2000;
            placeQuestionOnPage(answeredQuestion);
        }

    });
    countDown();
};

function countDown() {
    if (tickTack)
        return;
    setInterval(function () {
        $('#countdown').text((time -= 1000) / 1000);
        if (time <= 0) {
            loadQuestion();
        }
    }, 1000);
    tickTack = true;
}

function makeRequestForQuestion() {
    if (request) {
        request.abort();
    }	// abort any pending request

    var request = $.ajax({
        url: "/examapp/student/next",
        type: "post",
        data: {
            answerId: $("form input[name='answer']:checked").val(),
            questionNum: $("form input[name='sequenceNumber']").val()}
    });
    return request;
}

function redirect(){
    window.location.replace("/examapp/student/examStatistics");
}

function placeQuestionOnPage(answeredQuestion) {
    $("#question").remove();
    var question = answeredQuestion.question;
    if (question == undefined) {
        // no more questions
        redirect();
        return;
    }
    var forAppend =
        "<div id='question'>\n" +
        "<div>" + question.text + "</div>\n" +
        "<form>\n" + "<input type='hidden' value='"
        + answeredQuestion.sequenceNumber +
        "' name='sequenceNumber' />\n";

    var answers = question.answers;
    for (var i = 0; i < answers.length; i++) {
        var answer = answers[i];
        forAppend += "<input type='radio' name='answer' value='"
            + answer.id + "' />" + answer.text + "<br />\n";
    }
    forAppend += "</form>\n</div>\n";
    $("#insertQuestion").append(forAppend);
};