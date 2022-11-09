const commentsection = document.getElementById("comments");

const getReplies = (margin, copy, j, replies, repeats) => {
    if (copy[j].comments.length == 0) {
        replies += (`<div style='margin-left: ${margin}rem' class="reply comment">
                            <div class="comment-info">
                                <p class="comment-points">0 points ${copy[j].id}</p>
                                <p class="comment-user"> by ${copy[j].user.username}</p>
                                <p class="comment-date"> at ${copy[j].time}</p>
                            </div>
                                <div class="comment-body">
                                    <p class="comment-date">${copy[j].body}</p>
                                </div>
                            </div>
                        </div>`);
        return replies;
    }

    for (let x = 0; x < copy[j].comments.length; ++x) {
        if (!repeats.includes(copy[j].comments[x].id)) {
            repeats.push(copy[j].comments[x].id);
            replies += getReplies(margin + 3, copy[j].comments, x, replies, repeats);
        }
    }
    return replies;

}

const getUserComments = async (url) => {
    const response = await fetch("http://localhost:8081/comment/user/" + url);
    const json = await response.json();
    commentsection.innerHTML = "";
    let repeats = [];
    for (let i = 0; i < json.length; i++) {
        let cssclass = "comment";
        if (i == json.length - 1) cssclass = "comment last-sub";
        let replies = "";
        let margin = 3;
        let copy = json[i].comments;
        for (let j = 0; j < copy.length; ++j) {
            if (!repeats.includes(copy[j].id)) {
                repeats.push(copy[j].id);
                replies += getReplies(margin, copy, j, replies, repeats);
            }
        }
        if (!repeats.includes(json[i].id)) {
            repeats.push(json[i].id)
            commentsection.innerHTML += `<li class="${cssclass}">
                                        <div class="comment-info">
                                            <p class="comment-points">0 points ${json[i].id}</p>
                                            <p class="comment-user"> by ${json[i].user.username}</p>
                                            <p class="comment-date"> at ${json[i].time}</p>
                                        </div>
                                        <div class="comment-body">
                                            <p class="comment-date">${json[i].body}</p>
                                        </div>
                                        ${replies}
                                    </li>`;
        }
        else {
            console.log("aaaaaaaaaaa")
        }
    }
}

getUserComments("alex");
